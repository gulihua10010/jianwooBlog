package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPwdResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    /**
     * 是否成功
     */
    private String flagSuccess;
    /**
     * 失败原因, flagSuccess 失败必填
     */
    private String reason;
    /**
     * 登录ID(加密后)
     */
    private String loginIdEncrypt;
    /**
     * 验证码(加密后)
     */
    private String captchaCodeEncrypt;

    public static ForgetPwdResponse getInstance() {
        return new ForgetPwdResponse();
    }
}