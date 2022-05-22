package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.task.bo.TaskDataD0099BO;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-12 17:55
 */
@Component
@EnableAspectJAutoProxy
@Aspect
@Slf4j
public class ExceptionAOPHandler {
    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;
    @Autowired
    private TransactionUtils transactionUtils;

    @Autowired
    private WebconfBizService webconfBizService;

    //创建切入点,在service层切入
    @Pointcut(value = "(execution(* cn.jianwoo.blog.service.biz.impl.*.*(..))||" +
            "execution(* cn.jianwoo.blog.service.base.impl.*.*(..))||" +
            "execution(* cn.jianwoo.blog.service.notify.impl.*.*(..))||" +
            "execution(* cn.jianwoo.blog.task.impl.*.*(..))) " +
            "&& !execution(* cn.jianwoo.blog.service.base.impl.AsyncAutoTaskBaseServiceImpl.*(..))" +
            "&& !execution(* cn.jianwoo.blog.service.biz.impl.EmailBizServiceImpl.*(..))")
    public void servicePointCut() {
    }

    @AfterThrowing(value = "servicePointCut()", throwing = "e")
    public void sendExceptionByMail(JoinPoint joinPoint, Exception e) {
        String name = joinPoint.getSignature().getName();
        List<Object> args = new ArrayList<>();
        if (joinPoint.getArgs() != null) {
            args = Arrays.asList(joinPoint.getArgs());
        }

        log.error("===>> [ExceptionAOPHandler]Exception occurs,  method: {}", name);
        log.error("===>> exception: \r\n", e);
        StringBuilder msg = new StringBuilder(String.format("===>> [ExceptionAOPHandler]Exception occurs,  method: %s\r\n", name));
        msg.append(e.toString()).append(":").append(e.getMessage()).append("\r\n");
        msg.append(log(e));
        try {
            String isExceptionEmailNotify = webconfBizService.queryWebconfByKey(WebConfDataConfig.EXCEPTION_EMAIL_NOTIFY);
            if (Constants.TRUE.equals(isExceptionEmailNotify)) {
                TaskDataD0099BO taskDataD0099BO = new TaskDataD0099BO();
                taskDataD0099BO.setExcepionMsg(msg.toString());
                createTask(taskDataD0099BO);
            }
        } catch (JwBlogException ex) {
            log.error("ExceptionAOPHandler.sendExceptionByMail.queryWebconfByKey error:", ex);
        }


    }

    private void createTask(TaskDataD0099BO taskDataD0099BO) {
        transactionUtils.afterCompletion(() -> {
            log.info("===>> [ExceptionAOPHandler]createTask");
            Long taskId = null;
            try {

                taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0099.getValue(), JSONObject.toJSONString(taskDataD0099BO));
            } catch (JwBlogException ex) {
                log.error("\r\n>>MsgBaseServiceImpl.doCreateMsg exec failed, e\r\n", ex);
            }
            if (taskId != null) {
                log.info("MsgBaseServiceImpl.doTriggerTaskAfterCommit {}", taskId);
                asyncAutoTaskBaseService.doTriggerTask(taskId);
            }
        });

    }

    private String log(Throwable ex) {
        log.error("Exception request param:");
        StringBuilder msg = new StringBuilder();

        StackTraceElement[] error = ex.getStackTrace();
        for (StackTraceElement stackTraceElement : error) {
            msg.append(stackTraceElement.toString()).append("\r\n");

        }
//        log.error(msg.toString());
        return msg.toString();
    }

}
