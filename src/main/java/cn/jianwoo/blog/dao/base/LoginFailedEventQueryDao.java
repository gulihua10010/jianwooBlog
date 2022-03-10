package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.LoginFailedEvent;
import cn.jianwoo.blog.exception.DaoException;

public interface LoginFailedEventQueryDao {
    LoginFailedEvent queryLoginFailedEventByPrimaryKey(Long oid) throws DaoException;

    /**
     * 查询有效的记录
     *
     * @param loginId 登录ID
     * @param ip      ip地址
     * @return
     * @author gulihua
     */
    LoginFailedEvent queryEffectiveFailed(String loginId, String ip);
}