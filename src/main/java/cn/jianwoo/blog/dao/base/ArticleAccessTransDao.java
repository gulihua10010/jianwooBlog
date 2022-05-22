package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.exception.DaoException;

public interface ArticleAccessTransDao extends ArticleAccessQueryDao {
    void doInsert(ArticleAccess record) throws DaoException;

    void doInsertSelective(ArticleAccess record) throws DaoException;

    void doUpdateByPrimaryKey(ArticleAccess record) throws DaoException;

    void doUpdateByPrimaryKeySelective(ArticleAccess record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}