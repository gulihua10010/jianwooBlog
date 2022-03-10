package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class LoginFailedEventBizException extends JwBlogException {
    public static final LoginFailedEventBizException NOT_EXIST_EXCEPTION = new LoginFailedEventBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The login failed event[%s] doesn't exists.");
    public static final LoginFailedEventBizException CREATE_FAILED_EXCEPTION = new LoginFailedEventBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The login failed event creation is failed.");
    public static final LoginFailedEventBizException MODIFY_FAILED_EXCEPTION = new LoginFailedEventBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The login failed event[%s] modification is failed.");
    public static final LoginFailedEventBizException DELETE_FAILED_EXCEPTION = new LoginFailedEventBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The login failed event[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(LoginFailedEventBizException.class);

    public LoginFailedEventBizException() {
    }


    public LoginFailedEventBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public LoginFailedEventBizException(String code, String msg) {
        super(code, msg);
    }


    public LoginFailedEventBizException(String message) {
        super(message);
    }


    public LoginFailedEventBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public LoginFailedEventBizException(Throwable cause) {
        super(cause);
    }


    public LoginFailedEventBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public LoginFailedEventBizException print() {
        logger.warn("==>TagsBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public LoginFailedEventBizException format(Object... args) {
        return new LoginFailedEventBizException(this.code, this.msg, args);
    }
}
