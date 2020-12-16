package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class AdminBizException extends JwBlogException {
    public static final AdminBizException HAS_EXIST_EXCEPTION = new AdminBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The admin[%s] has been registered.");
    public static final AdminBizException NOT_EXIST_EXCEPTION = new AdminBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The admin[%s] does not exist.");
    public static final AdminBizException CREATE_FAILED_EXCEPTION = new AdminBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The admin[%s] creation is failed.");
    public static final AdminBizException MODIFY_FAILED_EXCEPTION = new AdminBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The admin[%s] modification is failed.");
    public static final AdminBizException DELETE_FAILED_EXCEPTION = new AdminBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The admin[%s] deletion is failed.");
    public static final AdminBizException PASSWORD_NOT_CORRECT_EXCEPTION = new AdminBizException(
            ExceptionConstants.ADMIN_PASSWORD_NOT_CORRECT, "The admin[%s] password is not correct.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(AdminBizException.class);

    public AdminBizException() {
    }


    public AdminBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public AdminBizException(String code, String msg) {
        super(code, msg);
    }


    public AdminBizException(String message) {
        super(message);
    }


    public AdminBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public AdminBizException(Throwable cause) {
        super(cause);
    }


    public AdminBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public AdminBizException print() {
        logger.warn("==>AdminBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public AdminBizException format(Object... args) {
        return new AdminBizException(this.code, this.msg, args);
    }
}
