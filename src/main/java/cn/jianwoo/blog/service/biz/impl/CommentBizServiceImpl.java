package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.ArticleTransDao;
import cn.jianwoo.blog.dao.base.BizPraiseTransDao;
import cn.jianwoo.blog.dao.base.CommentTransDao;
import cn.jianwoo.blog.dao.base.UserProfileTransDao;
import cn.jianwoo.blog.dao.biz.ArticleBizDao;
import cn.jianwoo.blog.dao.biz.CommentBizDao;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.entity.extension.CommentExt;
import cn.jianwoo.blog.entity.query.CommentPageQuery;
import cn.jianwoo.blog.entity.query.CommentQuery;
import cn.jianwoo.blog.enums.ArticleDelStatusEnum;
import cn.jianwoo.blog.enums.ArticleStatusEnum;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.enums.MsgTypeEnum;
import cn.jianwoo.blog.enums.PraiseTypeEnum;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.ArticleBizException;
import cn.jianwoo.blog.exception.BizPraiseBizException;
import cn.jianwoo.blog.exception.CommentBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.ArticleBaseService;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.base.CommentBaseService;
import cn.jianwoo.blog.service.base.MsgBaseService;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.biz.CommentBizService;
import cn.jianwoo.blog.service.biz.UserBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.service.bo.CommentBO;
import cn.jianwoo.blog.service.bo.CommentMainPageListBO;
import cn.jianwoo.blog.service.bo.FrequencyBO;
import cn.jianwoo.blog.service.bo.UserInfoBO;
import cn.jianwoo.blog.service.bo.UserTmpBO;
import cn.jianwoo.blog.service.param.CommentMainParam;
import cn.jianwoo.blog.service.param.CommentParam;
import cn.jianwoo.blog.task.bo.TaskDataD0020BO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CommentTransDao commentTransDao;

    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private WebconfBizService webconfBizService;
    @Autowired
    private UserBizService userBizService;
    @Autowired
    private BizPraiseTransDao bizPraiseTransDao;

    @Autowired
    private ArticleBaseService articleBaseService;
    @Autowired
    private CommentBaseService commentBaseService;

    @Autowired
    private MsgBaseService msgBaseService;
    @Autowired
    private ArticleTransDao articleTransDao;
    @Autowired
    private ArticleBizDao articleBizDao;
    @Autowired
    private UserProfileTransDao userProfileTransDao;
    @Autowired
    private AdminBizService adminBizService;

    @Autowired
    private TransactionUtils transactionUtils;

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
            commentExtList.forEach(o -> {
                CommentBO bo = new CommentBO();
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
    public void doCreateComment(CommentBO bo, boolean isAdmin) throws JwBlogException {
        String isCanComment = webconfBizService.queryWebconfByKey(WebConfDataConfig.GLOBAL_COMMENT_ALLOW);
        if (Constants.FALSE.equals(isCanComment)) {
            throw CommentBizException.BLOG_NOT_ALLOW_COMMENT_EXCEPTION_CN.print();
        }
        Article article = articleBaseService.queryArticleByOid(bo.getArticleOid());

        if (!ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_PUBLISHED_CN.print();
        }

        if (!article.getIsComment()) {
            throw ArticleBizException.ARTICLE_NOT_ALLOW_COMMENT_EXCEPTION_CN.print();
        }

        Date now = DateUtil.getNow();
        String frequency = webconfBizService.queryWebconfByKey(WebConfDataConfig.COMMENT_ON_FREQUENCY);
        FrequencyBO frequencyBO = JwUtil.parseFrequency(frequency);
        if (!frequencyBO.getIsNoLimit()) {
            Date beforeDate = DateUtil.add(now, -1L * frequencyBO.getTime(), frequencyBO.getTimeUnit());
            Long count = commentTransDao.queryCommentsByDateRangeAndIp(beforeDate, now, bo.getClientIp());
            if (count >= frequencyBO.getTimes()) {
                throw CommentBizException.FREQUENCY_HIGH_CN.print();
            }
        }
        String maxComments = webconfBizService.queryWebconfByKey(WebConfDataConfig.MAX_COMMENTS_ONE_DAY_ONE_IP);
        if (StringUtils.isNotBlank(maxComments) && !Constants.NEGATIVE_ONE.equals(maxComments)) {
            int maxCommentsNum = Integer.parseInt(maxComments);
            Date beforeDate = DateUtil.getFirstTimeOfDay(now);
            Long count = commentTransDao.queryCommentsByDateRangeAndIp(beforeDate, now, bo.getClientIp());
            if (count >= maxCommentsNum) {
                throw CommentBizException.MORE_THAN_MAX_COMMENTS_ONE_DAY.format(maxComments).print();
            }
        }


        String replyToUserId = null;
        Comment replyComment = null;
        if (!TOP_PARENT_OID.equals(bo.getParentOid())) {
            replyComment = commentBaseService.queryCommentByOid(bo.getParentOid());
            if (null == replyComment) {
                throw CommentBizException.NOT_EXISTS_REPLY_COMMENT_CN.print();
            }
            if (replyComment.getIsDelete()) {
                throw CommentBizException.HAS_DELETE_REPLY_COMMENT_CN.print();

            }
        }
        UserTmpBO tmpBO = new UserTmpBO();
        if (!isAdmin) {
            tmpBO = JwBuilder.of(UserTmpBO::new).with(UserTmpBO::setNickname, bo.getUserNick())
                    .with(UserTmpBO::setQq, bo.getContactQq())
                    .with(UserTmpBO::setWechat, bo.getContactWechat())
                    .with(UserTmpBO::setWeibo, bo.getContactWeibo())
                    .with(UserTmpBO::setTel, bo.getContactTel())
                    .with(UserTmpBO::setEmail, bo.getContactEmail())
                    .build();
            userBizService.doCreateOrUpdateUser(bo.getClientIp(), tmpBO);
        }
        if (isAdmin) {
            String loginId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AdminBO adminBO = adminBizService.queryAdminInfoByLoginId(loginId);
            tmpBO.setAvatarSrc(adminBO.getAvatarSrc());
            tmpBO.setUserId(adminBO.getUsername());
            tmpBO.setUsername(adminBO.getUsername());
            tmpBO.setNickname(adminBO.getUserNick());

        }

        Comment comment = JwBuilder.of(Comment::new)
                .with(Comment::setArtDelStatus, ArticleDelStatusEnum.NOT_REMOVE.getValue())
                .with(Comment::setArticleOid, bo.getArticleOid())
                .with(Comment::setArticleTitle, article.getTitle())
                .with(Comment::setArticleAuthor, article.getAuthor())
                .with(Comment::setArticlePushBy, article.getPushBy())
                .with(Comment::setContent, bo.getContent())
                .with(Comment::setPraiseCount, 0L)
                .with(Comment::setAvatarSrc, tmpBO.getAvatarSrc())
                .with(Comment::setClientIp, bo.getClientIp())
                .with(Comment::setUserRegion, JwUtil.format(tmpBO.getUserRegion(), Constants.UNKNOW))
                .with(Comment::setReadStatus, CommReadEnum.UNREAD.getValue())
                .with(Comment::setParentOid, bo.getParentOid())
                .with(Comment::setContactQq, bo.getContactQq())
                .with(Comment::setContactWechat, bo.getContactWechat())
                .with(Comment::setContactWeibo, bo.getContactWeibo())
                .with(Comment::setContactTel, bo.getContactTel())
                .with(Comment::setContactEmail, bo.getContactEmail())
                .with(Comment::setUserId, tmpBO.getUserId())
                .with(Comment::setUserName, tmpBO.getUsername())
                .with(Comment::setUserNick, tmpBO.getNickname())
                .with(Comment::setFlagAdmin, isAdmin)
                .with(Comment::setCommentTime, now)
                .with(Comment::setCreateTime, now)
                .with(Comment::setUpdateTime, now)
                .build();
        ArticleWithBLOBs newArticle = new ArticleWithBLOBs();
        newArticle.setOid(article.getOid());
        newArticle.setCommentCount(article.getCommentCount() + 1);
        newArticle.setUpdateTime(now);


        //根评论
        if (TOP_PARENT_OID.equals(bo.getParentOid())) {
            comment.setFloorNumber((article.getTotalCommentFloors() == null ? 0L : article.getTotalCommentFloors()) + 1);
            newArticle.setTotalCommentFloors(comment.getFloorNumber());

        } else {
            //回复评论
            comment.setFloorNumber(replyComment.getFloorNumber());
            if (TOP_PARENT_OID.equals(replyComment.getParentOid())) {
                comment.setReplyRootOid(replyComment.getOid());
            } else {
                comment.setReplyRootOid(replyComment.getReplyRootOid());
            }
            comment.setReplyToUserId(replyComment.getUserId());
            comment.setReplyToUserName(replyComment.getUserName());
            comment.setReplyToUserNick(replyComment.getUserNick());
            replyToUserId = replyComment.getUserId();
        }

        try {
            commentTransDao.doInsertSelective(comment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw CommentBizException.CREATE_FAILED_EXCEPTION_CN.print();

        }
        //更新父级评论的回复评论数量
        if (!TOP_PARENT_OID.equals(bo.getParentOid())) {
            try {
                commentBizDao.doUpdateCommentReplyCnt(comment.getParentOid(), BizEventOptTypeEnum.CREATE.getValue());
            } catch (DaoException e) {
                log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
                throw CommentBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }
        //更新根评论的总评论数量/更新时间
        if (null != comment.getReplyRootOid()) {
            try {
                commentBizDao.doUpdateCommentTotalReplyCnt(comment.getReplyRootOid(), BizEventOptTypeEnum.CREATE.getValue());
            } catch (DaoException e) {
                log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
                throw CommentBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }


        try {
            articleTransDao.doUpdateByPrimaryKeySelective(newArticle);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddComment exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(bo.getArticleOid()).print();
        }


        //执行异步任务
        TaskDataD0020BO taskDataD0020BO = new TaskDataD0020BO();
        taskDataD0020BO.setOid(comment.getOid());
        taskDataD0020BO.setIp(bo.getClientIp());
        taskDataD0020BO.setAsyncIpType(AsyncIpEnum.COMMENT.name());
        Long taskId = null;
        try {

            taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0020.getValue(), JSONObject.toJSONString(taskDataD0020BO));
        } catch (JwBlogException e) {
            log.error("\r\n>>CommentBizServiceImpl.doAddComment exec failed, e\r\n", e);
        }
        if (taskId != null) {
            transactionUtils.doTriggerTaskAfterCommit(taskId);
        }
        registerBizEvent(comment.getOid(), comment.getContent(), BizEventOptTypeEnum.CREATE,
                tmpBO.getUsername(), bo.getClientIp());
        //创建消息(管理员)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", article.getTitle());
        jsonObject.put("ip", comment.getClientIp());
        jsonObject.put("content", comment.getContent());
        jsonObject.put("artOid", comment.getArticleOid());
        jsonObject.put("commOid", comment.getOid());
        msgBaseService.doCreateMsg(MsgTypeEnum.M10100002.getValue(), article.getPushBy(), null, jsonObject, comment.getOid().toString());

        if (StringUtils.isNotBlank(replyToUserId)) {
            //创建消息(用户)
            msgBaseService.doCreateMsg(MsgTypeEnum.M20101004.getValue(), null, replyToUserId, jsonObject, comment.getOid().toString());

        }
    }

    @Override
    public List<CommentBO> queryCommentsByArtOid(Long artOid, String currentIp) {
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
                replyCommentsExt.setReplyToUserId(comment.getUserId());
                replyCommentsExt.setReplyToUserName(comment.getUserName());
                replyCommentsExt.setReplyToUserNick(comment.getUserNick());
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
    public void doDelCommentById(Long oid, String ip, boolean isAdmin) throws JwBlogException {
        List<Comment> commentList = new ArrayList<>();
//       processCommentWithLevel(artComments);
        //2022-06-29 不删除子评论
//        queryAllSubComments(oid, commentList);
//        if (!CollectionUtils.isEmpty(commentList)) {
//            for (Comment sub : commentList) {
//                try {
//                    commentTransDao.doDeleteByPrimaryKey(sub.getOid());
//                } catch (DaoException e) {
//                    log.warn("CommentBizServiceImpl.doDelCommentById deleted failed, oid={}", sub.getOid());
//                }
//            }
//        }
        Comment comment = commentBaseService.queryCommentByOid(oid);
        if (!isAdmin && !comment.getClientIp().equals(ip)) {
            throw CommentBizException.OPERATION_WITHOUT_PERMISSION.print();

        }

        //更新父级评论的回复评论数量
        if (!TOP_PARENT_OID.equals(comment.getParentOid())) {
            try {
                commentBizDao.doUpdateCommentReplyCnt(comment.getParentOid(), BizEventOptTypeEnum.DELETE.getValue());
            } catch (DaoException e) {
                log.error("CommentBizServiceImpl.doDelCommentById exec failed, e:\n", e);
                throw CommentBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }
        //更新根评论的总评论数量/更新时间
        if (null != comment.getReplyRootOid()) {
            try {
                commentBizDao.doUpdateCommentTotalReplyCnt(comment.getReplyRootOid(), BizEventOptTypeEnum.DELETE.getValue());
            } catch (DaoException e) {
                log.error("CommentBizServiceImpl.doDelCommentById exec failed, e:\n", e);
                throw CommentBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }

        try {
            articleBizDao.doUpdateArticleCommTotalCnt(comment.getArticleOid(), BizEventOptTypeEnum.DELETE.getValue());
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doDelCommentById exec failed, e:\n", e);
            throw ArticleBizException.MODIFY_FAILED_EXCEPTION.format(comment.getArticleOid()).print();
        }

        try {
            commentTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.warn("CommentBizServiceImpl.doDelCommentById deleted failed, oid={}", oid);
        }




        registerBizEvent(oid, null, BizEventOptTypeEnum.DELETE,
                JwUtil.generateUsername(ip), ip);

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
                    tmp.setArticleTitle(commentExt.getArticleTitle());
                    tmp.setReplyToUserName(replyCommentsExt.getReplyToUserName());
                    tmp.setReplyToUserId(replyCommentsExt.getReplyToUserId());
                    tmp.setReplyToUserNick(replyCommentsExt.getReplyToUserNick());
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
            commentList.forEach(o -> {
                CommentBO bo = new CommentBO();
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
    public void doDelCommentByListOid(List<Long> oidList, String ip) throws JwBlogException {
        for (Long oid : oidList) {
            doDelCommentById(oid, ip, true);
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
        registerBizEvent(oid, null, BizEventOptTypeEnum.UPDATE);

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

    @Override
    public CommentMainPageListBO queryCommentsMainPageByArtOid(CommentMainParam param) throws JwBlogException {
        Article article = articleBaseService.queryArticleByOid(param.getArtOid());
        if (!ArticleStatusEnum.PUBLISHED.getValue().equals(article.getStatus())) {
            throw ArticleBizException.STATUS_NOT_PUBLISHED_CN.format(article.getTitle()).print();
        }
        CommentPageQuery query = new CommentPageQuery();
        query.setArtOid(param.getArtOid());
        query.setParentOid(param.getParentOid());
        query.setReplyRootOid(param.getReplyRootOid());
        query.setRefOid(param.getRefOid());
        query.setCurrentIp(param.getCurrentIp());
        query.setOrderWay(param.getOrderWay());
        query.setLimit(param.getPageSize());
        query.setPage(param.getPageNo());

        CommentMainPageListBO resBO = new CommentMainPageListBO();
        log.info(">>>Query comment List, param:[{}]", JSONObject.toJSONString(query));
        boolean enablePageQuery = true;
        List<CommentBO> list = new ArrayList<>();
        if (null != query.getRefOid()) {
            try {
                Comment refComm = commentTransDao.queryCommentByOidWithDel(query.getRefOid());
                Long rootOid = TOP_PARENT_OID.equals(refComm.getParentOid()) ? refComm.getOid() : refComm.getReplyRootOid();
                Comment rootComm = refComm;
                if (rootOid.compareTo(refComm.getOid()) != 0) {
                    rootComm = commentTransDao.queryCommentByOidWithDel(rootOid);
                }
                BizPraise bizPraise = bizPraiseTransDao.queryByBizOidAndIp(rootComm.getOid(), param.getCurrentIp(), PraiseTypeEnum.COMMENT.getValue());
                CommentBO bo = new CommentBO();
                BeanUtils.copyProperties(rootComm, bo);
                bo.setIsPraise(false);
                if (bizPraise != null) {
                    bo.setIsPraise(true);
                }
                bo.setFlagEdit(false);
                if (param.getCurrentIp().equals(rootComm.getClientIp())) {
                    bo.setFlagEdit(true);
                }
                //分页查询回复评论
                CommentPageQuery replyQuery = new CommentPageQuery();
                replyQuery.setArtOid(param.getArtOid());
                replyQuery.setReplyRootOid(rootOid);
                replyQuery.setCurrentIp(param.getCurrentIp());
                replyQuery.setIsLimit(false);
                List<CommentExt> replyCommentList = commentBizDao.queryCommentsPageListByArticleOid(replyQuery);
                List<CommentBO> replyCommentBOList = new ArrayList<>();

                if (!CollectionUtils.isEmpty(replyCommentList)) {
                    for (CommentExt reply : replyCommentList) {
                        CommentBO replyBo = new CommentBO();
                        BeanUtils.copyProperties(reply, replyBo);

                        replyBo.setIsPraise(false);
                        if (reply.getPraiseOid() != null) {
                            replyBo.setIsPraise(true);
                        }
                        replyBo.setFlagEdit(false);
                        if (param.getCurrentIp().equals(reply.getClientIp())) {
                            replyBo.setFlagEdit(true);
                        }
                        reply.setReplyCount(reply.getReplyCount());
                        replyCommentBOList.add(replyBo);
                    }
                    bo.setReplyComments(replyCommentBOList);
                }
                bo.setReplyCount(rootComm.getTotalReplyCount());
                if (rootComm.getIsDelete()) {
                    if (bo.getReplyCount() > 0L) {
                        bo.setContent(Constants.COMMENT_HAS_DELETE);
                        bo.setFlagEdit(false);
                        list.add(bo);
                        enablePageQuery = false;
                    }
                } else {
                    list.add(bo);
                    enablePageQuery = false;
                }

            } catch (DaoException ignore) {
            }
        }

        if (enablePageQuery) {
            List<CommentExt> commentList = commentBizDao.queryCommentsPageListByArticleOid(query);
            // 如果评论删除,且有回复评论,显示:该评论已删除
            if (!CollectionUtils.isEmpty(commentList)) {
                for (CommentExt o : commentList) {
                    CommentBO bo = new CommentBO();
                    BeanUtils.copyProperties(o, bo);

                    bo.setIsPraise(false);
                    if (o.getPraiseOid() != null) {
                        bo.setIsPraise(true);
                    }
                    bo.setFlagEdit(false);
                    if (param.getCurrentIp().equals(o.getClientIp())) {
                        bo.setFlagEdit(true);
                    }
                    List<CommentBO> replyCommentBOList = new ArrayList<>();
                    if (TOP_PARENT_OID.equals(query.getParentOid())) {
                        //分页查询回复评论
                        CommentPageQuery replyQuery = new CommentPageQuery();
                        replyQuery.setArtOid(param.getArtOid());
                        replyQuery.setReplyRootOid(o.getOid());
                        replyQuery.setCurrentIp(param.getCurrentIp());
                        replyQuery.setLimit(param.getReplyPageSize());
                        replyQuery.setPage(param.getReplyPageNo());
                        replyQuery.setOrderWay(param.getOrderWay());
                        List<CommentExt> replyCommentList = commentBizDao.queryCommentsPageListByArticleOid(replyQuery);

                        if (!CollectionUtils.isEmpty(replyCommentList)) {
                            for (CommentExt reply : replyCommentList) {
                                CommentBO replyBo = new CommentBO();
                                BeanUtils.copyProperties(reply, replyBo);

                                replyBo.setIsPraise(false);
                                if (reply.getPraiseOid() != null) {
                                    replyBo.setIsPraise(true);
                                }
                                replyBo.setFlagEdit(false);
                                if (param.getCurrentIp().equals(reply.getClientIp())) {
                                    replyBo.setFlagEdit(true);
                                }
                                reply.setReplyCount(reply.getReplyCount());
                                replyCommentBOList.add(replyBo);
                            }
                            bo.setReplyComments(replyCommentBOList);
                        }
                        bo.setReplyCount(o.getTotalReplyCount());


                    }
                    if (o.getIsDelete()) {
                        if (bo.getReplyCount() > 0L) {
                            bo.setContent(Constants.COMMENT_HAS_DELETE);
                            bo.setFlagEdit(false);
                            list.add(bo);
                        }
                    } else {
                        list.add(bo);
                    }
                }
            }
        }
        //首次渲染评论时才查询用户信息
        if (TOP_PARENT_OID.equals(query.getParentOid()) && null == query.getReplyRootOid()
                && query.getPage() == 1) {
            UserProfile userProfile = userProfileTransDao.queryOneUserProfileByIp(param.getCurrentIp());
            if (null != userProfile) {
                UserInfoBO user = JwBuilder.of(UserInfoBO::new)
                        .with(UserInfoBO::setAvatarSrc, userProfile.getAvatarSrc())
                        .with(UserInfoBO::setUserId, userProfile.getUserId())
                        .with(UserInfoBO::setUserName, userProfile.getUsername())
                        .with(UserInfoBO::setUserNick, userProfile.getUserNick())
                        .with(UserInfoBO::setUserRegion, userProfile.getRegisterRegion())
                        .with(UserInfoBO::setContactEmail, userProfile.getUserEmail())
                        .with(UserInfoBO::setContactQq, userProfile.getUserQq())
                        .with(UserInfoBO::setContactWechat, userProfile.getUserWechat()).build();
                resBO.setUserInfo(user);
            }
        }

        resBO.setList(list);
        //首次渲染评论时才查询记录数
        if (TOP_PARENT_OID.equals(query.getParentOid()) && null == query.getReplyRootOid()
                && query.getPage() == 1) {
            //总条数
            resBO.setCount(commentBizDao.queryCommentsCountByArticleOid(query));
            //根评论总条数
            resBO.setRootCommCount(commentBizDao.queryCommentsRootCountByArticleOid(query));
        }

        return resBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddPraise(String commOid, String ip) throws JwBlogException {
        Long oid = Long.parseLong(commOid);
        Comment comment = commentBaseService.queryCommentByOid(oid);


        BizPraise bizPraise = bizPraiseTransDao.queryByBizOidAndIp(oid, ip, PraiseTypeEnum.COMMENT.getValue());
        if (null != bizPraise) {
            throw BizPraiseBizException.PRAISE_COMMENT_ALREADY.print();
        }

        try {
            Comment newComment = new Comment();
            newComment.setOid(comment.getOid());
            newComment.setPraiseCount(comment.getPraiseCount() + 1);
            newComment.setUpdateTime(DateUtil.getNow());
            commentTransDao.doUpdateByPrimaryKeySelective(newComment);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw CommentBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        BizPraise newArtPraise = JwBuilder.of(BizPraise::new)
                .with(BizPraise::setBizOid, oid)
                .with(BizPraise::setUserIp, ip)
                .with(BizPraise::setType, PraiseTypeEnum.COMMENT.getValue())
                .with(BizPraise::setCreateTime, DateUtil.getNow())
                .with(BizPraise::setUpdateTime, DateUtil.getNow()).build();
        try {

            bizPraiseTransDao.doInsertSelective(newArtPraise);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw BizPraiseBizException.CREATE_FAILED_EXCEPTION.format(String.format("commOid=%s, ip=%s", commOid, ip)).print();
        }
        //创建消息

        JSONObject jsonObject4Admin = new JSONObject();
        jsonObject4Admin.put("title", comment.getArticleTitle());
        jsonObject4Admin.put("ip", comment.getClientIp());
        jsonObject4Admin.put("artOid", comment.getArticleOid());
        jsonObject4Admin.put("commOid", comment.getOid());
        msgBaseService.doCreateMsg(MsgTypeEnum.M20100001.getValue(), comment.getArticlePushBy(), null, jsonObject4Admin, comment.getOid().toString());


        if (!comment.getArticlePushBy().equals(comment.getUserId())) {
            JSONObject jsonObject4User = new JSONObject();
            jsonObject4User.put("title", comment.getArticleTitle());
            jsonObject4User.put("ip", comment.getClientIp());
            jsonObject4User.put("artOid", comment.getArticleOid());
            jsonObject4User.put("commOid", comment.getOid());
            jsonObject4User.put("content", comment.getContent());
            msgBaseService.doCreateMsg(MsgTypeEnum.M20101001.getValue(), null, comment.getUserId(), jsonObject4User, comment.getOid().toString());
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateComment(CommentBO bo) throws JwBlogException {
        Date now = DateUtil.getNow();
        Comment comment = commentBaseService.queryCommentByOid(bo.getOid());

        if (!comment.getClientIp().equals(bo.getClientIp())) {
            throw CommentBizException.OPERATION_WITHOUT_PERMISSION.print();

        }
        if (!comment.getArticleOid().equals(bo.getArticleOid())) {
            throw CommentBizException.ARTICLE_NOT_SAME.print();

        }

        UserTmpBO tmpBO = JwBuilder.of(UserTmpBO::new).with(UserTmpBO::setNickname, bo.getUserName())
                .with(UserTmpBO::setQq, bo.getContactQq())
                .with(UserTmpBO::setWechat, bo.getContactWechat())
                .with(UserTmpBO::setWeibo, bo.getContactWeibo())
                .with(UserTmpBO::setTel, bo.getContactTel())
                .with(UserTmpBO::setEmail, bo.getContactEmail())
                .build();
        userBizService.doCreateOrUpdateUser(bo.getClientIp(), tmpBO);

        Comment newComment = JwBuilder.of(Comment::new)
                .with(Comment::setOid, bo.getOid())
                .with(Comment::setContent, bo.getContent())
                .with(Comment::setAvatarSrc, tmpBO.getAvatarSrc())
                .with(Comment::setClientIp, bo.getClientIp())
                .with(Comment::setReadStatus, CommReadEnum.UNREAD.getValue())
                .with(Comment::setContactQq, bo.getContactQq())
                .with(Comment::setContactWechat, bo.getContactWechat())
                .with(Comment::setContactWeibo, bo.getContactWeibo())
                .with(Comment::setContactTel, bo.getContactTel())
                .with(Comment::setContactEmail, bo.getContactEmail())
                .with(Comment::setUserName, bo.getUserName())
                .with(Comment::setUpdateTime, now)
                .build();

        try {
            commentTransDao.doUpdateByPrimaryKeySelective(newComment);
        } catch (DaoException e) {
            log.error("CommentBizServiceImpl.doUpdateComment exec failed, e:\n", e);
            throw CommentBizException.MODIFY_FAILED_EXCEPTION.format("commOid : " + bo.getOid()).print();

        }


        registerBizEvent(comment.getOid(), newComment.getContent(), BizEventOptTypeEnum.UPDATE,
                tmpBO.getUsername(), bo.getClientIp());
        //创建消息

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", comment.getArticleTitle());
        jsonObject.put("ip", comment.getClientIp());
        jsonObject.put("content", comment.getContent());
        jsonObject.put("artOid", comment.getArticleOid());
        jsonObject.put("commOid", comment.getOid());
        msgBaseService.doCreateMsg(MsgTypeEnum.M20100003.getValue(), comment.getArticlePushBy(), null, jsonObject, comment.getOid().toString());

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

    private void registerBizEvent(Long oid, String desc, BizEventOptTypeEnum optTypeEnum, String username, String ip) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext(), username, ip);
        event.setBizEventTypeEnum(BizEventTypeEnum.COMMENT);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOid(oid);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }


    private void registerBizEvent(Long oid, String desc, BizEventOptTypeEnum optTypeEnum) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.COMMENT);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOptEntityId(oid != null ? String.valueOf(oid) : null);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }

}