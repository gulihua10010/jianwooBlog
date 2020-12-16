package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.exception.DaoException;

public interface WebconfTransDao extends WebconfQueryDao {
    void doInsert(Webconf record) throws DaoException;


    void doInsertSelective(Webconf record) throws DaoException;


    void doUpdateByPrimaryKey(Webconf record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Webconf record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}