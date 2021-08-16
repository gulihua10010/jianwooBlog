package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.dao.biz.CommentBizDao;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentQuery;
import cn.jianwoo.blog.enums.ArticleDelStatusEnum;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.CommentBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.service.param.CommentParam;
import cn.jianwoo.blog.task.AsyncTask;
import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
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
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentBizServiceImpl implements CommentBizService {

    @Autowired
    private CommentBizDao commentBizDao;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private ArticleBizDao articleBizDao;
    @Autowired
    private CommentTransDao commentTransDao;
    @Autowired
    private AsyncTask asyncTask;
    private static final Long TOP_PARENT_OID = 0L;


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
    public List<CommentBO> queryRecentComments(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        List<CommentExt> commentExtList = commentBizDao.queryRecentComments(limit);
        List<CommentBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(commentExtList)) {
            CommentBO bo = new CommentBO();
            commentExtList.forEach(o -> {
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }


    @Override
    public int countWithUnreadComm() {
        return commentBizDao.countWithUnreadComm();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddComment(CommentBO bo) throws JwBlogException {
        Date now = DateUtil.getNow();

        Comment comment = JwBuilder.of(Comment::new)
                .with(Comment::setArtDelStauts, ArticleDelStatusEnum.NOT_REMOVE.getValue())
                .with(Comment::setArticleOid, bo.getArticleOid())
                .with(Comment::setContent, bo.getContent())
                .with(Comment::setPraiseCount, 0L)
                .with(Comment::setHeadImgSrc, bo.getHeadImgSrc())
                .with(Comment::setClientIp, bo.getClientIp())
                .with(Comment::setUserArea, Constants.UNKNOW)
                .with(Comment::setReadStatus, CommReadEnum.UNREAD.getValue())
                .with(Comment::setParentOid, bo.getParentOid())
                .with(Comment::setContactQq, bo.getContactQq())
                .with(Comment::setContactWechat, bo.getContactWechat())
                .with(Comment::setContactWeibo, bo.getContactWeibo())
                .with(Comment::setContactTel, bo.getContactTel())
                .with(Comment::setUserName, bo.getUserName())
                .with(Comment::setCommentTime, now)
                .with(Comment::setCreateTime, now)
                .with(Comment::setUpdateTime, now)
                .build();

        try {
            commentTransDao.doInsertSelective(comment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw CommentBizException.CREATE_FAILED_EXCEPTION.format("artOid : " + bo.getArticleOid()).print();

        }


        try {
            articleBizDao.doUpdateArticleCommentCnt(bo.getArticleOid());
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(bo.getArticleOid()).print();
        }

        //执行异步任务
        asyncTask.execCommentIpAreaTask(comment.getOid());

    }

    @Override
    public List<CommentBO> queryCommentsByArtOid(Long artOid) {
        List<CommentExt> artComments = commentBizDao.queryCommentsExtByArticleOid(artOid);
        List<CommentBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(artComments)) {
            artComments.forEach(o -> {
                CommentBO bo = new CommentBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        log.info(">>query comment Dao Data by article oid[{}]: {}", artOid, JSON.toJSONString(list));
        return processCommentWithLevel(list);
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
    private List<CommentBO> processCommentWithLevel(List<CommentBO> artComments) {
        List<CommentBO> comments = new ArrayList<>();
        Map<Long, List<CommentBO>> commentGroup = new HashMap<>();
        if (!CollectionUtils.isEmpty(artComments)) {
            Map<Long, CommentBO> commentMap = artComments.stream()
                    .collect(Collectors.toMap(CommentBO::getOid, a -> a, (k1, k2) -> k1));
            for (CommentBO c : artComments) {
                CommentBO commentExt = new CommentBO();
                BeanUtils.copyProperties(c, commentExt);
                if (Objects.equals(c.getParentOid(), TOP_PARENT_OID)) {
                    comments.add(commentExt);
                }
                if (!commentGroup.containsKey(c.getParentOid())) {
                    List<CommentBO> commList = new ArrayList<>();
                    commList.add(commentExt);
                    commentGroup.put(c.getParentOid(), commList);
                } else {
                    commentGroup.get(c.getParentOid()).add(commentExt);
                }
            }
            List<CommentBO> subs = new ArrayList<>();
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
    private void processComment(List<CommentBO> comments, Map<Long, List<CommentBO>> commentGroup,
                                List<CommentBO> subComments, Map<Long, CommentBO> commentMap) {
        for (CommentBO c : comments) {
            if (commentGroup.containsKey(c.getOid())) {
                List<CommentBO> subs = commentGroup.get(c.getOid());
                processComment(subs, commentGroup, subComments, commentMap);
                if (Objects.equals(c.getParentOid(), TOP_PARENT_OID)) {
                    c.setReplyComments(subComments);
                    subComments = new ArrayList<>();
                }

            }
            if (!Objects.equals(c.getParentOid(), TOP_PARENT_OID)) {
                if (subComments == null) {
                    subComments = new ArrayList<>();
                }
                CommentBO replyCommentsExt = new CommentBO();
                BeanUtils.copyProperties(c, replyCommentsExt);
                CommentBO comment = commentMap.get(c.getParentOid());
                replyCommentsExt.setParentUserName(comment.getUserName());
                subComments.add(replyCommentsExt);
            }
        }

    }
//


    private List<Comment> queryReplyCommentsByParentOid(Long parentOid) {
        return commentTransDao.queryCommentByParentOid(parentOid);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddCommentPraise(Long oid) throws JwBlogException {
        try {
            commentBizDao.doUpdateCommentPraiseCnt(oid);
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
    public List<CommentBO> queryAllEffectiveComment(CommentParam param) {
        CommentQuery query = new CommentQuery();
        BeanUtils.copyProperties(param, query);
        List<CommentExt> commentList = commentBizDao.queryAllCommentsExt(query);
        List<CommentBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(commentList)) {
            CommentBO bo = new CommentBO();
            commentList.forEach(o -> {
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        List<CommentBO> commentExtList = processCommentWithLevel(list);
        // 处理 commentExtList ，放在list里
        List<CommentBO> newCommentList = new ArrayList<>();
        for (CommentBO commentExt : commentExtList) {
            newCommentList.add(commentExt);
            if (!CollectionUtils.isEmpty(commentExt.getReplyComments())) {
                for (CommentBO replyCommentsExt : commentExt.getReplyComments()) {
                    CommentBO tmp = new CommentBO();
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
    public PageInfo<CommentBO> queryAllCommentPage(CommentParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        CommentQuery query = new CommentQuery();
        BeanUtils.copyProperties(param, query);
        List<CommentExt> commentList = commentBizDao.queryAllCommentsExt(query);
        List<CommentBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(commentList)) {
            CommentBO bo = new CommentBO();
            commentList.forEach(o -> {
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<CommentBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doDelCommentByListOid(List<Long> oidList) throws JwBlogException {
        for (Long oid : oidList) {
            doDelCommentById(oid);
        }

    }


    @Override
    public CommentBO queryCommentExtByOid(String oid) throws JwBlogException {
        try {
            CommentExt commentExt = commentBizDao.queryCommentExtByOid(Long.parseLong(oid));
            CommentBO bo = new CommentBO();
            BeanUtils.copyProperties(commentExt, bo);
            return bo;
        } catch (Exception e) {
            throw CommentBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();

        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateReadByOid(Long oid) throws JwBlogException {
        Comment comment = new Comment();
        comment.setOid(oid);
        comment.setReadStatus(CommReadEnum.READ.getValue());
        try {
            commentTransDao.doUpdateByPrimaryKeySelective(comment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doUpdateReadByOid exec failed, e:\n", e);
            throw CommentBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
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
        Comment comment = commentMap.get(subComment.getParentOid());
        if (comment != null) {
            return queryTopComment(comment, commentMap);
        } else {
            if (subComment.getParentOid().equals(TOP_PARENT_OID)) {
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