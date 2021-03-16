package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.router.WeconfApiUrlConfig;
import cn.jianwoo.blog.dto.request.WebconfRequest;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:40
 */
@RestController
@RequestMapping(WeconfApiUrlConfig.URL_PREFIX)
@Slf4j
public class WebconfApiController extends BaseController {
    @Autowired
    private WebconfBizService webconfBizService;


    /**
     * 文章配置更新(文章配置页面)<br/>
     * url:/api/admin/webconf/update<br/>
     *
     * @param param JSON 参数({@link WebconfRequest})
     *              title<br/>
     *              author<br/>
     *              keywords<br/>
     *              description<br/>
     *              record<br/>
     *              domain<br/>
     *              footHtml<br/>
     *              logoImg<br/>
     *              homeImg<br/>
     *              numPerPage<br/>
     *              isComment<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status(000000-SUCCESS,999999-SYSTEM ERROR)
     * msg
     * @author gulihua
     */
    @PostMapping(WeconfApiUrlConfig.URL_WEBCONF_UPDATE)
    public String doUpdateWebconf(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            WebconfRequest request = this.convertParam(param, WebconfRequest.class);
            BizValidation.paramValidate(request.getTitle(), "title", "网站标题不能为空!");
            BizValidation.paramValidate(request.getLogoImg(), "logoImg", "网站LOGO不能为空!");
            BizValidation.paramValidate(request.getHomeImg(), "homeImg", "首页图片不能为空!");
            WebconfBO bo = new WebconfBO();
            BeanUtils.copyProperties(request, bo);
            webconfBizService.doUpdateConfigWithBO(bo);

        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }

        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

}
