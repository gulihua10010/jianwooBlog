package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.config.router.admin.MenuApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.MenuTransDao;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.MenuVoRequest;
import cn.jianwoo.blog.dto.response.ArticleMenuResponse;
import cn.jianwoo.blog.dto.response.BackendMenuResponse;
import cn.jianwoo.blog.dto.response.HomeMenuResponse;
import cn.jianwoo.blog.dto.response.MenuInfoResponse;
import cn.jianwoo.blog.dto.response.MenuValidateResponse;
import cn.jianwoo.blog.dto.response.vo.ArticleMenuVO;
import cn.jianwoo.blog.dto.response.vo.BackendMenuVO;
import cn.jianwoo.blog.dto.response.vo.BackendSubMenuVO;
import cn.jianwoo.blog.dto.response.vo.HomeMenuVO;
import cn.jianwoo.blog.dto.response.vo.HomeSubMenuVO;
import cn.jianwoo.blog.dto.response.vo.MenuVO;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.entity.extension.MenuExt;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.bo.MenuValidateBO;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:12
 */

@RestController
@RequestMapping(MenuApiUrlConfig.URL_PREFIX)
@Slf4j
public class MenuApiController extends BaseController {
    @Autowired
    private MenuBizService menuBizService;
    @Autowired
    private MenuTransDao menuTransDao;

