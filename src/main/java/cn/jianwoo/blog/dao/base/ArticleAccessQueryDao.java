package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.exception.DaoException;

public interface ArticleAccessQueryDao {
    ArticleAccess queryArticleAccessByPrimaryKey(Long oid) throws DaoException;


    /**
     *
     * 通过文章主键和ip查询
     *
     * @author gulihua
     * @param artOid 文章主键
     * @param ip IP地址
     * @return
     */
    ArticleAccess queryArticleAccessByArtOidAndIp(Long artOid, String ip);
}