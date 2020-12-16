package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.page.MenuApiUrlConfig;
import cn.jianwoo.blog.dto.request.EntityOidListRequest;
import cn.jianwoo.blog.dto.request.EntityOidRequest;
import cn.jianwoo.blog.dto.request.MenuVoRequest;
import cn.jianwoo.blog.dto.response.MenuValidateResponse;
import cn.jianwoo.blog.enums.MenuTypeEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.MenuBizService;
import cn.jianwoo.blog.service.bo.MenuValidateBO;
import cn.jianwoo.blog.validation.BizValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:12
 */

@RestController
@RequestMapping(MenuApiUrlConfig.URL_PREFIX)
public class MenuApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MenuApiController.class);
    @Autowired
    private MenuBizService menuBizService;

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


    @PostMapping(MenuApiUrlConfig.URL_MENU_ADD)
    public String add(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MenuVoRequest request = this.convertParam(param, MenuVoRequest.class);
            BizValidation.paramValidate(request.getParentOid(), "parentOid", "菜单父id不能为空!");
            BizValidation.paramValidate(request.getText(), "text", "菜单名字不能为空!");
            BizValidation.paramValidate(request.getName(), "name", "菜单名不能为空!");
            menuBizService.doAddMenu(request.getName(), MenuTypeEnum.FRONTEND.getValue(), request.getParentOid(),
                    request.getText(), request.getIcon(), request.getUrl());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    @PostMapping(MenuApiUrlConfig.URL_MENU_UPDATE)
    public String update(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            MenuVoRequest request = this.convertParam(param, MenuVoRequest.class);
            BizValidation.paramValidate(request.getOid(), "oid", "菜单id不能为空!");
            BizValidation.paramValidate(request.getText(), "text", "菜单文本不能为空!");
            BizValidation.paramValidate(request.getName(), "name", "菜单名不能为空!");
            menuBizService.doUpdateMenu(request.getOid(), request.getText(), request.getName(), request.getIcon(),
                    request.getUrl());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


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

}
