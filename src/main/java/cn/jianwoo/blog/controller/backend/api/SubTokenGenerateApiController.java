package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.page.SubTokenApiUrlConfig;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dto.request.TokenGenRequest;
import cn.jianwoo.blog.dto.response.SubTokenResponse;
import cn.jianwoo.blog.enums.PageIdEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.ProcessTokenUtil;
import cn.jianwoo.blog.validation.BizValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-08 18:26
 */
@RestController
@RequestMapping(SubTokenApiUrlConfig.URL_PREFIX)
public class SubTokenGenerateApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SubTokenGenerateApiController.class);

    /**
     * 生成token，用于验证表单重复提交<br/>
     * url:/api/admin/token/generate<br/>
     *
     * @param param JSON 参数({@link TokenGenRequest})
     *              pageId<br/>
     * @return 返回响应 {@link SubTokenResponse}
     * token
     * @author gulihua
     */
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