package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.SubTokenApiUrlConfig;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dto.request.TokenGenRequest;
import cn.jianwoo.blog.dto.response.SubTokenResponse;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.ProcessTokenUtil;
import cn.jianwoo.blog.validation.BizValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-08 18:26
 */
@RestController
@RequestMapping(SubTokenApiUrlConfig.URL_PREFIX)
@Slf4j
public class SubTokenGenerateApiController extends BaseController {

    /**
     * 生成token，用于验证表单重复提交<br/>
     * url:/api/admin/token/generate<br/>
     *
     * @param param JSON 参数({@link TokenGenRequest})<br/>
     *              pageId<br/>
     * @return 返回响应 {@link SubTokenResponse}<br/>
     * token
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(SubTokenApiUrlConfig.URL_TOKEN_GENERATE)
    public String generateToken(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            TokenGenRequest request = this.convertParam(param, TokenGenRequest.class);
            BizValidation.paramValidate(request.getPageId(), "pageId", "pageId不能为空!");
            PageIdEnum pageIdEnum = PageIdEnum.getEnum(request.getPageId());
            if (pageIdEnum == null) {
                throw new JwBlogException(ExceptionConstants.VALIDATION_FAILED, "页面无效!");
            }
            String token = ProcessTokenUtil.generateToken(super.request, request.getPageId());
            SubTokenResponse response = SubTokenResponse.getInstance();
            response.setToken(token);
            return super.responseToJSONString(response);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);

        }

    }


}
