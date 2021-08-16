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
    public static final ArticleBizException NOT_EXIST_EXCEPTION_CN = new ArticleBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "文章[ID=%s] 不存在.");
    public static final ArticleBizException CREATE_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The article[%s] creation is failed.");
    public static final ArticleBizException MODIFY_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The article[%s] modification is failed.");
    public static final ArticleBizException DELETE_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The article[%s] deletion is failed.");
    public static final ArticleBizException QUERY_FAILED_EXCEPTION = new ArticleBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The article[%s] query failed.");
    public static final ArticleBizException QUERY_FAILED_EXCEPTION_CN = new ArticleBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "文章[%s]查询失败.");
    public static final ArticleBizException STATUS_NOT_SUPPORT = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "The status of article[%s] must be PUBLISHED or DRAFT.");
    public static final ArticleBizException STATUS_NOT_SUPPORT_CN = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]状态必须是已发布或者草稿!");
    public static final ArticleBizException HAS_DELETE_CN = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]已经删除!");
    public static final ArticleBizException STATUS_NOT_PUBLISHED_CN = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]还未发布!");
    public static final ArticleBizException STATUS_NOT_DRAFT = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]还不是草稿!");
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
