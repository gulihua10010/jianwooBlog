package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author GuLihua
 * @Description
 * @date 2021-09-09 18:27
 */
public interface EmailBizService {

    /**
     * 忘记密码发邮件
     *
     * @param loginID 登录ID
     * @param emailTo 收件人
     * @return
     * @author gulihua
     */
    void sendEmail4ForgetPwd(String loginID, String emailTo) throws JwBlogException;
}
