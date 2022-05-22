package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class LoginLogBizException extends JwBlogException {
    public static final LoginLogBizException NOT_EXIST_EXCEPTION = new LoginLogBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The login log[%s] doesn't exists.");
    public static final LoginLogBizException CREATE_FAILED_EXCEPTION = new LoginLogBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The login log[%s] creation is failed.");
    public static final LoginLogBizException MODIFY_FAILED_EXCEPTION = new LoginLogBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The login log[%s] modification is failed.");
    public static final LoginLogBizException DELETE_FAILED_EXCEPTION = new LoginLogBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The login log[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(LoginLogBizException.class);

    public LoginLogBizException() {
    }


    public LoginLogBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public LoginLogBizException(String code, String msg) {
        super(code, msg);
    }


    public LoginLogBizException(String message) {
        super(message);
    }


    public LoginLogBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public LoginLogBizException(Throwable cause) {
        super(cause);
    }


    public LoginLogBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public LoginLogBizException print() {
        logger.warn("==>LoginLogBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public LoginLogBizException format(Object... args) {
        return new LoginLogBizException(this.code, this.msg, args);
    }
}
