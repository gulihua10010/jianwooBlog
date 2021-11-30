package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.bo.AdminBO;
import org.springframework.lang.NonNull;

public interface AdminBizService {
    /**
     * 注册
     *
     * @param name     用户名
     * @param password 密码
     * @return
     * @author gulihua
     */
    void register(@NonNull String name, @NonNull String password, String ip) throws JwBlogException;


    /**
     * 登录
     *
     * @param name 用户名
     *             //* @param password 密码 (已经移除，在security做校验)
     * @param ip   ip
     * @return
     * @author gulihua
     */
    AuthToken authLogin(@NonNull String name, @NonNull String ip) throws JwBlogException;

    /**
     * 通过名字查询<br>
     * (优化)先从缓存拿，没有再从数据库拿<br>
     *
     * @param name 名字
     * @return
     * @author gulihua
     */
    AdminBO queryAdminByName(String name) throws JwBlogException;

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
     * 修改密码(忘记密码页面)<br>
     *
     * @param loginID     登录ID
     * @param newPassword 新密码
     * @param verifyCode  邮箱验证码
     * @return
     * @author gulihua
     */
    void doChangePassword4Forget(String loginID, String newPassword, String verifyCode) throws JwBlogException;


    /**
     * 修改密码验证(忘记密码页面)<br>
     *
     * @param loginID 登录ID
     * @param email   邮箱
     * @return
     * @author gulihua
     */
    void doChangePassword4ForgetCheck(String loginID, String email) throws JwBlogException;

    /**
     * 保存修改用户编辑信息<br>
     *
     * @param adminBO adminBO
     * @return
     * @author gulihua
     */
    void doSaveEditInfo(AdminBO adminBO) throws JwBlogException;
}
