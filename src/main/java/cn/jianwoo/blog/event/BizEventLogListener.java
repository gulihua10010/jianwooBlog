package cn.jianwoo.blog.event;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.BizEventLogTransDao;
import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.enums.ProcessStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:00
 */
@Service
@Slf4j
public class BizEventLogListener implements ApplicationListener<BizEventLogEvent> {
    @Autowired
    private BizEventLogTransDao bizEventLogTransDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(BizEventLogEvent bizEventLogEvent) {
        Date now = DateUtil.getNow();
        log.info(">>BizEventLogListener.onApplicationEvent start, [loginId{}]", bizEventLogEvent.getUsername());

        BizEventLog eventLog = JwBuilder.of(BizEventLog::new)
                .with(BizEventLog::setLoginId, bizEventLogEvent.getUsername())
                .with(BizEventLog::setUserName, bizEventLogEvent.getUsername())
                .with(BizEventLog::setEventType, bizEventLogEvent.getBizEventTypeEnum().getValue())
                .with(BizEventLog::setOptType, bizEventLogEvent.getBizEventOptTypeEnum().getValue())
                .with(BizEventLog::setOptEntityId, bizEventLogEvent.getOptEntityId())
                .with(BizEventLog::setTriggerTime, now)
                .with(BizEventLog::setOptEntityDesc, bizEventLogEvent.getDesc())
                .with(BizEventLog::setTriggerIp, bizEventLogEvent.getIp())
                .with(BizEventLog::setProcessStatus, ProcessStatusEnum.valueOfBoolean(bizEventLogEvent.getIsSuccess()))
                .with(BizEventLog::setFailedReason, bizEventLogEvent.getReason())
                .with(BizEventLog::setCreateTime, now)
                .with(BizEventLog::setUpdateTime, now).build();
        try {
            bizEventLogTransDao.doInsertSelective(eventLog);
        } catch (DaoException e) {
            log.error("\r\n>>BizEventLogListener.onApplicationEvent exec failed, e\r\n", e);
        }


    }
}
