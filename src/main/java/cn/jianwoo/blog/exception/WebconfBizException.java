package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class WebconfBizException extends JwBlogException {
    public static final WebconfBizException HAS_EXIST_EXCEPTION = new WebconfBizException(
            ExceptionConstants.BIZ_HAS_EXIST, "The webconf[%s] already exists.");
    public static final WebconfBizException NOT_EXIST_EXCEPTION = new WebconfBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The webconf[%s] does not exist.");
    public static final WebconfBizException CREATE_FAILED_EXCEPTION = new WebconfBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The webconf[%s] creation is failed.");
    public static final WebconfBizException MODIFY_FAILED_EXCEPTION = new WebconfBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The webconf[%s] modification is failed.");
    public static final WebconfBizException DELETE_FAILED_EXCEPTION = new WebconfBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The webconf[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(WebconfBizException.class);

    public WebconfBizException() {
    }


    public WebconfBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public WebconfBizException(String code, String msg) {
        super(code, msg);
    }


    public WebconfBizException(String message) {
        super(message);
    }


    public WebconfBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public WebconfBizException(Throwable cause) {
        super(cause);
    }


    public WebconfBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public WebconfBizException print() {
        logger.warn("==>WebconfBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public WebconfBizException format(Object... args) {
        return new WebconfBizException(this.code, this.msg, args);
    }
}
