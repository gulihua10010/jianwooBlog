package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.extension.ArticleExt;
import cn.jianwoo.blog.entity.query.ArticleQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleBizMapper {

    /**
     * 通过文章状态数组统计文章数量
     *
     * @param status {@link cn.jianwoo.blog.enums.ArticleStatusEnum} [STATUS]
     * @return
     * @author gulihua
     */
    int countArtsByStatus(String[] status);


    /**
     * 通过状态查找文章前n条数据 (通过MODIFIED_DATE desc排序)
     *
     * @param paramMap status {@link cn.jianwoo.blog.enums.ArticleStatusEnum} [STATUS]
     * @param paramMap n 前n条数据
     * @return
     * @author gulihua
     */
    List<Article> selectArticleByStatusAndLimit(Map<String, Object> paramMap);


    /**
     * 通过关键词keyword模糊查询已发布文章 (通过MODIFIED_DATE desc排序)
     *
     * @param keyword TITLE or TEXT
     * @return
     * @author gulihua
     */
    List<Article> selectPublishedArtsByKeyword(String keyword);


    /**
     * 获取已发布前limit条最新发布文章（通过MODIFIED_DATE desc排序）
     *
     * @param limit 前limit条
     * @return
     * @author gulihua
     */
    List<Article> selectPublishedNewestArts(Integer limit);


    /**
     * T获取已发布前limit条最热文章（通过COMMENT_COUNT,PRAISE_COUNT 排序）
     *
     * @param limit 前limit条
     * @return
     * @author gulihua
     */
    List<Article> selectPublishedHotArts(Integer limit);


    /**
     * 获取文章列表
     *
     * @param param 参数
     * @return
     * @author gulihua
     */
    List<ArticleExt> selectArticleListByStatus(ArticleQuery param);


    /**
     * 更新文章的评论数量
     *
     * @param artOid 主键
     * @return
     * @author gulihua
     */
    int updateArticleCommentCnt(Long artOid);


    /**
     * 更新文章的赞数量
     *
     * @param artOid 主键
     * @return
     * @author gulihua
     */
    int updateArticlePraiseCnt(Long artOid);


    /**
     * 恢复文章(与调用基础transDao区别在于REMOVE_RECYCLE_TIME字段可以更新为null)<br/>
     * 文章回收站恢复时调用<br/>
     *
     * @param record ArticleWithBLOBs
     * @return
     * @author gulihua
     */
    int restoreFromRecycle(Article record);


    /**
     * 分页获取首页文章列表
     *
     * @param param     参数
     * @param isPrivate 是否博主私有ip
     * @return
     * @author gulihua
     */
    List<ArticleExt> selectArticleListMain(@Param("param") ArticleQuery param, @Param("isPrivate") boolean isPrivate);

    /**
     * 获取某月的文章列表
     *
     * @param param     参数
     * @param isPrivate 是否博主私有ip
     * @return
     * @author gulihua
     */
    List<ArticleExt> selectMonthDatePublishList(@Param("param") ArticleQuery param, @Param("isPrivate") boolean isPrivate);


    /**
     * 获取某月的文章数量
     *
     * @param param     参数
     * @param isPrivate 是否博主私有ip
     * @return
     * @author gulihua
     */
    Integer selectMonthDatePublishCount(@Param("param") ArticleQuery param, @Param("isPrivate") boolean isPrivate);


    /**
     * 根据文章OID获取推荐文章列表
     *
     * @param oid 文章oid
     * @return
     * @author gulihua
     */
    List<ArticleExt> selectRecommendArticleByArtOid(Long oid);

    /**
     * 根据文章类别ID获取推荐文章列表
     *
     * @param categoryOid 类别id
     * @return
     * @author gulihua
     */
    List<ArticleExt> selectRecommendArticleByCategoryOid(Integer categoryOid);

}