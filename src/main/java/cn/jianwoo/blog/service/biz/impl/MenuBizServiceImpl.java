package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.MenuTransDao;
import cn.jianwoo.blog.dao.biz.MenuBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.MenuBizException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.bo.MenuBO;
import cn.jianwoo.blog.service.bo.MenuValidateBO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.DomainUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuBizServiceImpl implements MenuBizService {
    @Autowired
    private MenuTransDao menuTransDao;
    @Autowired
    private MenuBizDao menuBizDao;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CacheStore<String, Object> cacheStore;

    private static final Long TOP_LEVEL_MENU_OID = 0L;

    @Override
    public List<MenuBO> queryAdminMenuList() throws JwBlogException {
        String cacheKey = CacheKeyConstants.ADMIN_MENU_KEY;
        if (cacheStore.hasKey(cacheKey)) {
            return (List<MenuBO>) cacheStore.get(cacheKey).orElse(new ArrayList<MenuBO>());
        }
        List<Menu> menus = menuTransDao.queryEffectiveMenuByType(MenuTypeEnum.BACKEND.getValue());
        List<MenuBO> allMenus = queryMenuWithLevel(menus);
        cacheStore.put(cacheKey, allMenus);
        return allMenus;
    }


    private List<MenuBO> queryMenuWithLevel(List<Menu> oldMenuList) throws JwBlogException {
        List<MenuBO> menus = new ArrayList<>();
        Map<Long, List<MenuBO>> menuMaps = new HashMap<>();
        for (Menu menu : oldMenuList) {
            MenuBO tmp = new MenuBO();
            BeanUtils.copyProperties(menu, tmp);
            if (!menuMaps.containsKey(menu.getParentOid())) {
                List<MenuBO> m = new ArrayList<>();
                m.add(tmp);
                menuMaps.put(menu.getParentOid(), m);
            } else {
                menuMaps.get(menu.getParentOid()).add(tmp);
            }
            if (Objects.equals(menu.getParentOid(), TOP_LEVEL_MENU_OID)) {
                menus.add(tmp);
            }
        }
        processMenu(menus, new MenuBO(), menuMaps);

        menus.sort(Comparator.comparingInt(MenuBO::getIndex));
        return menus;
    }


    /**
     * 递归处理菜单
     *
     * @param menuList 只包含顶层级的菜单
     * @param tmpMenu  临时对象
     * @param menuMaps 根据ParentOid分组的map
     * @return
     * @author gulihua
     */
    private void processMenu(List<MenuBO> menuList, MenuBO tmpMenu, Map<Long, List<MenuBO>> menuMaps)
            throws JwBlogException {
        for (MenuBO menu : menuList) {
            if (menuMaps.containsKey(menu.getOid())) {
                List<MenuBO> m = menuMaps.get(menu.getOid());
                processMenu(m, tmpMenu, menuMaps);
                tmpMenu.getSubMenuList().sort(Comparator.comparingInt(MenuBO::getIndex));
                menu.setSubMenuList(tmpMenu.getSubMenuList());
                try {
                    DomainUtil.clearProperties(tmpMenu);
                } catch (Exception e) {
                    log.error("MenuBizServiceImpl.processMenu exec failed, e:\n", e);
                    throw new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e).print();
                }
            }
            if (!Objects.equals(menu.getParentOid(), TOP_LEVEL_MENU_OID)) {
                if (tmpMenu.getSubMenuList() == null) {
                    List<MenuBO> subMenu = new ArrayList<>();
                    subMenu.add(menu);
                    tmpMenu.setSubMenuList(subMenu);
                } else {
                    tmpMenu.getSubMenuList().add(menu);
                }

            }

        }
    }


    @Override
    public List<MenuBO> queryAllMainMenuList() throws JwBlogException {
        List<Menu> menus = menuTransDao.queryMenuByType(MenuTypeEnum.FRONTEND.getValue());
        return queryMenuWithLevel(menus);
    }

    @Override
    public List<MenuBO> queryEffectiveMainHomeMenuList() throws JwBlogException {
        String cacheKey = CacheKeyConstants.HOME_MENU_KEY;
        if (cacheStore.hasKey(cacheKey)) {
            return (List<MenuBO>) cacheStore.get(cacheKey).orElse(new ArrayList<MenuBO>());
        }
        List<Menu> menus = menuTransDao.queryEffectiveMenuByType(MenuTypeEnum.FRONTEND.getValue());
        List<MenuBO> allMenus = queryMenuWithLevel(menus);
        cacheStore.put(cacheKey, allMenus);
        return allMenus;
    }

    @Override
    public List<MenuBO> queryEffectiveMainMenuList() throws JwBlogException {
        List<Menu> menus = menuTransDao.queryEffectiveMenuByType(MenuTypeEnum.FRONTEND.getValue());
        return queryMenuWithLevel(menus);
    }


    @Override
    public int countMenu(String type) {
        return menuBizDao.countMenu(type);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateMenu(MenuBO menuBO) throws JwBlogException {

        Date now = DateUtil.getNow();

        int index = countMenuWithSameLevel(menuBO.getType(), menuBO.getParentOid());
        Menu menu = JwBuilder.of(Menu::new)
                .with(Menu::setName, menuBO.getName())
                .with(Menu::setType, menuBO.getType())
                .with(Menu::setParentOid, menuBO.getParentOid() == null ? TOP_LEVEL_MENU_OID : menuBO.getParentOid())
                .with(Menu::setIndex, index + 1)
                .with(Menu::setIcon, menuBO.getIcon())
                .with(Menu::setText, menuBO.getText())
                .with(Menu::setUrl, menuBO.getUrl())
                .with(Menu::setFlagCategory, menuBO.getFlagCategory())
                .with(Menu::setValid, true)
                .with(Menu::setCreateTime, now)
                .with(Menu::setUpdateTime, now)
                .build();
        try {
            menuTransDao.doInsertSelective(menu);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.doAddMenu exec failed, e:\n", e);
            throw MenuBizException.CREATE_FAILED_EXCEPTION.format(menuBO.getName()).print();
        }
        registerBizEvent(menu.getOid(), menu.getText(), BizEventOptTypeEnum.CREATE);
    }


    @Override
    public int countMenuWithSameLevel(String type, Long parentOid) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("parentOid", parentOid);
        return menuBizDao.countMenuWithSameLevel(params);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doResortMenuByOids(Long[] oids) throws JwBlogException {
        int n = 0;
        for (Long oid : oids) {
            Menu menu = new Menu();
            menu.setOid(oid);
            menu.setIndex(++n);
            menu.setUpdateTime(new Date());
            registerBizEvent(menu.getOid(), null, BizEventOptTypeEnum.UPDATE_MENU_SORT);
            try {
                menuTransDao.doUpdateByPrimaryKeySelective(menu);
            } catch (DaoException e) {
                log.error("MenuBizServiceImpl.doReSortMenuByoids exec failed, e:\n", e);
                throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();

            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateMenuName(String text, Long oid) throws JwBlogException {
        Menu menu = new Menu();
        menu.setOid(oid);
        menu.setText(text);
        menu.setUpdateTime(new Date());
        try {
            menuTransDao.doUpdateByPrimaryKeySelective(menu);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.doUpdateMenuName exec failed, e:\n", e);
            throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(menu.getOid(), menu.getText(), BizEventOptTypeEnum.UPDATE);
    }


    @Override
    public String queryMenuNameById(Long oid) throws JwBlogException {

        Menu menu = null;
        try {
            menu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.queryMenuNameById exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        return menu.getText();
    }


    @Override
    public String queryParentMenuTextBySubId(Long oid) throws JwBlogException {

        Menu subMenu = null;
        try {
            subMenu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.queryParentMenuNameBySubId exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        Menu parentMenu = null;
        try {
            parentMenu = menuTransDao.queryMenuByPrimaryKey(subMenu.getParentOid());
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.queryParentMenuNameBySubId exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        return parentMenu.getText();
    }


    @Override
    public Menu queryParentMenuBySubId(Long oid) throws JwBlogException {
        Menu subMenu = null;
        try {
            subMenu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.queryParentMenuBySubId exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        if (TOP_LEVEL_MENU_OID.equals(subMenu.getParentOid())) {
            return null;
        }
        Menu parentMenu = null;
        try {
            parentMenu = menuTransDao.queryMenuByPrimaryKey(subMenu.getParentOid());
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.queryParentMenuBySubId exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(subMenu.getParentOid()).print();
        }
        return parentMenu;
    }


    @Override
    public List<Menu> querySubMenuByParentId(Long oid) throws JwBlogException {

        Menu m = null;
        try {
            m = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.querySubMenuByParentId exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }

        return menuTransDao.queryMenuByParentIdAndType(oid, m.getType());
    }

    @Override
    public List<Menu> querySubCategoryByParentId(Long oid) {
        return menuTransDao.querySubCategoryByParentId(oid);
    }


    @Override
    public List<Menu> querySubMenuCategoryList() {
        final List<Menu> menuList = menuTransDao.queryEffectiveMenuByType(MenuTypeEnum.FRONTEND.getValue());
        List<Menu> newMenuList = menuList.stream().filter(menu -> (menu.getParentOid().compareTo(TOP_LEVEL_MENU_OID) != 0))
                .filter(Menu::getFlagCategory).collect(Collectors.toList());
//        Map<Long, List<Menu>> menuMap = menuList.stream().collect(Collectors.groupingBy(Menu::getParentOid));
//        List<Menu> newMenuList = new ArrayList<>();
//        for (Menu menu : parentMenu)
//        {
//            newMenuList.add(menu);
//            List<Menu> subMenu = menuMap.get(menu.getOid());
//            if (CollectionUtils.isNotEmpty(subMenu))
//            {
//                newMenuList.addAll(subMenu);
//            }
//        }
        return newMenuList;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSortMainMenuList(List<Long> oidList) throws JwBlogException {
        Integer sort = 1;

        for (Long oid : oidList) {
            Menu menu = new Menu();
            menu.setIndex(sort++);
            menu.setOid(oid);
            menu.setUpdateTime(new Date());
            registerBizEvent(menu.getOid(), null, BizEventOptTypeEnum.UPDATE_MENU_SORT);
            try {
                menuTransDao.doUpdateByPrimaryKeySelective(menu);
            } catch (DaoException e) {
                log.error("MenuBizServiceImpl.doSortFrontMenuList exec failed, e:\n", e);
                throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateMenu(MenuBO menuBO) throws JwBlogException {
        Menu menu = new Menu();
        menu.setOid(menuBO.getOid());
        menu.setName(menuBO.getName());
        menu.setText(menuBO.getText());
        menu.setIcon(menuBO.getIcon());
        menu.setUrl(menuBO.getUrl());
        menu.setValid(menuBO.getValid());
        menu.setFlagCategory(menuBO.getFlagCategory());
        menu.setUpdateTime(new Date());
        try {
            menuTransDao.doUpdateByPrimaryKeySelective(menu);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.doUpdateMenu exec failed, e:\n", e);
            throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(menuBO.getOid()).print();
        }
        String cacheKey = CacheKeyConstants.HOME_MENU_KEY;
        cacheStore.delete(cacheKey);

        registerBizEvent(menu.getOid(), menu.getText(), BizEventOptTypeEnum.UPDATE);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveMenuById(Long oid) throws JwBlogException {
        try {
            menuTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.doRemoveMenuById exec failed, e:\n", e);
            throw MenuBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(oid, null, BizEventOptTypeEnum.DELETE);

    }


    @Override
    public MenuValidateBO doValidateSubMenuExist(Long oid) throws JwBlogException {
        Menu menu = null;
        try {
            menu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.doValidateSubMenuExist exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        List<Menu> menuList = querySubMenuByParentId(oid);
        MenuValidateBO bo = new MenuValidateBO();
        bo.setIsSuccess(Constants.YES);
        if (CollectionUtils.isNotEmpty(menuList)) {
            bo.setIsSuccess(Constants.NO);
            bo.setResultMsg(String.format(ExceptionConstants.MENU_DEL_SUB_EXITS_DESC, menu.getText()));

        }
        return bo;
    }


    @Override
    public MenuValidateBO doValidateArticleExistsInMenu(Long oid) throws JwBlogException {
        Menu menu = null;
        try {
            menu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("MenuBizServiceImpl.doValidateArticleExistsInMenu exec failed, e:\n", e);
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        List<Article> articleList = articleTransDao.queryArticleByCategory(oid.intValue());

        MenuValidateBO bo = new MenuValidateBO();
        bo.setIsSuccess(Constants.YES);
        if (CollectionUtils.isNotEmpty(articleList)) {
            bo.setIsSuccess(Constants.NO);
            bo.setResultMsg(String.format(ExceptionConstants.MENU_DEL_ARTICLE_EXITS_DESC, menu.getText()));

        }
        return bo;
    }

    @Override
    public MenuBO queryMenuByOid(String oid) throws JwBlogException {
        Menu menu;
        try {
            menu = menuTransDao.queryMenuByPrimaryKey(Long.parseLong(oid));
            MenuBO menuBO = new MenuBO();
            BeanUtils.copyProperties(menu, menuBO);
            return menuBO;
        } catch (Exception e) {
            log.error(">>Query menu by oid {} failed, e\r\n{}", oid, e);
            throw MenuBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();
        }

    }

    @Override
    public MenuBO queryCascadeMenuByOid(String oid) throws JwBlogException {
        Menu menu;
        try {
            menu = menuTransDao.queryMenuByPrimaryKey(Long.parseLong(oid));
            MenuBO menuBO = new MenuBO();

            if (menu.getParentOid() != null && !Objects.equals(menu.getParentOid(), TOP_LEVEL_MENU_OID)) {
                Menu parentMenu = menuTransDao.queryMenuByPrimaryKey(menu.getParentOid());
                MenuBO subMenuBO = new MenuBO();
                BeanUtils.copyProperties(parentMenu, menuBO);
                BeanUtils.copyProperties(menu, subMenuBO);
                menuBO.setSubMenuList(Collections.singletonList(subMenuBO));
            } else {
                BeanUtils.copyProperties(menu, menuBO);
            }
            return menuBO;
        } catch (Exception e) {
            log.error(">>queryCascadeMenuByOid exec failed, oid=[{}], e\r\n{}", oid, e);
            throw MenuBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();
        }
    }

    private void registerBizEvent(Long oid, String desc, BizEventOptTypeEnum optTypeEnum) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.MENU);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOptEntityId(oid != null ? String.valueOf(oid) : null);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }
}