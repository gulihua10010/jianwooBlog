package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.UserTmpBO;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-17 12:03
 */
public interface UserBizService {


    /**
     * 创建或者更新用户
     *
     * @param ip        用户IP地址
     * @param userTmpBO 用户临时对象
     * @return
     * @author gulihua
     */
    void doCreateOrUpdateUser(String ip, UserTmpBO userTmpBO) throws JwBlogException;


    /**
     * 创建或者更新用户
     *
     * @param ip 用户IP地址
     * @return
     * @author gulihua
     */
    void doCreateOrUpdateUser(String ip) throws JwBlogException;
}
