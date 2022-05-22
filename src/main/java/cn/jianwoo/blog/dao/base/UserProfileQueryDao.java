package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.exception.DaoException;

public interface UserProfileQueryDao {
    UserProfile queryUserProfileByPrimaryKey(Long oid) throws DaoException;

    /**
     * 通过username获取
     *
     * @param name 用户名
     * @return
     * @author gulihua
     */
    UserProfile queryUserProfileByUsername(String name);

    /**
     * 通过ip地址获取
     *
     * @param ip IP地址
     * @return
     * @author gulihua
     */
    UserProfile queryOneUserProfileByIp(String ip);
}