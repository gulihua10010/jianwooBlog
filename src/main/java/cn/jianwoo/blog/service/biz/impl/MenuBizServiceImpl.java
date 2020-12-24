package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.MenuTransDao;
import cn.jianwoo.blog.dao.biz.MenuBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.extension.MenuExt;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.MenuBizException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.bo.MenuValidateBO;
import cn.jianwoo.blog.util.DomainUtil;
import cn.jianwoo.blog.validation.BizValidation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuBizServiceImpl implements MenuBizService {
    @Autowired
    private MenuTransDao menuTransDao;
    @Autowired
    private MenuBizDao menuBizDao;
    @Autowired
    private ArticleTransDao articleTransDao;

    @Override
    public List<MenuExt> queryBackGroudMenuList() throws JwBlogException {

        List<Menu> menus = menuTransDao.queryMenuByType(MenuTypeEnum.BACKEND.getValue());
        return queryMenuWithLevel(menus);
    }


    private List<MenuExt> queryMenuWithLevel(List<Menu> oldMenu) throws JwBlogException {
        List<MenuExt> menus = new ArrayList<>();
        Map<Long, List<MenuExt>> menuMaps = new HashMap<>();
        for (Menu m : oldMenu) {
            MenuExt tmp = new MenuExt();
            BeanUtils.copyProperties(m, tmp);
            if (!menuMaps.containsKey(m.getParentOid())) {
                List<MenuExt> menus1 = new ArrayList<>();
                menus1.add(tmp);
                menuMaps.put(m.getParentOid(), menus1);
            } else {

                menuMaps.get(m.getParentOid()).add(tmp);
            }
            if (m.getParentOid() == 0L) {
                menus.add(tmp);
            }
        }
        processMenu(menus, new MenuExt(), menuMaps);

        Collections.sort(menus, new Comparator<MenuExt>() {
            @Override
            public int compare(MenuExt o1, MenuExt o2) {
                int diff = o1.getIndex() - o2.getIndex();
                if (diff > 0) {
                    return 1;
                } else if (diff < 0) {
                    return -1;
                }
                return 0;
            }
        });
        return menus;
    }


    private void processMenu(List<MenuExt> menus, MenuExt newMenu, Map<Long, List<MenuExt>> menuMaps)
            throws JwBlogException {
        for (MenuExt m : menus) {
            if (menuMaps.containsKey(m.getOid())) {
                List<MenuExt> m1 = menuMaps.get(m.getOid());
                processMenu(m1, newMenu, menuMaps);
                Collections.sort(newMenu.getSubMenus(), new Comparator<MenuExt>() {
                    @Override
                    public int compare(MenuExt o1, MenuExt o2) {
                        int diff = o1.getIndex() - o2.getIndex();
                        if (diff > 0) {
                            return 1;
                        } else if (diff < 0) {
                            return -1;
                        }
                        return 0;
                    }
                });
                m.setSubMenus(newMenu.getSubMenus());
                try {
                    DomainUtil.clearProperties(newMenu);
                } catch (Exception e) {
                    throw new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e).print();
                }
            }
            if (m.getParentOid() != 0) {
                if (newMenu.getSubMenus() == null) {
                    List<MenuExt> subMenu = new ArrayList<>();
                    subMenu.add(m);
                    newMenu.setSubMenus(subMenu);
                } else {
                    newMenu.getSubMenus().add(m);
                }

            }

        }
    }

    //
    // public static void main(String[] args) throws Exception
    // {
    // List<Menu> menus=new ArrayList<>();
    // Menu m=new Menu();
    // m.setOid(1L);
    // m.setParentOid(0L);
    // menus.add(m);
    // m=new Menu();
    // m.setOid(2L);
    // m.setParentOid(0L);
    // menus.add(m);
    // m=new Menu();
    // m.setOid(3L);
    // m.setParentOid(1L);
    // menus.add(m);
    // m=new Menu();
    // m.setOid(4L);
    // m.setParentOid(1L);
    // menus.add(m);
    // m=new Menu();
    // m.setOid(5L);
    // m.setParentOid(2L);
    // menus.add(m);
    // m=new Menu();
    // m.setOid(6L);
    // m.setParentOid(3L);
    // menus.add(m);
    // MenuServiceImpl x=new MenuServiceImpl();
    // List<MenuExt> p= x.getMenuWithLevel(menus);
    // for (MenuExt m1:p)
    // {
    // System.out.print(m1.getOid()+"==");
    // for (MenuExt m2:m1.getSubMenus())
    // {
    // System.out.print(m2.getOid()+",");
    // if (m2.getSubMenus()!=null)
    // {
    // System.out.print(m2.getOid()+"==");
    // for (MenuExt m3:m2.getSubMenus())
    // {
    // System.out.print(m3.getOid()+",");
    //
    // }
    // }
    // }
    // System.out.println();
    // }
    //
    // }


    @Override
    public List<MenuExt> queryFrontDeskMenuList() throws JwBlogException {
        List<Menu> menus = menuTransDao.queryMenuByType(MenuTypeEnum.FRONTEND.getValue());
        return queryMenuWithLevel(menus);
    }


    @Override
    public int countMenu(int type) {
        return menuBizDao.countMenu(type);
    }


    @Override
    public void doAddMenu(String name, Integer type, Long parentOid, String text, String icon, String url)
            throws JwBlogException {

        Menu menu = new Menu();
        menu.setName(name);
        menu.setType(type);
        menu.setParentOid(parentOid == null ? 0L : parentOid);
        menu.setCreateDate(new Date());
        menu.setUpdateDate(new Date());
        int index = countMenuWithSameLevel(type, parentOid);
        menu.setIndex(index + 1);
        menu.setIcon(icon);
        menu.setText(text);
        menu.setUrl(url);
        try {
            menuTransDao.doInsert(menu);
        } catch (DaoException e) {
            throw MenuBizException.CREATE_FAILED_EXCEPTION.format(name).print();
        }
    }


    @Override
    public int countMenuWithSameLevel(Integer type, Long parentOid) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("parentOid", parentOid);
        return menuBizDao.countMenuWithSameLevel(params);
    }


    @Override
    public void doReSortMenuByoids(Long[] oids) throws JwBlogException {
        int n = 0;
        for (Long oid : oids) {
            Menu menu = new Menu();
            menu.setOid(oid);
            menu.setIndex(++n);
            menu.setUpdateDate(new Date());
            try {
                menuTransDao.doUpdateByPrimaryKeySelective(menu);
            } catch (DaoException e) {
                throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();

            }
        }
    }


    @Override
    public void doUpdateMenuName(String text, Long oid) throws JwBlogException {
        Menu menu = new Menu();
        menu.setOid(oid);
        menu.setText(text);
        menu.setUpdateDate(new Date());
        try {
            menuTransDao.doUpdateByPrimaryKeySelective(menu);
        } catch (DaoException e) {
            throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();

        }
    }


    @Override
    public String queryMenuNameById(Long oid) throws JwBlogException {

        Menu m = null;
        try {
            m = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        return m.getText();
    }


    @Override
    public String queryParentMenuNameBySubId(Long oid) throws JwBlogException {

        Menu subMenu = null;
        try {
            subMenu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        Menu parentMenu = null;
        try {
            parentMenu = menuTransDao.queryMenuByPrimaryKey(subMenu.getParentOid());
        } catch (DaoException e) {
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        return parentMenu.getText();
    }


    @Override
    public Menu queryParentMenuBySubId(Long oid) throws JwBlogException {
        BizValidation.paramValidate(oid, "oid");

        Menu subMenu = null;
        try {
            subMenu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        Menu parentMenu = null;
        try {
            parentMenu = menuTransDao.queryMenuByPrimaryKey(subMenu.getParentOid());
        } catch (DaoException e) {
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        return parentMenu;
    }


    @Override
    public List<Menu> querySubMenuByParentId(Long oid) throws JwBlogException {

        Menu m = null;
        try {
            m = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }

        return menuTransDao.queryMenuByParentIdAndType(oid, m.getType());
    }


    @Override
    public List<Menu> querySubMenuOrderedList(Integer type) {
        final List<Menu> menuList = menuTransDao.queryMenuByType(type);
        List<Menu> newMenuList = menuList.stream().filter(menu -> (menu.getParentOid().compareTo(0L) != 0))
                .collect(Collectors.toList());
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
    public void doSortFrontMenuList(List<Long> oidList) throws JwBlogException {
        Integer sort = 1;

        for (Long oid : oidList) {
            Menu menu = null;
            try {
                menu = menuTransDao.queryMenuByPrimaryKey(oid);
                menu.setIndex(sort++);
                menu.setUpdateDate(new Date());

            } catch (DaoException e) {
                throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
            }
            if (menu != null) {
                try {
                    menuTransDao.doUpdateByPrimaryKeySelective(menu);
                } catch (DaoException e) {
                    throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
                }
            }

        }

    }


    @Override
    public void doUpdateMenu(Long oid, String text, String name, String icon, String url) throws JwBlogException {
        Menu menu = new Menu();
        menu.setOid(oid);
        menu.setName(name);
        menu.setText(text);
        menu.setIcon(icon);
        menu.setUrl(url);
        menu.setUpdateDate(new Date());
        try {
            menuTransDao.doUpdateByPrimaryKeySelective(menu);
        } catch (DaoException e) {
            throw MenuBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();

        }
    }


    @Override
    public void doRemoveMenuById(Long oid) throws JwBlogException {
        try {
            menuTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MenuBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }

    }


    @Override
    public MenuValidateBO doValidateSubMenuExist(Long oid) throws JwBlogException {
        Menu menu = null;
        try {
            menu = menuTransDao.queryMenuByPrimaryKey(oid);
        } catch (DaoException e) {
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
            throw MenuBizException.NOT_EXIST_EXCEPTION.format(oid).print();
        }
        List<Article> articleList = articleTransDao.queryArticleByType(oid.intValue());

        MenuValidateBO bo = new MenuValidateBO();
        bo.setIsSuccess(Constants.YES);
        if (CollectionUtils.isNotEmpty(articleList)) {
            bo.setIsSuccess(Constants.NO);
            bo.setResultMsg(String.format(ExceptionConstants.MENU_DEL_ARTICLE_EXITS_DESC, menu.getText()));

        }
        return bo;
    }

}