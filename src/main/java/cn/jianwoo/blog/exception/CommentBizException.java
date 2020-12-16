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
    public static final CommentBizException CREATE_FAILED_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The comment[%s] creation is failed.");
    public static final CommentBizException MODIFY_FAILED_EXCEPTION = new CommentBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The comment[%s] modification is failed.");
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
