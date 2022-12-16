package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.BizPraiseTransDao;
import cn.jianwoo.blog.dao.base.MessageBoardTransDao;
import cn.jianwoo.blog.dao.base.UserProfileTransDao;
import cn.jianwoo.blog.dao.biz.MessageBoardBizDao;
import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.entity.extension.MessageBoardExt;
import cn.jianwoo.blog.entity.query.MessageBoardPageQuery;
import cn.jianwoo.blog.entity.query.MessageBoardQuery;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.enums.CommReadEnum;
import cn.jianwoo.blog.enums.MsgTypeEnum;
import cn.jianwoo.blog.enums.PraiseTypeEnum;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.BizPraiseBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.MessageBoardBizException;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.base.MessageBoardBaseService;
import cn.jianwoo.blog.service.base.MsgBaseService;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.biz.MessagBoardBizService;
import cn.jianwoo.blog.service.biz.UserBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.service.bo.FrequencyBO;
import cn.jianwoo.blog.service.bo.MessageBoardBO;
import cn.jianwoo.blog.service.bo.MessageBoardMainPageListBO;
import cn.jianwoo.blog.service.bo.UserInfoBO;
import cn.jianwoo.blog.service.bo.UserTmpBO;
import cn.jianwoo.blog.service.param.MessageBoardMainParam;
import cn.jianwoo.blog.service.param.MessageBoardParam;
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
import org.springframework.beans.factory.annotation.Value;
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

/**
 * @author gulihua
 * @Description
 * @date 2022-08-06 21:54
 */
@Service
@Slf4j
public class MessagBoardBizServiceImpl implements MessagBoardBizService {
    @Autowired
    private MessageBoardTransDao messageBoardTransDao;
    @Autowired
    private MessageBoardBizDao messageBoardBizDao;
    @Autowired
    private MessageBoardBaseService messageBoardBaseService;

    @Autowired
    private WebconfBizService webconfBizService;

    @Autowired
    private UserBizService userBizService;
    @Autowired
    private AdminBizService adminBizService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private MsgBaseService msgBaseService;
    @Autowired
    private UserProfileTransDao userProfileTransDao;
    @Autowired
    private BizPraiseTransDao bizPraiseTransDao;

    @Value("${admin.name}")
    private String admin;

    private static final Long TOP_PARENT_OID = 0L;


    @Override
    public Long countAllMessages() {
        return messageBoardBizDao.countAllMessage();
    }

