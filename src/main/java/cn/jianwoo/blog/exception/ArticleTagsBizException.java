package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class ArticleTagsBizException extends JwBlogException {
    public static final ArticleTagsBizException HAS_EXIST_EXCEPTION = new ArticleTagsBizException(
            ExceptionConstants.BIZ_HAS_EXIST, "The article[%s] tags already exists.");
    public static final ArticleTagsBizException NOT_EXIST_EXCEPTION = new ArticleTagsBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The article[%s] tags does not exist.");
    public static final ArticleTagsBizException CREATE_FAILED_EXCEPTION = new ArticleTagsBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The article[%s] tags creation is failed.");
    public static final ArticleTagsBizException MODIFY_FAILED_EXCEPTION = new ArticleTagsBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The article[%s] tags modification is failed.");
    public static final ArticleTagsBizException DELETE_FAILED_EXCEPTION = new ArticleTagsBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The article[%s] tags deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(ArticleTagsBizException.class);

    public ArticleTagsBizException() {
    }


    public ArticleTagsBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public ArticleTagsBizException(String code, String msg) {
        super(code, msg);
    }


    public ArticleTagsBizException(String message) {
        super(message);
    }


    public ArticleTagsBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public ArticleTagsBizException(Throwable cause) {
        super(cause);
    }


    public ArticleTagsBizException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public ArticleTagsBizException print() {
        logger.warn("==>ArticleTagsBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public ArticleTagsBizException format(Object... args) {
        return new ArticleTagsBizException(this.code, this.msg, args);
    }
}
