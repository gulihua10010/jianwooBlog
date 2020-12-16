package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface ArticleQueryDao {
    Article queryArticleByPrimaryKey(Long oid) throws DaoException;


    /**
     * 通过状态 查询文章
     *
     * @param status {@link cn.jianwoo.blog.enums.ArticleStatusEnum}
     * @return
     * @author gulihua
     */
    List<Article> queryArticleByStatus(Integer status);


    /**
     * 通过类别id 查询文章 *
     *
     * @param typeId Menu主键
     * @return
     * @author gulihua
     */
    List<Article> queryArticleByType(Integer typeId);
}