package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.exception.DaoException;

public interface ArticleTransDao extends ArticleQueryDao {
    void doInsert(Article record) throws DaoException;


    void doInsertSelective(Article record) throws DaoException;


    void doUpdateByPrimaryKey(Article record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Article record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}