package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.AsyncProcAutoTaskTransDao;
import cn.jianwoo.blog.dao.base.AsyncProcTaskTypeCfgTransDao;
import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.enums.ProcessStatusEnum;
import cn.jianwoo.blog.exception.AsyncProcTaskBizException;
import cn.jianwoo.blog.exception.AsyncProcTaskCfgBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.task.AsyncTaskExec;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-07 10:28
 */
@Service
@Slf4j
public class AsyncAutoTaskBaseServiceImpl implements AsyncAutoTaskBaseService {

    @Autowired
    private AsyncProcTaskTypeCfgTransDao asyncProcTaskTypeCfgTransDao;
    @Autowired
    private AsyncProcAutoTaskTransDao asyncProcAutoTaskTransDao;
    @Autowired
    private AsyncTaskExec asyncTaskExec;

    @Override
    public Long doCreateTask(String taskType, String taskData) throws JwBlogException {
        AsyncProcTaskTypeCfg asyncProcTaskTypeCfg;
        try {
            asyncProcTaskTypeCfg = asyncProcTaskTypeCfgTransDao.queryAsyncProcTaskTypeCfgByPrimaryKey(taskType);
        } catch (DaoException e) {
            throw AsyncProcTaskCfgBizException.NOT_EXIST_EXCEPTION.format(taskType).print();
        }
        Date now = DateUtil.getNow();

        AsyncProcAutoTask task = JwBuilder.of(AsyncProcAutoTask::new)
                .with(AsyncProcAutoTask::setTaskType, taskType)
                .with(AsyncProcAutoTask::setTaskData, taskData)
                .with(AsyncProcAutoTask::setTimesMaxRetry, asyncProcTaskTypeCfg.getTimesMaxRetry())
                .with(AsyncProcAutoTask::setStatusProc, ProcessStatusEnum.INIT.getValue())
                .with(AsyncProcAutoTask::setStatusProcDesc, ProcessStatusEnum.INIT.getDesc())
                .with(AsyncProcAutoTask::setProcStartTime, now)
                .with(AsyncProcAutoTask::setCreatedTime, now)
                .with(AsyncProcAutoTask::setLastUpdTime, now).build();

        try {
            asyncProcAutoTaskTransDao.doInsertSelective(task);
        } catch (DaoException e) {
            throw AsyncProcTaskCfgBizException.CREATE_FAILED_EXCEPTION.format(taskType).print();

        }
        return task.getTaskId();
    }

    @Override
    public void doUpdateTask(Long taskId, boolean isSuccess, String code, String reason) throws JwBlogException {
        Date now = DateUtil.getNow();
        AsyncProcAutoTask task = JwBuilder.of(AsyncProcAutoTask::new)
                .with(AsyncProcAutoTask::setTaskId, taskId)
                .with(AsyncProcAutoTask::setStatusProc, ProcessStatusEnum.SUCCESS.getValue())
                .with(AsyncProcAutoTask::setStatusProcDesc, ProcessStatusEnum.SUCCESS.getDesc())
                .with(AsyncProcAutoTask::setProcEndTime, now)
                .with(AsyncProcAutoTask::setLastUpdTime, now).build();
        if (!isSuccess) {
            task.setStatusProc(ProcessStatusEnum.FAILED.getValue());
            if (reason.length() > 500) {
                reason = reason.substring(0, 500);
            }
            task.setStatusProcDesc(reason);
            task.setFailedReasonDesc(reason);
            task.setTimesProcFailed(1);
            task.setFailedReasonCode(code);
        }

        try {
            asyncProcAutoTaskTransDao.doUpdateByPrimaryKeySelective(task);
        } catch (DaoException e) {
            throw AsyncProcTaskBizException.MODIFY_FAILED_EXCEPTION.format(taskId).print();

        }
    }

    @Override
    public void doTriggerTask(Long taskId) {
        asyncTaskExec.execTask(taskId);
    }
}
