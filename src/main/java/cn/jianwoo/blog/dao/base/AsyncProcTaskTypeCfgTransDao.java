package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.exception.DaoException;

public interface AsyncProcTaskTypeCfgTransDao extends AsyncProcTaskTypeCfgQueryDao {
    void doInsert(AsyncProcTaskTypeCfg record) throws DaoException;

    void doInsertSelective(AsyncProcTaskTypeCfg record) throws DaoException;

    void doUpdateByPrimaryKey(AsyncProcTaskTypeCfg record) throws DaoException;

    void doUpdateByPrimaryKeySelective(AsyncProcTaskTypeCfg record) throws DaoException;

    void doDeleteByPrimaryKey(String taskType) throws DaoException;
}