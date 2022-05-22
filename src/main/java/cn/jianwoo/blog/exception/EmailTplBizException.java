package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class EmailTplBizException extends JwBlogException {
    public static final EmailTplBizException HAS_EXIST_EXCEPTION = new EmailTplBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The email template[%s] already exists.");
    public static final EmailTplBizException HAS_EXIST_EXCEPTION_CN = new EmailTplBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "邮件模板[%s]已经存在.");
    public static final EmailTplBizException NOT_EXIST_EXCEPTION = new EmailTplBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The email template[%s] does not exist.");
    public static final EmailTplBizException NOT_EXIST_EXCEPTION_CN = new EmailTplBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "邮件模板[%s]不存在!");
    public static final EmailTplBizException NOT_USED_EXCEPTION_CN = new EmailTplBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "邮件模板[%s]是不使用的!");
    public static final EmailTplBizException CREATE_FAILED_EXCEPTION = new EmailTplBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The email template[%s] creation is failed.");
    public static final EmailTplBizException MODIFY_FAILED_EXCEPTION = new EmailTplBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The email template[%s] modification is failed.");
    public static final EmailTplBizException DELETE_FAILED_EXCEPTION = new EmailTplBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The email template[%s] deletion is failed.");
    public static final EmailTplBizException TPL_RENDER_FAILED_CN = new EmailTplBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "邮件模板渲染失败,请检查模板参数");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(EmailTplBizException.class);

    public EmailTplBizException() {
    }


    public EmailTplBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public EmailTplBizException(String code, String msg) {
        super(code, msg);
    }


    public EmailTplBizException(String message) {
        super(message);
    }


    public EmailTplBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public EmailTplBizException(Throwable cause) {
        super(cause);
    }


    public EmailTplBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public EmailTplBizException print() {
        logger.warn("==>EmailTplBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public EmailTplBizException format(Object... args) {
        return new EmailTplBizException(this.code, this.msg, args);
    }
}
