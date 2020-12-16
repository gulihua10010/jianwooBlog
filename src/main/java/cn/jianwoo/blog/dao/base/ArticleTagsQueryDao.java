package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.ArticleTags;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface ArticleTagsQueryDao {
    ArticleTags queryArticleTagsByPrimaryKey(Long oid) throws DaoException;


    List<ArticleTags> queryTagsByArtOid(Long artOid);

}