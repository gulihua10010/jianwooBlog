package cn.jianwoo.blog.service.bo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:53
 */
@Data
public class ForgetPwdResBO implements Serializable {
    private static final long serialVersionUID = 1970975993765800810L;
    /**
     * 是否成功<BR>
     * true --成功<BR>
     * false --失败<BR>
     */
    private boolean flagSuccess;
    /**
     * 错误代码, flagSuccess 失败必填
     */
    private String code;
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

    public static ForgetPwdResBO getInstance() {
        return new ForgetPwdResBO(true);
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        if (StringUtils.isNotBlank(reason)) {
            this.flagSuccess = false;
        }
        this.reason = reason;
    }

    public ForgetPwdResBO(boolean flagSuccess) {
        this.flagSuccess = flagSuccess;
    }
}