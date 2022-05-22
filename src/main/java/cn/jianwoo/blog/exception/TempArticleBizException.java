package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class TempArticleBizException extends JwBlogException {
    public static final TempArticleBizException NOT_EXIST_EXCEPTION = new TempArticleBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The temp article[%s] doesn't exists.");
    public static final TempArticleBizException NOT_EXIST_EXCEPTION_CN = new TempArticleBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "临时文章缓存[%s]不存在.");
    public static final TempArticleBizException STATUS_NOT_TEMP_CN = new TempArticleBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "临时文章缓存[%s]的状态不是有效的.");
    public static final TempArticleBizException STATUS_VOID_CN = new TempArticleBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "临时文章缓存[%s]已经作废!");
    public static final TempArticleBizException STATUS_RESTORE_CN = new TempArticleBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "临时文章缓存[%s]已经恢复!");
    public static final TempArticleBizException CREATE_FAILED_EXCEPTION = new TempArticleBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The temp article[%s] creation is failed.");
    public static final TempArticleBizException MODIFY_FAILED_EXCEPTION = new TempArticleBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The temp article[%s] modification is failed.");
    public static final TempArticleBizException DELETE_FAILED_EXCEPTION = new TempArticleBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The temp article[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(TempArticleBizException.class);

    public TempArticleBizException() {
    }


    public TempArticleBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public TempArticleBizException(String code, String msg) {
        super(code, msg);
    }


    public TempArticleBizException(String message) {
        super(message);
    }


    public TempArticleBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public TempArticleBizException(Throwable cause) {
        super(cause);
    }


    public TempArticleBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public TempArticleBizException print() {
        logger.warn("==>TempArticleBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public TempArticleBizException format(Object... args) {
        return new TempArticleBizException(this.code, this.msg, args);
    }
}
