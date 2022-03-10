package cn.jianwoo.blog.service.notify;

import cn.jianwoo.blog.exception.JwBlogException;

/**
 * 消息发送接口
 *
 * @author gulihua
 * @date 2022-02-26 16:18
 */
public interface NotifyMsgService {


    /**
     * 忘记密码发验证码
     *
     * @param loginID   登录ID
     * @param captcha 验证码
     * @param captcha 收信人
     * @return
     * @author gulihua
     */
    void sendCaptcha4ForgetPwd(String loginID, String captcha, String recipient) throws JwBlogException;
}
