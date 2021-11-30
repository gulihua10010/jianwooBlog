package cn.jianwoo.blog.event;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.LoginLogTransDao;
import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.enums.ProcessStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.task.AsyncTask;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:00
 */
@Service
@Slf4j
public class LoginEventListener implements ApplicationListener<LoginLogEvent> {
    @Autowired
    private LoginLogTransDao loginLogTransDao;
    @Autowired
    private AsyncTask asyncTask;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onApplicationEvent(LoginLogEvent loginLogEvent) {
        HttpServletRequest request = loginLogEvent.getRequest();
        String userAgent = request.getHeader("User-Agent");
//        log.info(">>userAgent {}" ,userAgent);
        if (StringUtils.isNotBlank(userAgent) && userAgent.length() > 200) {
            userAgent = userAgent.substring(0, 200);
        }
        String ip = request.getRemoteAddr();
        Date now = DateUtil.getNow();
        LoginLog loginLog = JwBuilder.of(LoginLog::new)
                .with(LoginLog::setLoginId, loginLogEvent.getUsername())
                .with(LoginLog::setUserName, loginLogEvent.getUsername())
                .with(LoginLog::setEventType, loginLogEvent.getEventTypeEnum().getValue())
                .with(LoginLog::setTriggerTime, now)
                .with(LoginLog::setTriggerDesc, loginLogEvent.getLoginDesc())
                .with(LoginLog::setTriggerDevice, userAgent)
                .with(LoginLog::setTriggerIp, ip)
                .with(LoginLog::setProcessStatus, ProcessStatusEnum.valueOfBoolean(loginLogEvent.getIsSuccess()))
                .with(LoginLog::setFailedReason, loginLogEvent.getReason())
                .with(LoginLog::setCreateTime, now)
                .with(LoginLog::setUpdateTime, now).build();
        try {
            loginLogTransDao.doInsertSelective(loginLog);
        } catch (DaoException e) {
            log.error("\r\n>>LoginEventListener.onApplicationEvent exec failed, e\r\n", e);
        }

        //执行异步任务
        asyncTask.execLoginIpAreaTask(loginLog.getOid());

    }
}
