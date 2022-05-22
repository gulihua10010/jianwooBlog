package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class UserProfileBizException extends JwBlogException {
    public static final UserProfileBizException NOT_EXIST_EXCEPTION = new UserProfileBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The user profile[%s] doesn't exists.");
    public static final UserProfileBizException CREATE_FAILED_EXCEPTION = new UserProfileBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The user profile[%s] creation is failed.");
    public static final UserProfileBizException MODIFY_FAILED_EXCEPTION = new UserProfileBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The user profile[%s] modification is failed.");
    public static final UserProfileBizException DELETE_FAILED_EXCEPTION = new UserProfileBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The user profile[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(UserProfileBizException.class);

    public UserProfileBizException() {
    }


    public UserProfileBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public UserProfileBizException(String code, String msg) {
        super(code, msg);
    }


    public UserProfileBizException(String message) {
        super(message);
    }


    public UserProfileBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public UserProfileBizException(Throwable cause) {
        super(cause);
    }


    public UserProfileBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public UserProfileBizException print() {
        logger.warn("==>UserProfileBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public UserProfileBizException format(Object... args) {
        return new UserProfileBizException(this.code, this.msg, args);
    }
}
