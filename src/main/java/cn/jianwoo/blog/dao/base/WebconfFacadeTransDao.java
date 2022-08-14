package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.WebconfFacade;
import cn.jianwoo.blog.exception.DaoException;

public interface WebconfFacadeTransDao extends WebconfFacadeQueryDao {
    void doInsert(WebconfFacade record) throws DaoException;

    void doInsertSelective(WebconfFacade record) throws DaoException;

    void doUpdateByPrimaryKey(WebconfFacade record) throws DaoException;

    void doUpdateByPrimaryKeySelective(WebconfFacade record) throws DaoException;

    void doDeleteByPrimaryKey(String key) throws DaoException;
}