package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentParam;
import cn.jianwoo.blog.exception.JwBlogException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentBizService {
    /**
     * 通过文章oid（articleOid）获取评论数量
     *
     * @param artOid 文章oid [ARTICLE.ARTICLE_OID]
     * @return int
     * @author gulihua
     */
    int countCommentsByArt(Long artOid);

    /**
     * 获取评论总数量
     *
     * @return int
     * @author gulihua
     */
    int countAllComments();

    /**
     * 获取最近limit条记录的评论 （通过UPDATE_DATE desc排序）
     *
     * @param limit limit
     * @return List<CommentExt>
     * @author gulihua
     */
    List<CommentExt> queryRecentComments(Integer limit);


    /**
     * 统计未读评论数据 (ART_DEL!=-1 AND IS_READ=0)
     *
     * @return int
     * @author gulihua
     */
    int countWithUnreadComm();


    /**
     * 添加评论
     *
     * @param artOid    文章oid [ARTICLE.ARTICLE_OID]
     * @param username  用户名
     * @param ip        ip
     * @param content   评论内容
     * @param parentOid parentOid父评论id [COMMENTS.PARENT]
     * @param qq        qq
     * @param headerImg 头像路径
     * @return
     * @author gulihua
     */
    void doAddComment(Long artOid, String username, String ip, String content, Long parentOid, String qq,
                      String headerImg) throws JwBlogException;


    /**
     * 通过文章oid请求评论
     *
     * @param artOid 文章oid [ARTICLE.ARTICLE_OID]
     * @return
     * @author gulihua
     */
    List<CommentExt> queryCommentsByArtOid(Long artOid);


    /**
     * 通过父评论获取子评论
     *
     * @param parentOid 父评论id [COMMENTS.PARENT]
     * @return
     * @author gulihua
     */
    List<Comment> queryReplyCommentsByParentOid(Long parentOid);


    /**
     * 评论添加赞
     *
     * @param oid 主键[COMMENTS.OID]
     * @return
     * @author gulihua
     */
    void doAddCommentPraise(Long oid) throws JwBlogException;


    /**
     * 获取最近的评论的主键
     *
     * @return
     * @author gulihua
     */
    Long queryLastCommentOid();


    /**
     * 通过oid删除评论
     *
     * @param oid 主键[COMMENTS.OID]
     * @return
     * @author gulihua
     */
    void doDelCommentById(Long oid) throws JwBlogException;


    /**
     * 获取所有有效评论
     *
     * @return List<CommentExt>
     * @author gulihua
     */
    @Deprecated
    List<CommentExt> queryAllEffectiveComment(CommentParam param);


    /**
     * 获取所有有效评论
     *
     * @param param 参数
     * @return PageInfo<CommentExt>
     * @author gulihua
     */
    PageInfo<CommentExt> queryAllCommentPage(CommentParam param);


    /**
     * 通过oid list删除评论
     *
     * @param oidList 主键list[COMMENTS.OID]
     * @return
     * @author gulihua
     */
    void doDelCommentByListOid(List<Long> oidList) throws JwBlogException;


    /**
     * 根据评论oid获取评论
     *
     * @param oid 评论oid
     * @return CommentExt
     * @author gulihua
     */
    CommentExt queryCommentExtByOid(Long oid);


    /**
     * 通过oid 标记已读
     *
     * @param oid 主键list[COMMENTS.OID]
     * @return
     * @author gulihua
     */
    void doUpdateReadByOid(Long oid) throws JwBlogException;


    /**
     * 通过oid list 批量标记已读
     *
     * @param oidList 主键list[COMMENTS.OID]
     * @return
     * @author gulihua
     */
    void doUpdateReadByOidList(List<Long> oidList) throws JwBlogException;

}
