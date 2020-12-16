package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.exception.DaoException;

public interface MenuTransDao extends MenuQueryDao {
    void doInsert(Menu record) throws DaoException;


    void doInsertSelective(Menu record) throws DaoException;


    void doUpdateByPrimaryKey(Menu record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Menu record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}