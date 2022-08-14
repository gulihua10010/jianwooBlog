package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class BizPraiseBizException extends JwBlogException {
    public static final BizPraiseBizException NOT_EXIST_EXCEPTION = new BizPraiseBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The Biz praise[%s] doesn't exists.");
    public static final BizPraiseBizException CREATE_FAILED_EXCEPTION = new BizPraiseBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The Biz praise[%s] creation is failed.");
    public static final BizPraiseBizException MODIFY_FAILED_EXCEPTION = new BizPraiseBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The Biz praise[%s] modification is failed.");
    public static final BizPraiseBizException DELETE_FAILED_EXCEPTION = new BizPraiseBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The Biz praise[%s] deletion is failed.");

    public static final ArticleBizException PRAISE_ARTICLE_ALREADY = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "你已经赞过了该文章!");
    public static final ArticleBizException PRAISE_COMMENT_ALREADY = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "你已经赞过了该评论!");
    public static final ArticleBizException PRAISE_MESSAGE_ALREADY = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "你已经赞过了该留言!");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(BizPraiseBizException.class);

    public BizPraiseBizException() {
    }


    public BizPraiseBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public BizPraiseBizException(String code, String msg) {
        super(code, msg);
    }


    public BizPraiseBizException(String message) {
        super(message);
    }


    public BizPraiseBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public BizPraiseBizException(Throwable cause) {
        super(cause);
    }


    public BizPraiseBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public BizPraiseBizException print() {
        logger.warn("==>TagsBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public BizPraiseBizException format(Object... args) {
        return new BizPraiseBizException(this.code, this.msg, args);
    }
}
