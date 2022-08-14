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
    public static final ArticleBizException ARTICLE_NOT_EXISTS_EXCEPTION_CN = new ArticleBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "该文章不存在!");
    public static final ArticleBizException ARTICLE_NOT_ALLOW_COMMENT_EXCEPTION_CN = new ArticleBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "该文章不允许评论!");

    public static final ArticleBizException STATUS_NOT_SUPPORT = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "The status of article[%s] must be PUBLISHED or DRAFT.");
    public static final ArticleBizException STATUS_NOT_SUPPORT_CN = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]状态必须是已发布或者草稿!");
    public static final ArticleBizException HAS_DELETE_CN = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]已经删除!");
    public static final ArticleBizException STATUS_NOT_PUBLISHED_CN = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]还未发布!");
    public static final ArticleBizException PASSWORD_VERIFY_CN = new ArticleBizException(
            ExceptionConstants.BIZ_OPERATION_PASSWORD_VERIFY, "文章[%s]需要验证密码!");
    public static final ArticleBizException PASSWORD_INCORRECT_CN = new ArticleBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "文章[%s]验证失败, 密码不正确!");
    public static final ArticleBizException PRIVATE_NOT_OPERATION = new ArticleBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "您没有权限访问文章[%s]!");
    public static final ArticleBizException STATUS_NOT_DRAFT = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]还不是草稿!");
    public static final ArticleBizException STATUS_HAS_DRAFT = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]已经是草稿!");
    public static final ArticleBizException STATUS_HAS_RECYCLE = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]已经移除到回收站!");
    public static final ArticleBizException STATUS_HAS_PUBLISHED = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]已经发布!");
    public static final ArticleBizException STATUS_NOT_RECYCLE = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]已经移出回收站!");

    public static final ArticleBizException STATUS_NOT_CORRECT = new ArticleBizException(
            ExceptionConstants.BIZ_STATUS_INCORRECT, "文章[%s]状态不正确, 不是[%]状态!");
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
