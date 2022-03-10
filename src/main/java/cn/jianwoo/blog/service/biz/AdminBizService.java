package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.service.bo.ForgetPwdResBO;
import org.springframework.lang.NonNull;

public interface AdminBizService {
    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @return
     * @author gulihua
     */
    void register(@NonNull String username, @NonNull String password, String ip) throws JwBlogException;


    /**
     * 登录
     *
     * @param loginID 登录ID
     *                //* @param password 密码 (已经移除，在security做校验)
     * @param ip      ip
     * @return
     * @author gulihua
     */
    AuthToken authLogin(@NonNull String loginID, @NonNull String ip) throws JwBlogException;

    /**
     * 通过登录ID查询<br>
     * (优化)先从缓存拿，没有再从数据库拿<br>
     *
     * @param loginID 登录ID
     * @return
     * @author gulihua
     */
    AdminBO queryAdminInfoByLoginId(String loginID) throws JwBlogException;

    /**
     * 通过主键查询<br>
     * (优化)先从缓存拿，没有再从数据库拿<br>
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    AdminBO queryAdminByOid(Long oid) throws JwBlogException;

    /**
     * 修改密码<br>
     *
     * @param loginID     登录ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     * @author gulihua
     */

    void doChangePassword(String loginID, String oldPassword, String newPassword) throws JwBlogException;


    /**
     * 修改密码验证码核实(忘记密码页面)<br>
     *
     * @param loginID     登录ID
     * @param email       邮箱地址
     * @param captchaCode 邮箱验证码
     * @return ForgetPwdResBO
     * @author gulihua
     */
    ForgetPwdResBO doForgetCaptchaAuth(String loginID, String email, String captchaCode) throws JwBlogException;

    /**
     * 修改密码(忘记密码页面)<br>
     *
     * @param loginIdEncrypt     登录ID(加密后)
     * @param newPasswordEncrypt 新密码(加密后)
     * @param captchaCodeEncrypt 邮箱验证码(加密后)
     * @return
     * @author gulihua
     */
    void doChangePassword4Forget(String loginIdEncrypt, String newPasswordEncrypt, String captchaCodeEncrypt) throws JwBlogException;


    /**
     * 保存修改用户编辑信息<br>
     *
     * @param adminBO adminBO
     * @return
     * @author gulihua
     */
    void doSaveEditInfo(AdminBO adminBO) throws JwBlogException;

    /**
     * 忘记密码时发送验证码(忘记密码页面)<br>
     *
     * @param loginID 用户名
     * @param email   邮箱
     * @return
     * @author gulihua
     */
    void doSendCaptcha4Forget(String loginID, String email) throws JwBlogException;
}
