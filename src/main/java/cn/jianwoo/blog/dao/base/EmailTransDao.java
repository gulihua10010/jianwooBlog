package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.exception.DaoException;

public interface EmailTransDao extends EmailQueryDao {
    void doInsert(Email record) throws DaoException;

    void doInsertSelective(Email record) throws DaoException;

    void doUpdateByPrimaryKey(Email record) throws DaoException;

    void doUpdateByPrimaryKeySelective(Email record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}