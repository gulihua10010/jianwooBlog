package cn.jianwoo.blog.dao.biz;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentPageQuery;
import cn.jianwoo.blog.entity.query.CommentQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface CommentBizDao {

    /**
     * 通过文章oid（articleOid）获取评论数量
     *
     * @param articleOid 文章oid [ARTICLE.ARTICLE_OID]
     * @return int
     * @author gulihua
     */
    int countWithCommentByArt(Long articleOid);

    /**
     * 获取评论总数量
     *
     * @return int
     * @author gulihua
     */
    int countAllComments();

    /**
     * 统计未读评论数据 (ART_DEL!=-1 AND IS_READ=0)
     *
     * @return int
     * @author gulihua
     */
    int countWithUnreadComm();


    /**
     * 获取最近limit条记录的评论 （通过UPDATE_DATE desc排序）
     *
     * @param limit limit
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> queryRecentComments(Integer limit);


    /**
     * 获取最后一条评论 (OID desc 排序)
     *
     * @return Comment Comment
     * @author gulihua
     */
    Comment queryLastComment();


    /**
     * 通过文章oid分页获取评论（带文章标题）(首页评论)
     *
     * @param query 查询参数
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> queryCommentsPageListByArticleOid(CommentPageQuery query);


    /**
     * 通过文章oid查询总记录数(首页评论)
     *
     * @param query 查询参数
     * @return Integer
     * @author gulihua
     */
    Long queryCommentsCountByArticleOid(CommentPageQuery query);


    /**
     * 通过文章oid查询根评论记录数(首页评论)
     *
     * @param query 查询参数
     * @return Integer
     * @author gulihua
     */
    Long queryCommentsRootCountByArticleOid(CommentPageQuery query);


    /**
     * 通过文章oid获取评论（带文章标题）(后台管理页面)
     *
     * @param artOid 文章oid
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> queryCommentsExtByArticleOid(Long artOid);


    /**
     * 获取所有评论（带文章标题）
     *
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> queryAllCommentsExt(CommentQuery param);


    /**
     * 根据评论oid获取评论
     *
     * @param oid 评论oid
     * @return CommentExt
     * @author gulihua
     */
    CommentExt queryCommentExtByOid(Long oid) throws DaoException;


    /**
     * 更新评论的赞数量
     *
     * @param oid 主键
     * @return
     * @author gulihua
     */
    void doUpdateCommentPraiseCnt(Long oid) throws DaoException;


    /**
     * 更新评论回复数量
     *
     * @param oid 主键
     * @param optType 操作类型(10:创建,40:删除)
     * @return
     * @author gulihua
     */
    void doUpdateCommentReplyCnt(Long oid, String optType) throws DaoException;

    /**
     * 更新评论回复总数量
     *
     * @param oid 主键
     * @param optType 操作类型(10:创建,40:删除)
     * @return
     * @author gulihua
     */
    void doUpdateCommentTotalReplyCnt(Long oid, String optType) throws DaoException;
}