package cn.jianwoo.blog.exception.hander;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.task.bo.TaskDataD0099BO;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;

/**
 * 全局异常处理
 *
 * @author gulihua
 * @Description
 * @date 2022-05-09 16:18
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    protected HttpServletRequest httpServletRequest;
    @Autowired
    private WebconfBizService webconfBizService;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (status != null && status.equals(HttpStatus.NOT_FOUND)) {
            log.info("404错误" + request.getContextPath());
            return new ResponseEntity<>(new BaseResponseDto(String.valueOf(HttpStatus.NOT_FOUND.value()), "资源不存在"), HttpStatus.NOT_FOUND);
        }
        log(e, request, body);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(JSONObject.toJSONString(BaseResponseDto.SYSTEM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("url:[").append(request.getContextPath()).append("]")
                .append("<br/>")
                .append("ip:[").append(JwUtil.getRealIpAddress(httpServletRequest)).append("]")
                .append("<br/>").append(sw);
        try {
            String isExceptionEmailNotify = webconfBizService.queryWebconfByKey(WebConfDataConfig.EXCEPTION_EMAIL_NOTIFY);
            if (Constants.TRUE.equals(isExceptionEmailNotify)) {
                TaskDataD0099BO taskDataD0099BO = new TaskDataD0099BO();
                taskDataD0099BO.setExcepionMsg(sb.toString());
                createTask(taskDataD0099BO);
            }
        } catch (JwBlogException ex) {
            log.error("ExceptionAOPHandler.sendExceptionByMail.queryWebconfByKey error:", ex);
        }
        return new ResponseEntity<>(JSONObject.toJSONString(BaseResponseDto.SYSTEM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
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

    @ExceptionHandler
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest request, Throwable e) {
        log.error("GlobalDefaultExceptionHandler catch exception.", e);
        log(e, request);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        TaskDataD0099BO taskDataD0099BO = new TaskDataD0099BO();
        taskDataD0099BO.setExcepionMsg(sw.toString());
        createTask(taskDataD0099BO);

        return JSON.toJSONString(BaseResponseDto.SYSTEM_ERROR);
    }


    private void log(Throwable ex, HttpServletRequest request) {
        log.error("Exception url：" + request.getRequestURL());
        Enumeration enumeration = request.getParameterNames();
        log.error("Exception request param");
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement().toString();
            log.error(name + "---" + request.getParameter(name));
        }

//        StackTraceElement[] error = ex.getStackTrace();
//        for (StackTraceElement stackTraceElement : error) {
//            log.error(stackTraceElement.toString());
//        }
    }

    private void log(Throwable ex, WebRequest request, Object body) {
        log.error("Exception url：" + request.getContextPath());
        log.error("Exception request body：" + JSON.toJSONString(body));
//        StackTraceElement[] error = ex.getStackTrace();
//        for (StackTraceElement stackTraceElement : error) {
//            log.error(stackTraceElement.toString());
//        }
    }
}
