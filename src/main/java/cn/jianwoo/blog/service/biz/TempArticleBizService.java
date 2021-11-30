package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.enums.TempArticlePageEnum;
import cn.jianwoo.blog.enums.TempArticleStatusEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.TempArticleBO;

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
    void doSaveTempArticle(TempArticleBO article) throws JwBlogException;


    /**
     * * 更新临时文章
     *
     * @param article 文章
     * @return
     * @author gulihua
     */
    void doUpdateTempArticle(TempArticleBO article) throws JwBlogException;

    /**
     * * 更新临时文章状态
     *
     * @param oid        文章主键
     * @param status     文章状态
     * @param restoreOid 恢复文章的oid
     * @return
     * @author gulihua
     */
    void doUpdateTempArticleStatus(Long oid, TempArticleStatusEnum status, Long restoreOid) throws JwBlogException;

    /**
     * 根据编辑文章的oid和类型查询
     *
     * @param oldArticleOid 编辑文章的oid
     * @param pageType      {@link TempArticlePageEnum}
     * @return
     * @author gulihua
     */
    TempArticleBO queryLastestTempArticle(Long oldArticleOid, String pageType);
}
