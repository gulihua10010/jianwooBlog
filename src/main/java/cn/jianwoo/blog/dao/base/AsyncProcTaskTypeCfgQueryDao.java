package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.exception.DaoException;

public interface AsyncProcTaskTypeCfgQueryDao {
    AsyncProcTaskTypeCfg queryAsyncProcTaskTypeCfgByPrimaryKey(String taskType) throws DaoException;
}