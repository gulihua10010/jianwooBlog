package cn.jianwoo.blog.task.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.TaskConstants;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.JwBlogTaskException;
import cn.jianwoo.blog.service.notify.NotifyMsgService;
import cn.jianwoo.blog.task.AsyncAutoTaskService;
import cn.jianwoo.blog.task.bo.TaskDataD0010BO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建消息后的邮件发送任务
 *
 * @author gulihua
 * @Description
 * @date 2022-05-07 13:46
 */
@Service("asyncProcAutoTaskD0010")
@Slf4j
public class AsyncProcAutoTaskD0010Impl implements AsyncAutoTaskService {
    @Autowired
    private NotifyMsgService emailNotifyService;

    @Override
    public JSONObject doProc(Long taskId, String taskData) throws JwBlogTaskException {

        log.info(">> start D0010 task({}) which data is {} <<", taskId, taskData);

        // read parameter taskData
        TaskDataD0010BO data = JSON.parseObject(taskData, TaskDataD0010BO.class);
        String[] recipients = data.getRecipient().split(Constants.COMMA_SEPARATOR);
        try {
            emailNotifyService.doSend(data.getEmailTplCode(), data.getParam(), recipients);
        } catch (JwBlogException e) {
            log.error("AsyncProcAutoTaskD0010Impl.doProc exec failed, e:", e);
            throw new JwBlogTaskException(TaskConstants.FAILED_CODE, e.getMsg());
        }
        return returnSuccessJsonResult();
    }

    private JSONObject returnSuccessJsonResult() {
        JSONObject jsonRlt = new JSONObject();
        jsonRlt.put(TaskConstants.RESULT_CODE, TaskConstants.SUCCESS_CODE);
        jsonRlt.put(TaskConstants.RESULT_MSG, TaskConstants.SUCCESS_MSG);
        return jsonRlt;
    }
}
