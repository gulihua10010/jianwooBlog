package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.UserTmpBO;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-13 20:10
 */
public interface UserBaseService {


    /**
     * 通过username查询
     *
     * @param username 用户名
     * @return
     * @author gulihua
     */
    UserProfile queryUserByUsername(String username);


    /**
     * 创建用户
     *
     * @param userProfile 用户
     * @return
     * @author gulihua
     */
    void doCreateUser(UserProfile userProfile) throws JwBlogException;


    /**
     * 更新用户
     *
     * @param userProfile 用户
     * @return
     * @author gulihua
     */
    void doUpdateUser(UserProfile userProfile) throws JwBlogException;


    /**
     * 创建用户
     *
     * @param ip 用户IP地址
     * @return
     * @author gulihua
     */
    void doCreateUser(String ip) throws JwBlogException;


    /**
     * 创建用户
     *
     * @param ip        用户IP地址
     * @param userTmpBO 用户临时对象
     * @return
     * @author gulihua
     */
    void doCreateUser(String ip, UserTmpBO userTmpBO) throws JwBlogException;


    /**
     * 更新用户
     *
     * @param ip        用户IP地址
     * @param userTmpBO 用户临时对象
     * @return
     * @author gulihua
     */
    void doUpdateUser(String ip, UserTmpBO userTmpBO) throws JwBlogException;
}
