package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.exception.DaoException;

public interface BizEventLogTransDao extends BizEventLogQueryDao {
    void doInsert(BizEventLog record) throws DaoException;

    void doInsertSelective(BizEventLog record) throws DaoException;

    void doUpdateByPrimaryKey(BizEventLog record) throws DaoException;

    void doUpdateByPrimaryKeySelective(BizEventLog record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}