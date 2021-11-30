package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class BizEventLogBizException extends JwBlogException {
    public static final BizEventLogBizException NOT_EXIST_EXCEPTION = new BizEventLogBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The biz event log[%s] doesn't exists.");
    public static final BizEventLogBizException CREATE_FAILED_EXCEPTION = new BizEventLogBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The biz event log[%s] creation is failed.");
    public static final BizEventLogBizException MODIFY_FAILED_EXCEPTION = new BizEventLogBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The biz event log[%s] modification is failed.");
    public static final BizEventLogBizException DELETE_FAILED_EXCEPTION = new BizEventLogBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The biz event log[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(BizEventLogBizException.class);

    public BizEventLogBizException() {
    }


    public BizEventLogBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public BizEventLogBizException(String code, String msg) {
        super(code, msg);
    }


    public BizEventLogBizException(String message) {
        super(message);
    }


    public BizEventLogBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public BizEventLogBizException(Throwable cause) {
        super(cause);
    }


    public BizEventLogBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public BizEventLogBizException print() {
        logger.warn("==>TagsBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public BizEventLogBizException format(Object... args) {
        return new BizEventLogBizException(this.code, this.msg, args);
    }
}