    /**
     * 菜单排序(菜单页面)<br/>
     * url:api/admin/menu/sort<br/>
     *
     * @param param JSON 参数({@link EntityOidListRequest})
     *              entityOidList<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(MenuApiUrlConfig.URL_MENU_SORT)
    public String sort(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidListRequest request = this.convertParam(param, EntityOidListRequest.class);
            BizValidation.paramValidate(request.getEntityOidList(), "entityOidList", "菜单id不能为空!");
            menuBizService.doSortFrontMenuList(request.getEntityOidList());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

    /**
     * 菜单添加(菜单页面)<br/>
     * url:api/admin/menu/add<br/>
     *
     * @param param JSON 参数({@link MenuVoRequest})
     *              oid<br/>
     *              parentOid<br/>
     *              text<br/>
     *              url<br/>
     *              name<br/>
     *              icon<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.MENU_ADD)
    @SubToken()
    @PostMapping(MenuApiUrlConfig.URL_MENU_ADD)
    public String add(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MenuVoRequest request = this.convertParam(param, MenuVoRequest.class);
            BizValidation.paramValidate(request.getParentOid(), "parentOid", "菜单父id不能为空!");
            BizValidation.paramValidate(request.getText(), "text", "菜单名字不能为空!");
            BizValidation.paramValidate(request.getName(), "name", "菜单名不能为空!");
            BizValidation.paramLengthValidate(request.getName(), Constants.MENU_LENGTH, "name", "菜单名字不能大于10个字符!");
            BizValidation.paramLengthValidate(request.getText(), Constants.MENU_LENGTH, "text", "菜单文本不能大于10个字符!");
            BizValidation.paramRegexValidate(request.getName(), Constants.MENU_NAME_REGEX, "name", "菜单文本必须是字母、数字、符号\\'_#$@\\'，不能包含其他特殊字符!");
            menuBizService.doAddMenu(request.getName(), MenuTypeEnum.FRONTEND.getValue(), request.getParentOid(),
                    request.getText(), request.getIcon(), request.getUrl());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

    /**
     * 菜单更新(菜单页面)<br/>
     * url:api/admin/menu/update<br/>
     *
     * @param param JSON 参数({@link MenuVoRequest})
     *              oid<br/>
     *              parentOid<br/>
     *              text<br/>
     *              url<br/>
     *              name<br/>
     *              icon<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PageId(PageIdEnum.MENU_EDIT)
    @SubToken()
    @PostMapping(MenuApiUrlConfig.URL_MENU_UPDATE)
    public String update(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MenuVoRequest request = this.convertParam(param, MenuVoRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "菜单id不能为空!");
            BizValidation.paramValidate(request.getText(), "text", "菜单文本不能为空!");
            BizValidation.paramValidate(request.getName(), "name", "菜单名不能为空!");
            BizValidation.paramLengthValidate(request.getName(), Constants.MENU_LENGTH, "name", "菜单名字不能大于10个字符!");
            BizValidation.paramLengthValidate(request.getText(), Constants.MENU_LENGTH, "text", "菜单文本不能大于10个字符!");
            BizValidation.paramRegexValidate(request.getName(), Constants.MENU_NAME_REGEX, "name", "菜单文本必须是字母、数字、符号\\'_#$@\\'，不能包含其他特殊字符!");
            menuBizService.doUpdateMenu(request.getOid(), request.getText(), request.getName(), request.getIcon(),
                    request.getUrl(),request.getValid());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

    /**
     * 验证子菜单是否存在，删除菜单前验证(菜单页面)<br/>
     * url:api/admin/menu/validate/submenu<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(MenuApiUrlConfig.URL_MENU_VALIDATE_SUBMENU)
    public String validateSubMenuExist(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "菜单id不能为空!");
            MenuValidateBO bo = menuBizService.doValidateSubMenuExist(request.getEntityOid());
            MenuValidateResponse response = MenuValidateResponse.getInstance();
            BeanUtils.copyProperties(bo, response);
            return super.responseToJSONString(response);

        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }

    }

    /**
     * 验证菜单下文章是否存在，删除菜单前验证(菜单页面)<br/>
     * url:api/admin/menu/validate/article/exist<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(MenuApiUrlConfig.URL_MENU_VALIDATE_ARTICLE_EXIST)
    public String validateArticleExistsInMenu(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "菜单id不能为空!");
            MenuValidateBO bo = menuBizService.doValidateArticleExistsInMenu(request.getEntityOid());
            MenuValidateResponse response = MenuValidateResponse.getInstance();
            BeanUtils.copyProperties(bo, response);
            return super.responseToJSONString(response);

        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }

    }


    /**
     * 删除菜单(菜单页面)<br/>
     * url:api/admin/menu/remove<br/>
     *
     * @param param JSON 参数({@link EntityOidRequest})
     *              entityOid<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(MenuApiUrlConfig.URL_MENU_REMOVE)
    public String delete(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            EntityOidRequest request = this.convertParam(param, EntityOidRequest.class);
            BizValidation.paramValidate(request.getEntityOid(), "entityOid", "菜单id不能为空!");
            menuBizService.doRemoveMenuById(request.getEntityOid());

        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 获取后台菜单信息<br/>
     * url:/api/admin/menu/backend/info/list<br/>
     *
     * @return 返回响应 {@link BackendMenuResponse}
     * status<br/>
     * count<br/>
     * data<br/>
     * --title<br/>
     * --icon<br/>
     * --list<br/>
     * ----name<br/>
     * ----title<br/>
     * ----jump<br/>
     * @author gulihua
     */
    @GetMapping(MenuApiUrlConfig.URL_MENU_BACKEND_INFO_LIST)
    public String getBackendInfoList() {
        BackendMenuResponse response = BackendMenuResponse.getInstance();
        List<BackendMenuVO> list = new ArrayList<>();
        try {
            List<MenuExt> menuExtList = menuBizService.queryBackGroudMenuList();
            for (MenuExt menuExt : menuExtList) {
                List<BackendSubMenuVO> subMenuVOList = new ArrayList<>();
                BackendMenuVO vo = new BackendMenuVO();
                vo.setIcon(menuExt.getIcon());
                vo.setTitle(menuExt.getText());
                for (MenuExt subMenu : menuExt.getSubMenus()) {
                    BackendSubMenuVO subMenuVO = JwBuilder.of(BackendSubMenuVO::new)
                            .with(BackendSubMenuVO::setTitle, subMenu.getText())
                            .with(BackendSubMenuVO::setName, subMenu.getName())
                            .with(BackendSubMenuVO::setJump, subMenu.getUrl())
                            .build();
                    subMenuVOList.add(subMenuVO);
                }
                vo.setList(subMenuVOList);
                list.add(vo);
            }

        } catch (JwBlogException e) {
            log.error("MenuApiController.getBackendInfoList exec failed, e:\n", e);
        }
        response.setData(list);
        return super.responseToJSONString(response);

    }

