package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class BlackIpBizException extends JwBlogException {
    public static final BlackIpBizException NOT_EXIST_EXCEPTION = new BlackIpBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The black ip[%s] does not exist.");
    public static final BlackIpBizException HAS_EXIST_EXCEPTION_CN = new BlackIpBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "黑名单IP[%s]已经存在, 不需要重复添加!");
    public static final BlackIpBizException CREATE_FAILED_EXCEPTION = new BlackIpBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The black ip[%s] creation is failed.");
    public static final BlackIpBizException MODIFY_FAILED_EXCEPTION = new BlackIpBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The black ip[%s] modification is failed.");
    public static final BlackIpBizException DELETE_FAILED_EXCEPTION = new BlackIpBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The black ip[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(BlackIpBizException.class);

    public BlackIpBizException() {
    }


    public BlackIpBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public BlackIpBizException(String code, String msg) {
        super(code, msg);
    }


    public BlackIpBizException(String message) {
        super(message);
    }


    public BlackIpBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public BlackIpBizException(Throwable cause) {
        super(cause);
    }


    public BlackIpBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public BlackIpBizException print() {
        logger.warn("==>BlackIpBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public BlackIpBizException format(Object... args) {
        return new BlackIpBizException(this.code, this.msg, args);
    }
}
