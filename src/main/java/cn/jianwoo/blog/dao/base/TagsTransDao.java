package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Tags;
import cn.jianwoo.blog.exception.DaoException;

public interface TagsTransDao extends TagsQueryDao {
    void doInsert(Tags record) throws DaoException;


    void doInsertSelective(Tags record) throws DaoException;


    void doUpdateByPrimaryKey(Tags record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Tags record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}