package cn.jianwoo.blog.task.impl;

import cn.jianwoo.blog.constants.TaskConstants;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.JwBlogTaskException;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.biz.EmailBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.task.AsyncAutoTaskService;
import cn.jianwoo.blog.task.bo.TaskDataD0099BO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 异常邮件发送任务
 *
 * @author gulihua
 * @Description
 * @date 2022-05-07 13:46
 */
@Service("asyncProcAutoTaskD0099")
@Slf4j
public class AsyncProcAutoTaskD0099Impl implements AsyncAutoTaskService {
    @Autowired
    private EmailBizService emailBizService;
    @Autowired
    private AdminBizService adminBizService;
    @Value("${admin.name}")
    private String adminName;

    @Override
    public JSONObject doProc(Long taskId, String taskData) throws JwBlogTaskException {

        log.info(">> start D0099 task({}) which data is {} <<", taskId, taskData);

        // read parameter taskData
        TaskDataD0099BO data = JSON.parseObject(taskData, TaskDataD0099BO.class);
        //发送邮件
        try {
            AdminBO admin = adminBizService.queryAdminInfoByLoginId(adminName.trim());

            emailBizService.doSendEmail(admin.getUserEmail(), "-1", null, "【简窝博客】系统异常", data.getExcepionMsg());
        } catch (JwBlogException ex) {
//            log.error("defaultExceptionHandler(AsyncProcAutoTaskD0099Impl).sendEmail failed, exception:\r\n", ex);
            return returnFailedJsonResult(ex.getMsg());
        }
        return returnSuccessJsonResult();
    }

    private JSONObject returnSuccessJsonResult() {
        JSONObject jsonRlt = new JSONObject();
        jsonRlt.put(TaskConstants.RESULT_CODE, TaskConstants.SUCCESS_CODE);
        jsonRlt.put(TaskConstants.RESULT_MSG, TaskConstants.SUCCESS_MSG);
        return jsonRlt;
    }

    private JSONObject returnFailedJsonResult(String msg) {
        JSONObject jsonRlt = new JSONObject();
        jsonRlt.put(TaskConstants.RESULT_CODE, TaskConstants.FAILED_CODE);
        jsonRlt.put(TaskConstants.RESULT_MSG, msg);
        return jsonRlt;
    }
}
