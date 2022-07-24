package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:00
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ForgetPasswordRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    /**
     * 登录ID(加密后)
     */
    private String loginIdEncrypt;
    /**
     * 验证码(加密后)
     */
    private String captchaCodeEncrypt;
    /**
     * 登录ID
     */
    private String loginID;
    /**
     * 验证码
     */
    private String captchaCode;

    /**
     * 新密码
     */
    private String newPasswordEncrypt;

    /**
     * 验证码token
     */
    private String captcha_token;

    /**
     * 前端guid
     */
    private String guid;

    /**
     * 邮箱地址
     */
    private String email;


}
