package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.MessageProfileTransDao;
import cn.jianwoo.blog.dao.base.MessageTemplateTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.entity.MessageTemplate;
import cn.jianwoo.blog.enums.ProcessStatusEnum;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.MsgProfileBizException;
import cn.jianwoo.blog.exception.MsgTemplateBizException;
import cn.jianwoo.blog.service.base.AdminBaseService;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.base.MsgBaseService;
import cn.jianwoo.blog.task.bo.TaskDataD0010BO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-06 17:42
 */
@Service
@Slf4j
public class MsgBaseServiceImpl implements MsgBaseService {
    @Autowired
    private MessageProfileTransDao messageProfileTransDao;
    @Autowired
    private MessageTemplateTransDao messageTemplateTransDao;
    @Autowired
    private AdminBaseService adminBaseService;


    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;
    @Autowired
    private TransactionUtils transactionUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateMsg(String busiSceneCode, String loginId, JSONObject msgData, String bizId) throws JwBlogException {
        MessageTemplate messageTemplate;
        try {
            messageTemplate = messageTemplateTransDao.queryMessageTemplateByBusiSceneCode(busiSceneCode);
        } catch (DaoException e) {
            throw MsgTemplateBizException.NOT_EXIST_EXCEPTION.format(busiSceneCode).print();
        }
        Admin admin = adminBaseService.queryAdminByLoginId(loginId);
        Date now = DateUtil.getNow();
        MessageProfileWithBLOBs messageProfile = JwBuilder.of(MessageProfileWithBLOBs::new)
                .with(MessageProfileWithBLOBs::setBusiSceneCode, busiSceneCode)
                .with(MessageProfileWithBLOBs::setBusiType, messageTemplate.getBusiType())
                .with(MessageProfileWithBLOBs::setMsgType, messageTemplate.getMsgType())
                .with(MessageProfileWithBLOBs::setMsgTitle, JwUtil.doRenderTpl(messageTemplate.getMsgTitleTemplate(), msgData.toString()))
                .with(MessageProfileWithBLOBs::setMsgLink, JwUtil.doRenderTpl(messageTemplate.getMsgLinkTemplate(), msgData.toString()))
                .with(MessageProfileWithBLOBs::setMsgContent, JwUtil.doRenderTpl(new String(messageTemplate.getMsgContentTemplate()), msgData.toString()).getBytes(StandardCharsets.UTF_8))
                .with(MessageProfileWithBLOBs::setBizId, bizId)
                .with(MessageProfileWithBLOBs::setBizParam, msgData.toJSONString().getBytes(StandardCharsets.UTF_8))
                .with(MessageProfileWithBLOBs::setReceiverId, loginId)
                .with(MessageProfileWithBLOBs::setReceiverName, admin.getUserNick())
                .with(MessageProfileWithBLOBs::setReceiverEmail, admin.getUserEmail())
                .with(MessageProfileWithBLOBs::setReceiverMobileNo, admin.getUserPhone())
                .with(MessageProfileWithBLOBs::setEmailNotify, messageTemplate.getEmailNotify())
                .with(MessageProfileWithBLOBs::setEmailTplCode, messageTemplate.getEmailTplCode())
                .with(MessageProfileWithBLOBs::setFlagRead, false)
                .with(MessageProfileWithBLOBs::setFlagPopup, false)
                .with(MessageProfileWithBLOBs::setProcRslt, ProcessStatusEnum.SUCCESS.getValue())
                .with(MessageProfileWithBLOBs::setProcTime, now)
                .with(MessageProfileWithBLOBs::setSendTime, now)
                .with(MessageProfileWithBLOBs::setCreateTime, now)
                .with(MessageProfileWithBLOBs::setUpdateTime, now).build();

        try {
            messageProfileTransDao.doInsertSelective(messageProfile);
        } catch (DaoException e) {
            throw MsgProfileBizException.CREATE_FAILED_EXCEPTION.format(busiSceneCode).print();

        }

        if (messageTemplate.getEmailNotify()) {
            TaskDataD0010BO taskDataD0010BO = new TaskDataD0010BO();
            taskDataD0010BO.setEmailTplCode(messageProfile.getEmailTplCode());
            taskDataD0010BO.setParam(msgData);
            taskDataD0010BO.setRecipient(messageProfile.getReceiverEmail());
            Long taskId = null;
            try {

                taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0010.getValue(), JSONObject.toJSONString(taskDataD0010BO));
            } catch (JwBlogException e) {
                log.error("\r\n>>MsgBaseServiceImpl.doCreateMsg exec failed, e\r\n", e);
            }
            if (taskId != null) {
                transactionUtils.doTriggerTaskAfterCommit(taskId);
            }
        }


    }

    @Override
    public MessageProfile getMessageProfile(Long oid) throws JwBlogException {
        try {
            return messageProfileTransDao.queryMessageProfileByPrimaryKey(oid);
        } catch (DaoException e) {
            throw MsgProfileBizException.NOT_EXIST_EXCEPTION.format(oid).print();

        }
    }
}
