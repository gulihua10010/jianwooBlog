package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.ArticleBO;

/**
 * @author GuLihua
 * @Description
 * @date 2021-04-21 10:45
 */
public interface TempArticleBizService {

    /**
     * * 临时保存文章
     *
     * @param article 文章
     * @return
     * @author gulihua
     */
    void doSaveTempArticle(ArticleBO article) throws JwBlogException;


    /**
     * * 更新临时文章
     *
     * @param article 文章
     * @return
     * @author gulihua
     */
    void doUpdateTempArticle(ArticleBO article) throws JwBlogException;

    /**
     * * 更新临时文章状态
     *
     * @param oid    文章主键
     * @param status 文章状态
     * @return
     * @author gulihua
     */
    void doUpdateTempArticleStatus(Long oid, ArticleStatusEnum status) throws JwBlogException;
}
