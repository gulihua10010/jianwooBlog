package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.exception.DaoException;

public interface VisitTransDao extends VisitQueryDao {
    void doInsert(Visit record) throws DaoException;


    void doInsertSelective(Visit record) throws DaoException;


    void doUpdateByPrimaryKey(Visit record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Visit record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}