package cn.jianwoo.blog.dao.base;


import cn.jianwoo.blog.entity.TempArticle;
import cn.jianwoo.blog.exception.DaoException;

public interface TempArticleQueryDao {
    TempArticle queryTempArticleByPrimaryKey(Long oid) throws DaoException;

    /**
     * 查询最新的缓存临时文章
     *
     * @return
     * @author gulihua
     */
    TempArticle queryLastestTempArticle(Long oldArticleOid, String pageType);
}