    @Override
    public List<MessageBoardBO> queryRecentMessages(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        List<MessageBoardExt> messageExtList = messageBoardBizDao.queryRecentMessage(limit);
        List<MessageBoardBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageExtList)) {
            messageExtList.forEach(o -> {
                MessageBoardBO bo = new MessageBoardBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        return list;
    }

    @Override
    public Long countWithUnreadMessage() {
        return messageBoardBizDao.countWithUnreadMsg();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateMessageBoard(MessageBoardBO bo, boolean isAdmin) throws JwBlogException {
        String isCanMessage = webconfBizService.queryWebconfByKey(WebConfDataConfig.GLOBAL_MESSAGE_ALLOW);
        if (Constants.FALSE.equals(isCanMessage)) {
            throw MessageBoardBizException.BLOG_NOT_ALLOW_MESSAGE_EXCEPTION_CN.print();
        }
        Date now = DateUtil.getNow();
        String frequency = webconfBizService.queryWebconfByKey(WebConfDataConfig.MESSAGE_ON_FREQUENCY);
        FrequencyBO frequencyBO = JwUtil.parseFrequency(frequency);
        if (!frequencyBO.getIsNoLimit()) {
            Date beforeDate = DateUtil.add(now, -1L * frequencyBO.getTime(), frequencyBO.getTimeUnit());
            Long count = messageBoardTransDao.queryMessagesByDateRangeAndIp(beforeDate, now, bo.getClientIp());
            if (count >= frequencyBO.getTimes()) {
                throw MessageBoardBizException.FREQUENCY_HIGH_CN.print();
            }
        }
        String maxMessages = webconfBizService.queryWebconfByKey(WebConfDataConfig.MAX_MESSAGES_ONE_DAY_ONE_IP);
        if (StringUtils.isNotBlank(maxMessages) && !Constants.NEGATIVE_ONE.equals(maxMessages)) {
            int maxMessagesNum = Integer.parseInt(maxMessages);
            Date beforeDate = DateUtil.getFirstTimeOfDay(now);
            Long count = messageBoardTransDao.queryMessagesByDateRangeAndIp(beforeDate, now, bo.getClientIp());
            if (count >= maxMessagesNum) {
                throw MessageBoardBizException.MORE_THAN_MAX_MESSAGES_ONE_DAY.format(maxMessages).print();
            }
        }
        String replyToUserId = null;
        MessageBoard replyMessage = null;
        if (!TOP_PARENT_OID.equals(bo.getParentOid())) {
            replyMessage = messageBoardBaseService.queryMessageByOid(bo.getParentOid());
            if (null == replyMessage) {
                throw MessageBoardBizException.NOT_EXISTS_REPLY_MESSAGE_CN.print();
            }
            if (replyMessage.getIsDelete()) {
                throw MessageBoardBizException.NOT_EXISTS_REPLY_MESSAGE_CN.print();

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
        MessageBoard message = JwBuilder.of(MessageBoard::new)
                .with(MessageBoard::setContent, bo.getContent())
                .with(MessageBoard::setPraiseCount, 0L)
                .with(MessageBoard::setAvatarSrc, tmpBO.getAvatarSrc())
                .with(MessageBoard::setClientIp, bo.getClientIp())
                .with(MessageBoard::setUserRegion, JwUtil.format(tmpBO.getUserRegion(), Constants.UNKNOW))
                .with(MessageBoard::setReadStatus, CommReadEnum.UNREAD.getValue())
                .with(MessageBoard::setParentOid, bo.getParentOid())
                .with(MessageBoard::setContactQq, bo.getContactQq())
                .with(MessageBoard::setContactWechat, bo.getContactWechat())
                .with(MessageBoard::setContactWeibo, bo.getContactWeibo())
                .with(MessageBoard::setContactTel, bo.getContactTel())
                .with(MessageBoard::setContactEmail, bo.getContactEmail())
                .with(MessageBoard::setUserId, tmpBO.getUserId())
                .with(MessageBoard::setUserName, tmpBO.getUsername())
                .with(MessageBoard::setUserNick, tmpBO.getNickname())
                .with(MessageBoard::setFlagAdmin, isAdmin)
                .with(MessageBoard::setPushTime, now)
                .with(MessageBoard::setCreateTime, now)
                .with(MessageBoard::setUpdateTime, now)
                .build();

        //回复留言

        if (!TOP_PARENT_OID.equals(bo.getParentOid())) {
            if (TOP_PARENT_OID.equals(replyMessage.getParentOid())) {
                message.setReplyRootOid(replyMessage.getOid());
            } else {
                message.setReplyRootOid(replyMessage.getReplyRootOid());
            }
            message.setReplyToUserId(replyMessage.getUserId());
            message.setReplyToUserName(replyMessage.getUserName());
            message.setReplyToUserNick(replyMessage.getUserNick());
            replyToUserId = replyMessage.getUserId();
        }

        try {
            messageBoardTransDao.doInsertSelective(message);
        } catch (DaoException e) {
            log.error("MessagBoardBizServiceImpl.doCreateMessageBoard exec failed, e:\n", e);
            throw MessageBoardBizException.CREATE_FAILED_EXCEPTION_CN.print();
        }

        //更新父级留言的回复留言数量
        if (!TOP_PARENT_OID.equals(bo.getParentOid())) {
            try {
                messageBoardBizDao.doUpdateMessageReplyCnt(message.getParentOid(), BizEventOptTypeEnum.CREATE.getValue());
            } catch (DaoException e) {
                log.error("MessagBoardBizServiceImpl.doCreateMessageBoard exec failed, e:\n", e);
                throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }
        //更新根留言的总留言数量/更新时间
        if (null != message.getReplyRootOid()) {
            try {
                messageBoardBizDao.doUpdateMessageTotalReplyCnt(message.getReplyRootOid(), BizEventOptTypeEnum.CREATE.getValue());
            } catch (DaoException e) {
                log.error("MessagBoardBizServiceImpl.doCreateMessageBoard exec failed, e:\n", e);
                throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }

        //执行异步任务
        TaskDataD0020BO taskDataD0020BO = new TaskDataD0020BO();
        taskDataD0020BO.setOid(message.getOid());
        taskDataD0020BO.setIp(bo.getClientIp());
        taskDataD0020BO.setAsyncIpType(AsyncIpEnum.MESSAGE_BOARD.name());
        Long taskId = null;
        try {

            taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0020.getValue(), JSONObject.toJSONString(taskDataD0020BO));
        } catch (JwBlogException e) {
            log.error("\r\n>>MessagBoardBizServiceImpl.doCreateMessageBoard exec failed, e\r\n", e);
        }
        if (taskId != null) {
            transactionUtils.doTriggerTaskAfterCommit(taskId);
        }
        registerBizEvent(message.getOid(), message.getContent(), BizEventOptTypeEnum.CREATE,
                tmpBO.getUsername(), bo.getClientIp());

        //创建消息(管理员)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ip", message.getClientIp());
        jsonObject.put("content", message.getContent());
        jsonObject.put("msgOid", message.getOid());
        msgBaseService.doCreateMsg(MsgTypeEnum.M00100002.getValue(), admin, null, jsonObject, message.getOid().toString());

        if (StringUtils.isNotBlank(replyToUserId)) {
            //创建消息(用户)
            msgBaseService.doCreateMsg(MsgTypeEnum.M30101004.getValue(), null, replyToUserId, jsonObject, message.getOid().toString());

        }
    }

    @Override
    public List<MessageBoardBO> queryMessages() {
        List<MessageBoardExt> messages = messageBoardBizDao.queryMessageExt();
        List<MessageBoardBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messages)) {
            messages.forEach(o -> {
                MessageBoardBO bo = new MessageBoardBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        log.info(">>query messages Dao Data: {}", JSON.toJSONString(list));
        return processMessageWithLevel(list);
    }


    /**
     * message list处理留言 把博客的顶级留言放在 MessageExt 对象中<br/>
     * ReplyMessageExt是顶级留言的所有回复留言
     * <p>
     * eg:<br/>
     * 文章留言a<br/>
     * --a的回复留言1<br/>
     * --a的回复留言2<br/>
     * --a的回复留言3<br/>
     * 文章留言b<br/>
     * --b的回复留言4<br/>
     * --b的回复留言5<br/>
     * --b的回复留言6<br/>
     *
     * @param messages 根据文章oid获取的留言list
     * @return
     * @author gulihua
     */
    private List<MessageBoardBO> processMessageWithLevel(List<MessageBoardBO> messages) {
        List<MessageBoardBO> newMessages = new ArrayList<>();
        Map<Long, List<MessageBoardBO>> messageGroup = new HashMap<>();
        if (!CollectionUtils.isEmpty(messages)) {
            Map<Long, MessageBoardBO> messageMap = messages.stream()
                    .collect(Collectors.toMap(MessageBoardBO::getOid, a -> a, (k1, k2) -> k1));
            for (MessageBoardBO m : messages) {
                MessageBoardBO messageExt = new MessageBoardBO();
                BeanUtils.copyProperties(m, messageExt);
                if (Objects.equals(m.getParentOid(), TOP_PARENT_OID)) {
                    newMessages.add(messageExt);
                }
                if (!messageGroup.containsKey(m.getParentOid())) {
                    List<MessageBoardBO> messList = new ArrayList<>();
                    messList.add(messageExt);
                    messageGroup.put(m.getParentOid(), messList);
                } else {
                    messageGroup.get(m.getParentOid()).add(messageExt);
                }
            }
            List<MessageBoardBO> subs = new ArrayList<>();
            processMessage(newMessages, messageGroup, subs, messageMap);
        }


        return newMessages;
    }

    /**
     * 递归处理留言
     *
     * @param newMessages  父级留言，根据父级留言会递归获取子留言，遍历子留言，再获取子留言，知道获取不到，即没有子留言
     * @param messageGroup 根据父留言Parent进行分的组
     * @param subMessages  子留言，递归叠加的所有顶级留言的子留言list，当没有子留言，new 新的对象
     * @param messageMap   根据主键oid进行分的组
     * @return
     * @author gulihua
     */
    private void processMessage(List<MessageBoardBO> newMessages, Map<Long, List<MessageBoardBO>> messageGroup,
                                List<MessageBoardBO> subMessages, Map<Long, MessageBoardBO> messageMap) {
        for (MessageBoardBO m : newMessages) {
            if (messageGroup.containsKey(m.getOid())) {
                List<MessageBoardBO> subs = messageGroup.get(m.getOid());
                processMessage(subs, messageGroup, subMessages, messageMap);
                if (Objects.equals(m.getParentOid(), TOP_PARENT_OID)) {
                    m.setReplyMessages(subMessages);
                    subMessages = new ArrayList<>();
                }

            }
            if (!Objects.equals(m.getParentOid(), TOP_PARENT_OID)) {
                if (subMessages == null) {
                    subMessages = new ArrayList<>();
                }
                MessageBoardBO replyMessagesExt = new MessageBoardBO();
                BeanUtils.copyProperties(m, replyMessagesExt);
                MessageBoardBO message = messageMap.get(m.getParentOid());
                replyMessagesExt.setReplyToUserId(message.getUserId());
                replyMessagesExt.setReplyToUserName(message.getUserName());
                replyMessagesExt.setReplyToUserNick(message.getUserNick());
                subMessages.add(replyMessagesExt);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddMessagePraise(Long oid) throws JwBlogException {
        try {
            messageBoardBizDao.doUpdateMessagePraiseCnt(oid);
        } catch (DaoException e) {
            log.error("MessagBoardBizServiceImpl.doAddMessagePraise exec failed, e:\n", e);
            throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doDelMessageById(Long oid, String ip, boolean isAdmin) throws JwBlogException {
        MessageBoard message = messageBoardBaseService.queryMessageByOid(oid);
        if (!isAdmin && !message.getClientIp().equals(ip)) {
            throw MessageBoardBizException.OPERATION_WITHOUT_PERMISSION.print();

        }

        //更新父级留言的回复留言数量
        if (!TOP_PARENT_OID.equals(message.getParentOid())) {
            try {
                messageBoardBizDao.doUpdateMessageReplyCnt(message.getParentOid(), BizEventOptTypeEnum.DELETE.getValue());
            } catch (DaoException e) {
                log.error("MessagBoardBizServiceImpl.doDelMessageById exec failed, e:\n", e);
                throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }
        //更新根留言的总留言数量/更新时间
        if (null != message.getReplyRootOid()) {
            try {
                messageBoardBizDao.doUpdateMessageTotalReplyCnt(message.getReplyRootOid(), BizEventOptTypeEnum.DELETE.getValue());
            } catch (DaoException e) {
                log.error("MessagBoardBizServiceImpl.doDelMessageById exec failed, e:\n", e);
                throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.print();

            }
        }

        try {
            messageBoardTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.warn("MessagBoardBizServiceImpl.doDelMessageById deleted failed, oid={}", oid);
        }

        registerBizEvent(oid, null, BizEventOptTypeEnum.DELETE,
                JwUtil.generateUsername(ip), ip);
    }

    @Override
    public PageInfo<MessageBoardBO> queryAllMessagePage(MessageBoardParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        MessageBoardQuery query = new MessageBoardQuery();
        BeanUtils.copyProperties(param, query);
        List<MessageBoardExt> messageList = messageBoardBizDao.queryAllMessageExt(query);
        List<MessageBoardBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageList)) {
            messageList.forEach(o -> {
                MessageBoardBO bo = new MessageBoardBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<MessageBoardBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doDelMessageByListOid(List<Long> oidList, String ip) throws JwBlogException {
        for (Long oid : oidList) {
            doDelMessageById(oid, ip, true);
        }
    }

    @Override
    public MessageBoardBO queryMessageExtByOid(String oid) throws JwBlogException {
        try {
            MessageBoard message = messageBoardBaseService.queryMessageByOid(Long.parseLong(oid));
            MessageBoardBO bo = new MessageBoardBO();
            BeanUtils.copyProperties(message, bo);
            return bo;
        } catch (Exception e) {
            throw MessageBoardBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();

        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateReadByOid(Long oid) throws JwBlogException {
        MessageBoard message = new MessageBoard();
        message.setOid(oid);
        message.setReadStatus(CommReadEnum.READ.getValue());
        try {
            messageBoardTransDao.doUpdateByPrimaryKeySelective(message);
        } catch (DaoException e) {
            log.error("MessagBoardBizServiceImpl.doUpdateReadByOid exec failed, e:\n", e);
            throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
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
    public MessageBoardMainPageListBO queryMessagesMainPage(MessageBoardMainParam param) throws JwBlogException {
        MessageBoardPageQuery query = new MessageBoardPageQuery();
        query.setParentOid(param.getParentOid());
        query.setReplyRootOid(param.getReplyRootOid());
        query.setRefOid(param.getRefOid());
        query.setCurrentIp(param.getCurrentIp());
        query.setOrderWay(param.getOrderWay());
        query.setLimit(param.getPageSize());
        query.setPage(param.getPageNo());

        MessageBoardMainPageListBO resBO = new MessageBoardMainPageListBO();
        log.info(">>>Query message List, param:[{}]", JSONObject.toJSONString(query));
        boolean enablePageQuery = true;
        List<MessageBoardBO> list = new ArrayList<>();
        if (null != query.getRefOid()) {
            try {
                MessageBoard refMsg = messageBoardTransDao.queryMessageBoardByOidWithDel(query.getRefOid());
                Long rootOid = TOP_PARENT_OID.equals(refMsg.getParentOid()) ? refMsg.getOid() : refMsg.getReplyRootOid();
                MessageBoard rootMsg = refMsg;
                if (rootOid.compareTo(refMsg.getOid()) != 0) {
                    rootMsg = messageBoardTransDao.queryMessageBoardByOidWithDel(rootOid);
                }

                BizPraise bizPraise = bizPraiseTransDao.queryByBizOidAndIp(rootMsg.getOid(), param.getCurrentIp(), PraiseTypeEnum.MESSAGE_BOARD.getValue());
                MessageBoardBO bo = new MessageBoardBO();
                BeanUtils.copyProperties(rootMsg, bo);
                bo.setIsPraise(false);
                if (bizPraise != null) {
                    bo.setIsPraise(true);
                }
                bo.setFlagEdit(false);
                if (param.getCurrentIp().equals(rootMsg.getClientIp())) {
                    bo.setFlagEdit(true);
                }
                //分页查询回复评论
                MessageBoardPageQuery replyQuery = new MessageBoardPageQuery();
                replyQuery.setReplyRootOid(rootOid);
                replyQuery.setCurrentIp(param.getCurrentIp());
                replyQuery.setIsLimit(false);
                List<MessageBoardExt> replyMessageList = messageBoardBizDao.queryMessagePageList(replyQuery);
                List<MessageBoardBO> replyMessageBOList = new ArrayList<>();

                if (!CollectionUtils.isEmpty(replyMessageList)) {
                    for (MessageBoardExt reply : replyMessageList) {
                        MessageBoardBO replyBo = new MessageBoardBO();
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
                        replyMessageBOList.add(replyBo);
                    }
                    bo.setReplyMessages(replyMessageBOList);
                }
                bo.setReplyCount(rootMsg.getTotalReplyCount());
                if (rootMsg.getIsDelete()) {
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
            List<MessageBoardExt> messageList = messageBoardBizDao.queryMessagePageList(query);
            // 如果留言删除,且有回复留言,显示:该留言已删除
            if (!CollectionUtils.isEmpty(messageList)) {
                for (MessageBoardExt o : messageList) {
                    MessageBoardBO bo = new MessageBoardBO();
                    BeanUtils.copyProperties(o, bo);

                    bo.setIsPraise(false);
                    if (o.getPraiseOid() != null) {
                        bo.setIsPraise(true);
                    }
                    bo.setFlagEdit(false);
                    if (param.getCurrentIp().equals(o.getClientIp())) {
                        bo.setFlagEdit(true);
                    }
                    List<MessageBoardBO> replyMessageBOList = new ArrayList<>();
                    if (TOP_PARENT_OID.equals(query.getParentOid())) {
                        //分页查询回复留言
                        MessageBoardPageQuery replyQuery = new MessageBoardPageQuery();
                        replyQuery.setReplyRootOid(o.getOid());
                        replyQuery.setCurrentIp(param.getCurrentIp());
                        replyQuery.setLimit(param.getReplyPageSize());
                        replyQuery.setPage(param.getReplyPageNo());
                        replyQuery.setOrderWay(param.getOrderWay());
                        List<MessageBoardExt> replyMessageList = messageBoardBizDao.queryMessagePageList(replyQuery);

                        if (!CollectionUtils.isEmpty(replyMessageList)) {
                            for (MessageBoardExt reply : replyMessageList) {
                                MessageBoardBO replyBo = new MessageBoardBO();
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
                                replyMessageBOList.add(replyBo);
                            }
                            bo.setReplyMessages(replyMessageBOList);
                        }
                        bo.setReplyCount(o.getTotalReplyCount());


                    }
                    if (o.getIsDelete()) {
                        if (bo.getReplyCount() > 0L) {
                            bo.setContent(Constants.MESSAGE_HAS_DELETE);
                            bo.setFlagEdit(false);
                            list.add(bo);
                        }
                    } else {
                        list.add(bo);
                    }
                }
            }
        }
        //首次渲染留言时才查询用户信息
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
        //首次渲染留言时才查询记录数
        if (TOP_PARENT_OID.equals(query.getParentOid()) && null == query.getReplyRootOid()
                && query.getPage() == 1) {
            //总条数
            resBO.setCount(messageBoardBizDao.countAllMessage());
            //根留言总条数
            resBO.setRootMsgCount(messageBoardBizDao.queryMessageRootCount());
        }

        return resBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddPraise(String msgOid, String ip) throws JwBlogException {
        Long oid = Long.parseLong(msgOid);
        MessageBoard message = messageBoardBaseService.queryMessageByOid(oid);


        BizPraise bizPraise = bizPraiseTransDao.queryByBizOidAndIp(oid, ip, PraiseTypeEnum.MESSAGE_BOARD.getValue());
        if (null != bizPraise) {
            throw BizPraiseBizException.PRAISE_MESSAGE_ALREADY.print();
        }

        try {
            MessageBoard newMessage = new MessageBoard();
            newMessage.setOid(message.getOid());
            newMessage.setPraiseCount(message.getPraiseCount() + 1);
            newMessage.setUpdateTime(DateUtil.getNow());
            messageBoardTransDao.doUpdateByPrimaryKeySelective(newMessage);
        } catch (DaoException e) {
            log.error("ArticleBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
        BizPraise newArtPraise = JwBuilder.of(BizPraise::new)
                .with(BizPraise::setBizOid, oid)
                .with(BizPraise::setUserIp, ip)
                .with(BizPraise::setType, PraiseTypeEnum.MESSAGE_BOARD.getValue())
                .with(BizPraise::setCreateTime, DateUtil.getNow())
                .with(BizPraise::setUpdateTime, DateUtil.getNow()).build();
        try {

            bizPraiseTransDao.doInsertSelective(newArtPraise);
        } catch (DaoException e) {
            log.error("MessagBoardBizServiceImpl.doAddPraise exec failed, e:\n", e);
            throw BizPraiseBizException.CREATE_FAILED_EXCEPTION.format(String.format("msgOid=%s, ip=%s", msgOid, ip)).print();
        }
        //创建消息

        JSONObject jsonObject4Admin = new JSONObject();
        jsonObject4Admin.put("ip", message.getClientIp());
        jsonObject4Admin.put("msgOid", message.getOid());
        msgBaseService.doCreateMsg(MsgTypeEnum.M30100001.getValue(), admin, null, jsonObject4Admin, message.getOid().toString());

        if (!admin.equals(message.getUserId())) {
            JSONObject jsonObject4User = new JSONObject();
            jsonObject4User.put("ip", message.getClientIp());
            jsonObject4User.put("msgOid", message.getOid());
            jsonObject4User.put("content", message.getContent());
            msgBaseService.doCreateMsg(MsgTypeEnum.M30101001.getValue(), null, message.getUserId(), jsonObject4User, message.getOid().toString());
        }
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateMessage(MessageBoardBO bo) throws JwBlogException {
        Date now = DateUtil.getNow();
        MessageBoard message = messageBoardBaseService.queryMessageByOid(bo.getOid());


        if (!message.getClientIp().equals(bo.getClientIp())) {
            throw MessageBoardBizException.OPERATION_WITHOUT_PERMISSION.print();

        }


        UserTmpBO tmpBO = JwBuilder.of(UserTmpBO::new).with(UserTmpBO::setNickname, bo.getUserName())
                .with(UserTmpBO::setQq, bo.getContactQq())
                .with(UserTmpBO::setWechat, bo.getContactWechat())
                .with(UserTmpBO::setWeibo, bo.getContactWeibo())
                .with(UserTmpBO::setTel, bo.getContactTel())
                .with(UserTmpBO::setEmail, bo.getContactEmail())
                .build();
        userBizService.doCreateOrUpdateUser(bo.getClientIp(), tmpBO);

        MessageBoard newMessage = JwBuilder.of(MessageBoard::new)
                .with(MessageBoard::setOid, bo.getOid())
                .with(MessageBoard::setContent, bo.getContent())
                .with(MessageBoard::setAvatarSrc, tmpBO.getAvatarSrc())
                .with(MessageBoard::setClientIp, bo.getClientIp())
                .with(MessageBoard::setReadStatus, CommReadEnum.UNREAD.getValue())
                .with(MessageBoard::setContactQq, bo.getContactQq())
                .with(MessageBoard::setContactWechat, bo.getContactWechat())
                .with(MessageBoard::setContactWeibo, bo.getContactWeibo())
                .with(MessageBoard::setContactTel, bo.getContactTel())
                .with(MessageBoard::setContactEmail, bo.getContactEmail())
                .with(MessageBoard::setUserName, bo.getUserName())
                .with(MessageBoard::setUpdateTime, now)
                .build();

        try {
            messageBoardTransDao.doUpdateByPrimaryKeySelective(newMessage);
        } catch (DaoException e) {
            log.error("MessagBoardBizServiceImpl.doUpdateMessage exec failed, e:\n", e);
            throw MessageBoardBizException.MODIFY_FAILED_EXCEPTION.format("msgOid : " + bo.getOid()).print();

        }


        registerBizEvent(message.getOid(), newMessage.getContent(), BizEventOptTypeEnum.UPDATE,
                tmpBO.getUsername(), bo.getClientIp());
        //创建消息

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ip", newMessage.getClientIp());
        jsonObject.put("content", newMessage.getContent());
        jsonObject.put("msgOid", newMessage.getOid());
        msgBaseService.doCreateMsg(MsgTypeEnum.M30100003.getValue(), admin, null, jsonObject, message.getOid().toString());

    }

    private void registerBizEvent(Long oid, String desc, BizEventOptTypeEnum optTypeEnum, String username, String ip) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext(), username, ip);
        event.setBizEventTypeEnum(BizEventTypeEnum.MESSAGE_BOARD);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOid(oid);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }

    private void registerBizEvent(Long oid, String desc, BizEventOptTypeEnum optTypeEnum) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.MESSAGE_BOARD);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOptEntityId(oid != null ? String.valueOf(oid) : null);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }
}
