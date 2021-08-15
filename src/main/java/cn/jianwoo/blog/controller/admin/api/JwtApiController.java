package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.router.admin.JwtApiUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-06 11:53
 */
@RestController
@RequestMapping(JwtApiUrlConfig.URL_PREFIX)
@Slf4j
public class JwtApiController extends BaseController {
    @GetMapping(JwtApiUrlConfig.URL_JWT_VERIFY_TOKEN)


    /**
     * 初始化登录验证码<br/>
     * url:/api/admin/jwt/verify/token<br/>
     *
     * @return 返回响应 {@link BaseResponseDto}
     * @author gulihua
     */
    public String verifyToken() {
        log.info(">>Start Jwt Verify Token::");
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }

}
