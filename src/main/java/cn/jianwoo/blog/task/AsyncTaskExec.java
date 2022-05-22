package cn.jianwoo.blog.task;

import cn.hutool.extra.spring.SpringUtil;
import cn.jianwoo.blog.constants.StatusCode;
import cn.jianwoo.blog.constants.TaskConstants;
import cn.jianwoo.blog.dao.base.AsyncProcAutoTaskTransDao;
import cn.jianwoo.blog.dao.base.AsyncProcTaskTypeCfgTransDao;
import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.exception.AsyncProcTaskBizException;
import cn.jianwoo.blog.exception.AsyncProcTaskCfgBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-07 11:54
 */
@Component
@Async("jianwooAsyncTaskExecutor")
@Slf4j
public class AsyncTaskExec {
    @Autowired
    private AsyncProcTaskTypeCfgTransDao asyncProcTaskTypeCfgTransDao;
    @Autowired
    private AsyncProcAutoTaskTransDao asyncProcAutoTaskTransDao;
    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;

    public Future<String> execTask(Long taskId) {
        log.info(">> Start async task jianwooAsyncTaskExecutor");

        try {
            AsyncProcAutoTask task;
            try {
                task = asyncProcAutoTaskTransDao.queryAsyncProcAutoTaskByPrimaryKey(taskId);
            } catch (DaoException e) {
                throw AsyncProcTaskBizException.NOT_EXIST_EXCEPTION.format(taskId).print();
            }
            AsyncProcTaskTypeCfg asyncProcTaskTypeCfg;
            try {
                asyncProcTaskTypeCfg = asyncProcTaskTypeCfgTransDao.queryAsyncProcTaskTypeCfgByPrimaryKey(task.getTaskType());
            } catch (DaoException e) {
                throw AsyncProcTaskCfgBizException.NOT_EXIST_EXCEPTION.format(task.getTaskType()).print();
            }
            AsyncAutoTaskService service = SpringUtil.getBean(asyncProcTaskTypeCfg.getExecSrvId());
            try {
                JSONObject res = service.doProc(taskId, task.getTaskData());
                if (TaskConstants.SUCCESS_CODE.equals(res.getString(TaskConstants.RESULT_CODE))) {
                    asyncAutoTaskBaseService.doUpdateTask(taskId, true, StatusCode.SUCCESS.getStatus(), null);
                } else {
                    asyncAutoTaskBaseService.doUpdateTask(taskId, false, res.getString(TaskConstants.RESULT_CODE), res.getString(TaskConstants.RESULT_MSG));
                }
            } catch (JwBlogException e) {
                log.error("AsyncAutoTaskBaseServiceImpl.doTriggerTask exec failed, e:\r\n", e);
                asyncAutoTaskBaseService.doUpdateTask(taskId, false, e.getCode(), e.getMsg());
            } catch (Exception e) {
                log.error("AsyncAutoTaskBaseServiceImpl.doTriggerTask exec failed, e:\r\n", e);
                asyncAutoTaskBaseService.doUpdateTask(taskId, false, StatusCode.FAILED.getStatus(), e.getMessage());
            }
        } catch (JwBlogException e) {
            log.error("jianwooAsyncTaskExecutor.execTask exec failed, e:", e);

        }
        log.info(">> End async task jianwooAsyncTaskExecutor");

        return new AsyncResult<>("jianwooAsyncTaskExecutor");

    }

}
