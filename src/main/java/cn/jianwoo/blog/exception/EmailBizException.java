package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class EmailBizException extends JwBlogException {
    public static final EmailBizException NOT_EXIST_EXCEPTION = new EmailBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The email[%s] does not exist.");
    public static final EmailBizException NOT_EXIST_EXCEPTION_CN = new EmailBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "邮件[%s]不存在!");
    public static final EmailBizException CREATE_FAILED_EXCEPTION = new EmailBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The email[%s] creation is failed.");
    public static final EmailBizException MODIFY_FAILED_EXCEPTION = new EmailBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The email[%s] modification is failed.");
    public static final EmailBizException DELETE_FAILED_EXCEPTION = new EmailBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The email[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(EmailBizException.class);

    public EmailBizException() {
    }


    public EmailBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public EmailBizException(String code, String msg) {
        super(code, msg);
    }


    public EmailBizException(String message) {
        super(message);
    }


    public EmailBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public EmailBizException(Throwable cause) {
        super(cause);
    }


    public EmailBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public EmailBizException print() {
        logger.warn("==>EmailBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public EmailBizException format(Object... args) {
        return new EmailBizException(this.code, this.msg, args);
    }
}
