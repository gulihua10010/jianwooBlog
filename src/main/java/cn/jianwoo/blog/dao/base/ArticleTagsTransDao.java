package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.exception.DaoException;

public interface ArticleTagsTransDao extends ArticleTagsQueryDao {
    void doInsert(ArticleTags record) throws DaoException;


    void doInsertSelective(ArticleTags record) throws DaoException;


    void doUpdateByPrimaryKey(ArticleTags record) throws DaoException;


    void doUpdateByPrimaryKeySelective(ArticleTags record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;


    void doDeleteByArticleOid(Long artOid) throws DaoException;
}