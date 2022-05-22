package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class ArticleAccessBizException extends JwBlogException {
    public static final ArticleAccessBizException NOT_EXIST_EXCEPTION = new ArticleAccessBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The Article access[%s] doesn't exists.");
    public static final ArticleAccessBizException CREATE_FAILED_EXCEPTION = new ArticleAccessBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The Article access[artirleOid=%s, ip=%s] creation is failed.");
    public static final ArticleAccessBizException MODIFY_FAILED_EXCEPTION = new ArticleAccessBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The Article access[%s] modification is failed.");
    public static final ArticleAccessBizException DELETE_FAILED_EXCEPTION = new ArticleAccessBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The Article access[%s] deletion is failed.");

    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(ArticleAccessBizException.class);

    public ArticleAccessBizException() {
    }


    public ArticleAccessBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public ArticleAccessBizException(String code, String msg) {
        super(code, msg);
    }


    public ArticleAccessBizException(String message) {
        super(message);
    }


    public ArticleAccessBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public ArticleAccessBizException(Throwable cause) {
        super(cause);
    }


    public ArticleAccessBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public ArticleAccessBizException print() {
        logger.warn("==>ArticleAccessBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public ArticleAccessBizException format(Object... args) {
        return new ArticleAccessBizException(this.code, this.msg, args);
    }
}
