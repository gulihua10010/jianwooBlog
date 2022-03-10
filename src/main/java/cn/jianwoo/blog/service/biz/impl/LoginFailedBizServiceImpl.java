package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.LoginFailedEventTransDao;
import cn.jianwoo.blog.entity.LoginFailedEvent;
import cn.jianwoo.blog.enums.LoginFailedEventStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.LoginFailedEventBizException;
import cn.jianwoo.blog.service.biz.LoginFailedBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-08 11:41
 */
@Service
public class LoginFailedBizServiceImpl implements LoginFailedBizService {
    @Autowired
    private LoginFailedEventTransDao loginFailedEventTransDao;
    @Autowired
    private WebconfBizService webconfBizService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSaveRecord(String loginId, String ip) throws JwBlogException {
        Date now = DateUtil.getNow();
        LoginFailedEvent record = loginFailedEventTransDao.queryEffectiveFailed(loginId, ip);
        if (null != record) {
            LoginFailedEvent newRecord = new LoginFailedEvent();
            newRecord.setOid(record.getOid());
            newRecord.setFailedTimes(record.getFailedTimes() + 1);
            newRecord.setUpdateTime(now);
            String value = webconfBizService.queryWebconfByKey(WebConfDataConfig.MAX_LOGIN_FAILED_TIMES);
            if (StringUtils.isNotBlank(value) && newRecord.getFailedTimes() >= Integer.parseInt(value)) {
                newRecord.setIsBlock(true);
                newRecord.setBlockTime(now);
                newRecord.setUnblockTime(DateUtil.add(now, 1, TimeUnit.HOURS));
            }
            try {
                loginFailedEventTransDao.doUpdateByPrimaryKeySelective(newRecord);
            } catch (DaoException e) {
                throw LoginFailedEventBizException.MODIFY_FAILED_EXCEPTION.format(record.getOid()).print();
            }

            return;
        }
        LoginFailedEvent event = new LoginFailedEvent();
        event.setLoginId(loginId);
        event.setLoginIp(ip);
        event.setFailedTimes(1);
        event.setCreateTime(now);
        event.setUpdateTime(now);
        try {
            loginFailedEventTransDao.doInsertSelective(event);
        } catch (DaoException e) {
            throw LoginFailedEventBizException.CREATE_FAILED_EXCEPTION.print();
        }
    }

    @Override
    public boolean queryIsBlock(String loginId, String ip) throws JwBlogException {
        LoginFailedEvent record = loginFailedEventTransDao.queryEffectiveFailed(loginId, ip);
        if (null == record) {
            return false;
        }
        if (!record.getIsBlock()) {
            return false;
        }
        Date now = DateUtil.getNow();

        if (null != record.getUnblockTime() && record.getUnblockTime().before(now)) {
            LoginFailedEvent newRecord = new LoginFailedEvent();
            newRecord.setOid(record.getOid());
            newRecord.setStatus(LoginFailedEventStatusEnum.VOID.getValue());
            newRecord.setUpdateTime(now);

            try {
                loginFailedEventTransDao.doUpdateByPrimaryKeySelective(newRecord);
            } catch (DaoException e) {
                throw LoginFailedEventBizException.MODIFY_FAILED_EXCEPTION.format(record.getOid()).print();
            }
            return false;
        }
        return true;
    }

    @Override
    public void doVoidRecord(String loginId, String ip) throws JwBlogException {
        LoginFailedEvent record = loginFailedEventTransDao.queryEffectiveFailed(loginId, ip);
        if (null != record) {
            LoginFailedEvent newRecord = new LoginFailedEvent();
            newRecord.setOid(record.getOid());
            newRecord.setStatus(LoginFailedEventStatusEnum.VOID.getValue());
            newRecord.setUpdateTime(DateUtil.getNow());

            try {
                loginFailedEventTransDao.doUpdateByPrimaryKeySelective(newRecord);
            } catch (DaoException e) {
                throw LoginFailedEventBizException.MODIFY_FAILED_EXCEPTION.format(record.getOid()).print();
            }
        }
    }
}
