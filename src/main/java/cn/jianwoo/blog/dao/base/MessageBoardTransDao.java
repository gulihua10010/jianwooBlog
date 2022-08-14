package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.exception.DaoException;

public interface MessageBoardTransDao extends MessageBoardQueryDao {
    void doInsert(MessageBoard record) throws DaoException;

    void doInsertSelective(MessageBoard record) throws DaoException;

    void doUpdateByPrimaryKey(MessageBoard record) throws DaoException;

    void doUpdateByPrimaryKeySelective(MessageBoard record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}