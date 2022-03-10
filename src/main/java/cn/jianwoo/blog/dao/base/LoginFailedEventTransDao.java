package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.LoginFailedEvent;
import cn.jianwoo.blog.exception.DaoException;

public interface LoginFailedEventTransDao extends LoginFailedEventQueryDao {
    void doInsert(LoginFailedEvent record) throws DaoException;

    void doInsertSelective(LoginFailedEvent record) throws DaoException;

    void doUpdateByPrimaryKey(LoginFailedEvent record) throws DaoException;

    void doUpdateByPrimaryKeySelective(LoginFailedEvent record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}