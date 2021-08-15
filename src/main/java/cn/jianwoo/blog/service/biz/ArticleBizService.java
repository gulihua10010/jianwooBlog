package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleParam;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.ArticleBO;
import com.github.pagehelper.PageInfo;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ArticleBizService {


    /**
     * * 保存文章
     *
     * @param article 文章
     * @return
     * @author gulihua
     */
    void doSaveArticle(ArticleBO article) throws JwBlogException;


    /**
     * * 更新文章信息
     *
     * @param article 文章
     * @return
     * @author gulihua
     */
    void doUpdateArticleInfo(ArticleBO article) throws JwBlogException;



    /**
     * * 更新文章
     *
     * @param article 文章
     * @return
     * @author gulihua
     */
    void doUpdateArticle(ArticleBO article) throws JwBlogException;

    /**
     * 把文章移动到回收站 status = -1
     *
     * @param oid 标题 article.oid [ARTICLE.OID]
     * @return
     * @author gulihua
     */
    void doRemoveToRecycle(Long oid) throws JwBlogException;


    /**
     * 文章删除
     *
     * @param id 标题 article.oid [ARTICLE.OID]
     * @return
     * @author gulihua
     */
    void doDeleteArticle(Long id) throws JwBlogException;


    /**
     * 把文章移动到草稿 status = 0
     *
     * @param id 标题 article.oid [ARTICLE.OID]
     * @return
     * @author gulihua
     */
    void doRemoveToDraft(Long id) throws JwBlogException;


    /**
     * 把文章发布 status = 1
     *
     * @param id 标题 article.oid [ARTICLE.OID]
     * @return
     * @author gulihua
     */
    void doPublishedArticle(Long id) throws JwBlogException;

    /**
     * 获取有效文章数量 (status = 0 or status = 1)
     *
     * @return
     * @author gulihua
     */
    Integer countWithEffectiveArts();

    /**
     * 获取已发布文章数量 (status = 1)
     *
     * @return
     * @author gulihua
     */
    Integer countWithPublishArts();

    /**
     * 获取草稿文章数量 (status = 0)
     *
     * @return
     * @author gulihua
     */
    Integer countWithDraftArts();


    /**
     * 获取最近n条已发布文章 (status = 1) (通过MODIFIED_DATE desc排序)
     *
     * @param n n条
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryRecentPublishedArts(int n);


    /**
     * 获取最近n条草稿文章 (status = 0) (通过MODIFIED_DATE desc排序)
     *
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryRecentDraft(int n);


    /**
     * 获取回收站文章 (status = -1)
     *
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryRecycleBinArts();


    /**
     * 获取草稿文章 (status = -1)
     *
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryDraftArts() throws JwBlogException;


    /**
     * 从回收站恢复文章 (status = -1 -> status = 1)
     *
     * @return
     * @author gulihua
     */
    void doRestoreRecycleBinArts(Long oid) throws JwBlogException;


    /**
     * 获取已发布的文章 (status = 1)
     *
     * @return
     * @throws JwBlogException
     */
    List<ArticleBO> queryPublishedArticles() throws JwBlogException;


    /**
     * 通过关键词keyword模糊查询已发布文章 (通过MODIFIED_DATE desc排序) 如果为空， 获取已发布的文章
     *
     * @param keyword TITLE or TEXT
     * @return
     * @author gulihua
     */
    List<ArticleBO> querySearchArtsByKeyword(String keyword) throws JwBlogException;


    /**
     * 获取已发布前limit条最新发布文章（通过MODIFIED_DATE desc排序） limit 为 null， 取默认10
     *
     * @param limit 前limit条
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryNewestArts(Integer limit) throws JwBlogException;


    /**
     * 获取已发布前limit条最热文章（通过COMMENT_COUNT,PRAISE_COUNT 排序） limit 为 null， 取默认10
     *
     * @param limit 前limit条
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryHotArts(Integer limit) throws JwBlogException;


    /**
     * 获取随机文章 limit 为 null， 取默认10 文章 < limit，取全部文章
     *
     * @param limit 前limit条
     * @return
     * @author gulihua
     */
    List<ArticleBO> queryRandomArts(Integer limit) throws JwBlogException;


    /**
     * 文章添加赞
     *
     * @param artOid 文章主键 (OID)
     * @return
     * @author gulihua
     */
    void doAddPraise(Long artOid) throws JwBlogException;


    /**
     * 获取有效文章列表
     *
     * @param param 参数
     * @return
     * @author gulihua
     */
    PageInfo<ArticleBO> queryEffectiveArticleList(ArticleParam param);


    /**
     * 获取回收站文章列表
     *
     * @param param 参数
     * @return
     * @author gulihua
     */
    PageInfo<ArticleBO> queryRecycleBinArticleList(ArticleParam param);


    /**
     * 把文章列表移动进回收站
     *
     * @param oidList 文章oid集合
     * @return
     * @author gulihua
     */
    void doRemoveToRecycleBinList(List<Long> oidList) throws JwBlogException;


    /**
     * 把文章列表从回收站恢复至草稿
     *
     * @param oidList 文章oid集合
     * @return
     * @author gulihua
     */
    void doRestoreRecycleToDraftList(List<Long> oidList) throws JwBlogException;


    /**
     * 把文章列表从回收站删除
     *
     * @param oidList 文章oid集合
     * @return
     * @author gulihua
     */
    void doDeleteRecycleBinList(List<Long> oidList) throws JwBlogException;


    /**
     * 查询文章编辑信息
     *
     * @param artOid 文章oid
     * @return ArticleBO
     * @author gulihua
     */
    ArticleBO queryArticleEditInfo(Long artOid) throws JwBlogException;
}
