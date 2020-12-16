package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.exception.DaoException;

public interface CommentTransDao extends CommentQueryDao {
    void doInsert(Comment record) throws DaoException;


    void doInsertSelective(Comment record) throws DaoException;


    void doUpdateByPrimaryKey(Comment record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Comment record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}