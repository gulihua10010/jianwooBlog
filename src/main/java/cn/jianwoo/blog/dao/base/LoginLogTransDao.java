package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.exception.DaoException;

public interface LoginLogTransDao extends LoginLogQueryDao {
    void doInsert(LoginLog record) throws DaoException;

    void doInsertSelective(LoginLog record) throws DaoException;

    void doUpdateByPrimaryKey(LoginLog record) throws DaoException;

    void doUpdateByPrimaryKeySelective(LoginLog record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}