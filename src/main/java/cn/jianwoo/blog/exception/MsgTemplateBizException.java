package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class MsgTemplateBizException extends JwBlogException {
    public static final MsgTemplateBizException HAS_EXIST_EXCEPTION = new MsgTemplateBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The msg Template[%s] already exists.");
    public static final MsgTemplateBizException NOT_EXIST_EXCEPTION = new MsgTemplateBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The msg Template[%s] does not exist.");
    public static final MsgTemplateBizException CREATE_FAILED_EXCEPTION = new MsgTemplateBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The msg Template[%s] creation is failed.");
    public static final MsgTemplateBizException MODIFY_FAILED_EXCEPTION = new MsgTemplateBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The msg Template[%s] modification is failed.");
    public static final MsgTemplateBizException DELETE_FAILED_EXCEPTION = new MsgTemplateBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The msg Template[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(MsgTemplateBizException.class);

    public MsgTemplateBizException() {
    }


    public MsgTemplateBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public MsgTemplateBizException(String code, String msg) {
        super(code, msg);
    }


    public MsgTemplateBizException(String message) {
        super(message);
    }


    public MsgTemplateBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public MsgTemplateBizException(Throwable cause) {
        super(cause);
    }


    public MsgTemplateBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public MsgTemplateBizException print() {
        logger.warn("==>MsgTemplateBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public MsgTemplateBizException format(Object... args) {
        return new MsgTemplateBizException(this.code, this.msg, args);
    }
}
