package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.dao.biz.CommentBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.extension.ReplyCommentsExt;
import cn.jianwoo.blog.entity.query.CommentParam;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.CommentBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.task.AsyncTask;
import cn.jianwoo.blog.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentBizServiceImpl implements CommentBizService {

    @Autowired
    private CommentBizDao commentBizDao;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private CommentTransDao commentTransDao;
    @Autowired
    private AsyncTask asyncTask;


    @Override
    public int countCommentsByArt(Long artOid) {
        if (artOid == null) {
            return 0;
        }
        return commentBizDao.countWithCommentByArt(artOid);
    }


    @Override
    public int countAllComments() {
        return commentBizDao.countAllComments();
    }


    @Override
    public List<CommentExt> queryRecentComments(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        return commentBizDao.queryRecentComments(limit);
    }


    @Override
    public int countWithUnreadComm() {
        return commentBizDao.countWithUnreadComm();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddComment(Long artOid, String username, String ip, String content, Long parentOid, String qq,
                             String headerImg) throws JwBlogException {
        Date now = DateUtil.getNow();

        Comment comment = JwBuilder.of(Comment::new)
                .with(Comment::setArtDel, 0)
                .with(Comment::setArticleOid, artOid)
                .with(Comment::setContent, content)
                .with(Comment::setPraiseCount, 0L)
                .with(Comment::setHeadImg, headerImg)
                .with(Comment::setIp, ip)
                .with(Comment::setArea, Constants.UNKNOW)
                .with(Comment::setIsRead, CommReadEnum.UNREAD.getValue())
                .with(Comment::setParent, parentOid)
                .with(Comment::setQq, qq)
                .with(Comment::setUser, username)
                .with(Comment::setDate, now)
                .with(Comment::setCreateDate, now)
                .with(Comment::setUpdateDate, now)
                .build();

        try {
            commentTransDao.doInsert(comment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw CommentBizException.CREATE_FAILED_EXCEPTION.format("artOid:" + artOid).print();

        }
        Article article = null;
        try {
            article = articleTransDao.queryArticleByPrimaryKey(artOid);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw ArticleBizException.NOT_EXIST_EXCEPTION.format(artOid).print();

        }
        article.setCommentCount((article.getCommentCount() == null ? 0 : article.getCommentCount()) + 1);
        try {
            articleTransDao.doUpdateByPrimaryKeySelective(article);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(artOid).print();
        }

        //执行异步任务
        asyncTask.execCommentIpAreaTask(comment.getOid());

    }

    @Override
    public List<CommentExt> queryCommentsByArtOid(Long artOid) {
        List<CommentExt> artComments = commentBizDao.queryCommentsExtByArticleOid(artOid);
        return processCommentWithLevel(artComments);
    }

    /**
     * 根据comment list处理评论 把评论文章的顶级评论放在 CommentExt 对象中<br/>
     * ReplyCommentsExt是顶级评论的所有回复评论
     * <p>
     * eg:<br/>
     * 文章评论a<br/>
     * --a的回复评论1<br/>
     * --a的回复评论2<br/>
     * --a的回复评论3<br/>
     * 文章评论b<br/>
     * --b的回复评论4<br/>
     * --b的回复评论5<br/>
     * --b的回复评论6<br/>
     *
     * @param artComments 根据文章oid获取的评论list
     * @return
     * @author gulihua
     */
    private List<CommentExt> processCommentWithLevel(List<CommentExt> artComments) {
        List<CommentExt> comments = new ArrayList<>();
        Map<Long, List<CommentExt>> commentGroup = new HashMap<>();
        if (!CollectionUtils.isEmpty(artComments)) {
            Map<Long, Comment> commentMap = artComments.stream()
                    .collect(Collectors.toMap(Comment::getOid, a -> a, (k1, k2) -> k1));
            for (CommentExt c : artComments) {
                CommentExt commentExt = new CommentExt();
                BeanUtils.copyProperties(c, commentExt);
                if (c.getParent() == 0L) {
                    comments.add(commentExt);
                }
                if (!commentGroup.containsKey(c.getParent())) {
                    List<CommentExt> c1 = new ArrayList<>();
                    c1.add(commentExt);
                    commentGroup.put(c.getParent(), c1);
                } else {
                    commentGroup.get(c.getParent()).add(commentExt);
                }
            }
            List<ReplyCommentsExt> subs = new ArrayList<>();
            processComment(comments, commentGroup, subs, commentMap);
        }

        return comments;
    }

    /**
     * 递归处理评论
     *
     * @param comments     父级评论，根据父级评论会递归获取子评论，遍历子评论，再获取子评论，知道获取不到，即没有子评论
     * @param commentGroup 根据父评论Parent进行分的组
     * @param subComments  子评论，递归叠加的所有顶级评论的子评论list，当没有子评论，new 新的对象
     * @param commentMap   根据主键oid进行分的组
     * @return
     * @author gulihua
     */
    private void processComment(List<CommentExt> comments, Map<Long, List<CommentExt>> commentGroup,
                                List<ReplyCommentsExt> subComments, Map<Long, Comment> commentMap) {
        for (CommentExt c : comments) {
            if (commentGroup.containsKey(c.getOid())) {
                List<CommentExt> subs = commentGroup.get(c.getOid());
                processComment(subs, commentGroup, subComments, commentMap);
                if (c.getParent() == 0L) {
                    c.setReplyComments(subComments);
                    subComments = new ArrayList<>();
                }

            }
            if (c.getParent() != 0L) {
                if (subComments == null) {
                    subComments = new ArrayList<>();
                }
                ReplyCommentsExt replyCommentsExt = new ReplyCommentsExt();
                BeanUtils.copyProperties(c, replyCommentsExt);
                Comment comment = commentMap.get(c.getParent());
                replyCommentsExt.setParentUserName(comment.getUser());
                subComments.add(replyCommentsExt);
            }
        }

    }
//

    @Override
    public List<Comment> queryReplyCommentsByParentOid(Long parentOid) {
        return commentTransDao.queryCommentByParentOid(parentOid);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddCommentPraise(Long oid) throws JwBlogException {
        Comment comment = null;
        try {
            comment = commentTransDao.queryCommentByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddCommentPraise exec failed, e:\n", e);
            throw CommentBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
        comment.setPraiseCount(comment.getPraiseCount() + 1);

        try {
            commentTransDao.doUpdateByPrimaryKey(comment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddCommentPraise exec failed, e:\n", e);
            throw CommentBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public Long queryLastCommentOid() {
        Comment comment = commentBizDao.queryLastComment();
        return comment.getOid();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doDelCommentById(Long oid) throws JwBlogException {
        List<Comment> commentList = new ArrayList<>();
//       processCommentWithLevel(artComments);
        queryAllSubComments(oid, commentList);
        if (!CollectionUtils.isEmpty(commentList)) {
            for (Comment sub : commentList) {
                try {
                    commentTransDao.doDeleteByPrimaryKey(sub.getOid());
                } catch (DaoException e) {
                    log.warn("CommentBizServiceImpl.doDelCommentById deleted failed, oid={}", sub.getOid());
                }
            }
        }
        try {
            commentTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.warn("CommentBizServiceImpl.doDelCommentById deleted failed, oid={}", oid);

        }

    }


    @Override
    @Deprecated
    public List<CommentExt> queryAllEffectiveComment(CommentParam param) {
        List<CommentExt> commentList = commentBizDao.queryAllCommentsExt(param);
        List<CommentExt> commentExtList = processCommentWithLevel(commentList);
        // 处理 commentExtList ，放在list里
        List<CommentExt> newCommentList = new ArrayList<>();
        for (CommentExt commentExt : commentExtList) {
            newCommentList.add(commentExt);
            if (!CollectionUtils.isEmpty(commentExt.getReplyComments())) {
                for (ReplyCommentsExt replyCommentsExt : commentExt.getReplyComments()) {
                    CommentExt tmp = new CommentExt();
                    BeanUtils.copyProperties(replyCommentsExt, tmp);
                    tmp.setTitle(commentExt.getTitle());
                    tmp.setParentUserName(replyCommentsExt.getParentUserName());
                    newCommentList.add(tmp);
                }
            }
        }
        return newCommentList;
    }


    @Override
    public PageInfo<CommentExt> queryAllCommentPage(CommentParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<CommentExt> commentList = commentBizDao.queryAllCommentsExt(param);
        PageInfo<CommentExt> pageInfo = new PageInfo<>(commentList);
        return pageInfo;
    }


    @Override
    public void doDelCommentByListOid(List<Long> oidList) throws JwBlogException {
        for (Long oid : oidList) {
            doDelCommentById(oid);
        }

    }


    @Override
    public CommentExt queryCommentExtByOid(Long oid) {
        return commentBizDao.queryCommentExtByOid(oid);
    }


    @Override
    public void doUpdateReadByOid(Long oid) throws JwBlogException {
        Comment comment = new Comment();
        comment.setOid(oid);
        comment.setIsRead(CommReadEnum.READ.getValue());
        try {
            commentTransDao.doUpdateByPrimaryKeySelective(comment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doUpdateReadByOid exec failed, e:\n", e);
            throw CommentBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    public void doUpdateReadByOidList(List<Long> oidList) throws JwBlogException {
        if (!CollectionUtils.isEmpty(oidList)) {
            for (Long oid : oidList) {
                doUpdateReadByOid(oid);
            }
        }
    }


    /**
     * 根据子评论oid获取顶级评论
     *
     * @param subComment 子评论
     * @param commentMap 根据主键oid进行分的组
     * @return
     * @author gulihua
     */
    private Comment queryTopComment(Comment subComment, Map<Long, Comment> commentMap) {
        Comment comment = commentMap.get(subComment.getParent());
        if (comment != null) {
            return queryTopComment(comment, commentMap);
        } else {
            if (subComment.getParent().equals(0L)) {
                return subComment;
            }
        }
        return null;
    }


    /**
     * 递归获取父评论的所有子评论
     *
     * @param parentOid   父评论oid
     * @param commentList 子评论 list
     * @return
     * @author gulihua
     */
    private void queryAllSubComments(Long parentOid, List<Comment> commentList) {
        if (null == commentList) {
            commentList = new ArrayList<>();
        }
        List<Comment> subCommList = queryReplyCommentsByParentOid(parentOid);
        if (!CollectionUtils.isEmpty(subCommList)) {
            for (Comment sub : subCommList) {
                queryAllSubComments(sub.getOid(), commentList);
            }
            commentList.addAll(subCommList);
        }

    }

}