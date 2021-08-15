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
}
