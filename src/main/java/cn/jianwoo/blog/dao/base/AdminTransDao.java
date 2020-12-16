package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.DaoException;

public interface AdminTransDao extends AdminQueryDao {
    void doInsert(Admin record) throws DaoException;


    void doInsertSelective(Admin record) throws DaoException;


    void doUpdateByPrimaryKey(Admin record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Admin record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}