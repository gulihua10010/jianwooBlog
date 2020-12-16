package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class ArticleBizException extends JwBlogException {
    public static final ArticleBizException HAS_EXIST_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_HAS_EXIST, "The article[%s] already exists.");
    public static final ArticleBizException NOT_EXIST_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The article[%s] does not exist.");
    public static final ArticleBizException CREATE_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The article[%s] creation is failed.");
    public static final ArticleBizException MODIFY_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The article[%s] modification is failed.");
    public static final ArticleBizException DELETE_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The article[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(ArticleBizException.class);

    public ArticleBizException() {
    }


    public ArticleBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public ArticleBizException(String code, String msg) {
        super(code, msg);
    }


    public ArticleBizException(String message) {
        super(message);
    }


    public ArticleBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public ArticleBizException(Throwable cause) {
        super(cause);
    }


    public ArticleBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public ArticleBizException print() {
        logger.warn("==>ArticleBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public ArticleBizException format(Object... args) {
        return new ArticleBizException(this.code, this.msg, args);
    }
}
