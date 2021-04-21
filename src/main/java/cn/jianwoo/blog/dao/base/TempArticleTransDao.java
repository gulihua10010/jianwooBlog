package cn.jianwoo.blog.dao.base;


import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.exception.DaoException;

public interface TempArticleTransDao extends TempArticleQueryDao {
    void doInsert(TempArticle record) throws DaoException;

    void doInsertSelective(TempArticle record) throws DaoException;

    void doUpdateByPrimaryKey(TempArticle record) throws DaoException;

    void doUpdateByPrimaryKeySelective(TempArticle record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}