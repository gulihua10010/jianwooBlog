package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class AsyncProcTaskBizException extends JwBlogException {
    public static final AsyncProcTaskBizException HAS_EXIST_EXCEPTION = new AsyncProcTaskBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The Async Task[%s] already exists.");
    public static final AsyncProcTaskBizException NOT_EXIST_EXCEPTION = new AsyncProcTaskBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The Async Task[%s] does not exist.");
    public static final AsyncProcTaskBizException CREATE_FAILED_EXCEPTION = new AsyncProcTaskBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The Async Task[%s] creation is failed.");
    public static final AsyncProcTaskBizException MODIFY_FAILED_EXCEPTION = new AsyncProcTaskBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The Async Task[%s] modification is failed.");
    public static final AsyncProcTaskBizException DELETE_FAILED_EXCEPTION = new AsyncProcTaskBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The Async Task[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(AsyncProcTaskBizException.class);

    public AsyncProcTaskBizException() {
    }


    public AsyncProcTaskBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public AsyncProcTaskBizException(String code, String msg) {
        super(code, msg);
    }


    public AsyncProcTaskBizException(String message) {
        super(message);
    }


    public AsyncProcTaskBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public AsyncProcTaskBizException(Throwable cause) {
        super(cause);
    }


    public AsyncProcTaskBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public AsyncProcTaskBizException print() {
        logger.warn("==>AnnounceBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public AsyncProcTaskBizException format(Object... args) {
        return new AsyncProcTaskBizException(this.code, this.msg, args);
    }
}
