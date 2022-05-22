package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class AnnounceBizException extends JwBlogException {
    public static final AnnounceBizException HAS_EXIST_EXCEPTION = new AnnounceBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The announcement msg[%s] already exists.");
    public static final AnnounceBizException NOT_EXIST_EXCEPTION_CN = new AnnounceBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "该公告不存在!");

    public static final AnnounceBizException HAS_VOID_EXCEPTION_CN = new AnnounceBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "该公告已经作废!");

    public static final AnnounceBizException NOT_VOID_EXCEPTION_CN = new AnnounceBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "该公告还未作废!");
    public static final AnnounceBizException NOT_EXIST_EXCEPTION = new AnnounceBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The announcement msg[%s] does not exist.");
    public static final AnnounceBizException CREATE_FAILED_EXCEPTION = new AnnounceBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The announcement msg[%s] creation is failed.");
    public static final AnnounceBizException MODIFY_FAILED_EXCEPTION = new AnnounceBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The announcement msg[%s] modification is failed.");
    public static final AnnounceBizException DELETE_FAILED_EXCEPTION = new AnnounceBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The announcement msg[%s] deletion is failed.");
    public static final AnnounceBizException TPL_RENDER_FAILED_CN = new AnnounceBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "邮件模板渲染失败,请检查模板参数");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(AnnounceBizException.class);

    public AnnounceBizException() {
    }


    public AnnounceBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public AnnounceBizException(String code, String msg) {
        super(code, msg);
    }


    public AnnounceBizException(String message) {
        super(message);
    }


    public AnnounceBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public AnnounceBizException(Throwable cause) {
        super(cause);
    }


    public AnnounceBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public AnnounceBizException print() {
        logger.warn("==>AnnounceBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public AnnounceBizException format(Object... args) {
        return new AnnounceBizException(this.code, this.msg, args);
    }
}
