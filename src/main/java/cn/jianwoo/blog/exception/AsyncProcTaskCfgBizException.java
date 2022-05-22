package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class AsyncProcTaskCfgBizException extends JwBlogException {
    public static final AsyncProcTaskCfgBizException HAS_EXIST_EXCEPTION = new AsyncProcTaskCfgBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The Async Task Cfg[%s] already exists.");
    public static final AsyncProcTaskCfgBizException NOT_EXIST_EXCEPTION = new AsyncProcTaskCfgBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The Async Task Cfg[%s] does not exist.");
    public static final AsyncProcTaskCfgBizException CREATE_FAILED_EXCEPTION = new AsyncProcTaskCfgBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The Async Task Cfg[%s] creation is failed.");
    public static final AsyncProcTaskCfgBizException MODIFY_FAILED_EXCEPTION = new AsyncProcTaskCfgBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The Async Task Cfg[%s] modification is failed.");
    public static final AsyncProcTaskCfgBizException DELETE_FAILED_EXCEPTION = new AsyncProcTaskCfgBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The Async Task Cfg[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(AsyncProcTaskCfgBizException.class);

    public AsyncProcTaskCfgBizException() {
    }


    public AsyncProcTaskCfgBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public AsyncProcTaskCfgBizException(String code, String msg) {
        super(code, msg);
    }


    public AsyncProcTaskCfgBizException(String message) {
        super(message);
    }


    public AsyncProcTaskCfgBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public AsyncProcTaskCfgBizException(Throwable cause) {
        super(cause);
    }


    public AsyncProcTaskCfgBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public AsyncProcTaskCfgBizException print() {
        logger.warn("==>AnnounceBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public AsyncProcTaskCfgBizException format(Object... args) {
        return new AsyncProcTaskCfgBizException(this.code, this.msg, args);
    }
}
