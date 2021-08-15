package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.exception.DaoException;

public interface ArticleTransDao extends ArticleQueryDao {
    void doInsert(ArticleWithBLOBs record) throws DaoException;


    void doInsertSelective(ArticleWithBLOBs record) throws DaoException;


    void doUpdateByPrimaryKey(Article record) throws DaoException;


    void doUpdateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record) throws DaoException;


    void doUpdateByPrimaryKeySelective(ArticleWithBLOBs record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}