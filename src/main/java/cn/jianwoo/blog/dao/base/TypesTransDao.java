package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Types;
import cn.jianwoo.blog.exception.DaoException;

public interface TypesTransDao extends TypesQueryDao {
    void doInsert(Types record) throws DaoException;


    void doInsertSelective(Types record) throws DaoException;


    void doUpdateByPrimaryKey(Types record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Types record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}