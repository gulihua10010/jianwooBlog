package cn.jianwoo.blog.dao.biz.mapper;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentParam;

import java.util.List;

public interface CommentBizMapper {

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
    List<CommentExt> selectRecentComments(Integer limit);


    /**
     * 获取最后一条评论 (OID desc 排序)
     *
     * @return Comment Comment
     * @author gulihua
     */
    Comment selectLastComment();


    /**
     * 通过文章oid获取评论（带文章标题）
     *
     * @param artOid 文章oid
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> selectCommentsExtByArticleOid(Long artOid);


    /**
     * 获取所有评论（带文章标题）
     *
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> selectAllCommentsExt(CommentParam param);


    /**
     * 根据评论oid获取评论
     *
     * @param oid 评论oid
     * @return CommentExt
     * @author gulihua
     */
    CommentExt selectCommentExtByOid(Long oid);

}