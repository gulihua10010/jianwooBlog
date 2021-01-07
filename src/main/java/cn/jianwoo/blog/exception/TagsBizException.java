package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class TagsBizException extends JwBlogException {
    public static final TagsBizException HAS_EXIST_EXCEPTION = new TagsBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The tags[%s] already exists.");
    public static final TagsBizException HAS_EXIST_EXCEPTION_CN = new TagsBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "标签[%s]已经存在.");
    public static final TagsBizException HAS_EXIST_EXCEPTION_LIST_CN = new TagsBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "标签列表中%s已经存在.");
    public static final TagsBizException NOT_EXIST_EXCEPTION = new TagsBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The tags[%s] does not exist.");
    public static final TagsBizException CREATE_FAILED_EXCEPTION = new TagsBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The tags[%s] creation is failed.");
    public static final TagsBizException MODIFY_FAILED_EXCEPTION = new TagsBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The tags[%s] modification is failed.");
    public static final TagsBizException DELETE_FAILED_EXCEPTION = new TagsBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The tags[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(TagsBizException.class);

    public TagsBizException() {
    }


    public TagsBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public TagsBizException(String code, String msg) {
        super(code, msg);
    }


    public TagsBizException(String message) {
        super(message);
    }


    public TagsBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public TagsBizException(Throwable cause) {
        super(cause);
    }


    public TagsBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public TagsBizException print() {
        logger.warn("==>TagsBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public TagsBizException format(Object... args) {
        return new TagsBizException(this.code, this.msg, args);
    }
}
