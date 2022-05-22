package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class MsgProfileBizException extends JwBlogException {
    public static final MsgProfileBizException HAS_EXIST_EXCEPTION = new MsgProfileBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The msg Profile[%s] already exists.");
    public static final MsgProfileBizException NOT_EXIST_EXCEPTION = new MsgProfileBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The msg Profile[%s] does not exist.");
    public static final MsgProfileBizException CREATE_FAILED_EXCEPTION = new MsgProfileBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The msg Profile[%s] creation is failed.");
    public static final MsgProfileBizException MODIFY_FAILED_EXCEPTION = new MsgProfileBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The msg Profile[%s] modification is failed.");
    public static final MsgProfileBizException DELETE_FAILED_EXCEPTION = new MsgProfileBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The msg Profile[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(MsgProfileBizException.class);

    public MsgProfileBizException() {
    }


    public MsgProfileBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public MsgProfileBizException(String code, String msg) {
        super(code, msg);
    }


    public MsgProfileBizException(String message) {
        super(message);
    }


    public MsgProfileBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public MsgProfileBizException(Throwable cause) {
        super(cause);
    }


    public MsgProfileBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public MsgProfileBizException print() {
        logger.warn("==>MsgProfileBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public MsgProfileBizException format(Object... args) {
        return new MsgProfileBizException(this.code, this.msg, args);
    }
}