    /**
     * 获取前台菜单(文章类别)<br/>
     * url:/api/admin/menu/article/type/list<br/>
     *
     * @return 返回响应 {@link ArticleMenuResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --name<br/>
     * @author gulihua
     */
    @GetMapping(MenuApiUrlConfig.URL_MENU_ARTICLE_TYPE_LIST)
    public String getArticleTypeMenuList() {
        ArticleMenuResponse response = ArticleMenuResponse.getInstance();
        List<ArticleMenuVO> list = new ArrayList<>();
        List<Menu> menuList = menuBizService.querySubMenuOrderedList(MenuTypeEnum.FRONTEND.getValue());
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                ArticleMenuVO vo = JwBuilder.of(ArticleMenuVO::new)
                        .with(ArticleMenuVO::setId, menu.getOid())
                        .with(ArticleMenuVO::setName, StringEscapeUtils.escapeHtml4(menu.getText())).build();
                list.add(vo);
            }
        }

        response.setData(list);
        return super.responseToJSONString(response);

    }


    /**
     * 获取前台首页菜单<br/>
     * url:/api/admin/menu/home/list<br/>
     *
     * @return 返回响应 {@link ArticleMenuResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --name<br/>
     * --index<br/>
     * --icon<br/>
     * --text<br/>
     * --url<br/>
     * --valid<br/>
     * --subMenuList<br/>
     * ----id<br/>
     * ----name<br/>
     * ----index<br/>
     * ----icon<br/>
     * ----text<br/>
     * ----url<br/>
     * ----valid<br/>
     * ----parentOid<br/>
     * @author gulihua
     */
    @GetMapping(MenuApiUrlConfig.URL_MENU_HOME_LIST)
    public String getHomeMenuList() {
        HomeMenuResponse response = HomeMenuResponse.getInstance();
        List<HomeMenuVO> list = new ArrayList<>();
        List<MenuExt> menuExtList = null;
        try {
            menuExtList = menuBizService.queryAllFrontDeskMenuList();
            if (CollectionUtils.isNotEmpty(menuExtList)) {
                for (MenuExt menuExt : menuExtList) {
                    List<HomeSubMenuVO> subMenuVOList = new ArrayList<>();
                    HomeMenuVO vo = new HomeMenuVO();
                    BeanUtils.copyProperties(menuExt, vo);
                    vo.setId(menuExt.getOid());
                    if (CollectionUtils.isNotEmpty(menuExt.getSubMenus())) {
                        for (MenuExt subMenu : menuExt.getSubMenus()) {
                            HomeSubMenuVO homeSubMenuVO = new HomeSubMenuVO();
                            BeanUtils.copyProperties(subMenu, homeSubMenuVO);
                            homeSubMenuVO.setId(subMenu.getOid());
                            subMenuVOList.add(homeSubMenuVO);
                        }
                    }

                    vo.setSubMenuList(subMenuVOList);
                    list.add(vo);
                }
            }

        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }

        response.setData(list);
        return super.responseToJSONString(response);

    }


    /**
     * 获取前台首页菜单<br/>
     * url:/api/admin/menu/info/{id}<br/>
     *
     * @return 返回响应 {@link ArticleMenuResponse}
     * status<br/>
     * data<br/>
     * --id<br/>
     * --name<br/>
     * --index<br/>
     * --icon<br/>
     * --text<br/>
     * --url<br/>
     * --valid<br/>
     * @author gulihua
     */
    @GetMapping(MenuApiUrlConfig.URL_MENU_INFO)
    public String getMenuInfo(@PathVariable("id") Long id) {
        MenuInfoResponse response = MenuInfoResponse.getInstance();
        MenuVO vo = new MenuVO();
        Menu menu = null;
        try {
            BizValidation.paramValidate(id, "id", "菜单Oid不能为空!");
            menu = menuTransDao.queryMenuByPrimaryKey(id);
            BeanUtils.copyProperties(menu, vo);
            vo.setId(menu.getOid());
            response.setData(vo);

        } catch (Exception e) {
            return super.exceptionToString(e);

        }

        return super.responseToJSONString(response);

    }

}
