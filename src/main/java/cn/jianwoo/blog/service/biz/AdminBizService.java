package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.JwBlogException;
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
    boolean register(@NonNull String name, @NonNull String password) throws JwBlogException;


    /**
     * 登录
     *
     * @param name     用户名
     * @param password 密码
     * @param ip       ip
     * @return
     * @author gulihua
     */
    Admin login(@NonNull String name, @NonNull String password, @NonNull String ip) throws JwBlogException;
}
