package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class CommentBizException extends JwBlogException {
    public static final CommentBizException HAS_EXIST_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_HAS_EXIST, "The comment[%s] already exists.");
    public static final CommentBizException NOT_EXIST_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The comment[%s] does not exist.");
    public static final CommentBizException NOT_EXIST_EXCEPTION_CN = new CommentBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "评论[%s]不存在!");
    public static final CommentBizException THIS_COMMENT_NOT_EXIST_EXCEPTION_CN = new CommentBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "该评论不存在!");
    //Operation without permission
    public static final CommentBizException OPERATION_WITHOUT_PERMISSION = new CommentBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "您没有权限操作!拒绝访问!");
    public static final CommentBizException ARTICLE_NOT_SAME = new CommentBizException(
            ExceptionConstants.BIZ_NOT_MATCH, "修改评论的文章与之前的不一致!");
    public static final CommentBizException FREQUENCY_HIGH_CN = new CommentBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "系统检测到您评论的频率过高, 请稍后再试!");

    public static final CommentBizException MORE_THAN_MAX_COMMENTS_ONE_DAY = new CommentBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "您今天最多可以发%s条评论, 请明天再试!");

    public static final CommentBizException BLOG_NOT_ALLOW_COMMENT_EXCEPTION_CN = new CommentBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "网站的评论系统已经关闭!");
    public static final CommentBizException NOT_EXISTS_REPLY_COMMENT_CN = new CommentBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "所回复的评论不存在!");
    public static final CommentBizException HAS_DELETE_REPLY_COMMENT_CN = new CommentBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "所回复的评论已经删除!");

    public static final CommentBizException CREATE_FAILED_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The comment[%s] creation is failed.");
    public static final CommentBizException CREATE_FAILED_EXCEPTION_CN = new CommentBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "评论发表失败!");
    public static final CommentBizException MODIFY_FAILED_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The comment[%s] modification is failed.");
    public static final CommentBizException MODIFY_FAILED_EXCEPTION_CN = new CommentBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "评论修改失败");
    public static final CommentBizException DELETE_FAILED_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The comment[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(CommentBizException.class);

    public CommentBizException() {
    }


    public CommentBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public CommentBizException(String code, String msg) {
        super(code, msg);
    }


    public CommentBizException(String message) {
        super(message);
    }


    public CommentBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public CommentBizException(Throwable cause) {
        super(cause);
    }


    public CommentBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public CommentBizException print() {
        logger.warn("==>CommentBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public CommentBizException format(Object... args) {
        return new CommentBizException(this.code, this.msg, args);
    }
}
