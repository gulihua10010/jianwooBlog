package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.exception.DaoException;

public interface BizPraiseTransDao extends BizPraiseQueryDao {
    void doInsert(BizPraise record) throws DaoException;

    void doInsertSelective(BizPraise record) throws DaoException;

    void doUpdateByPrimaryKey(BizPraise record) throws DaoException;

    void doUpdateByPrimaryKeySelective(BizPraise record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}