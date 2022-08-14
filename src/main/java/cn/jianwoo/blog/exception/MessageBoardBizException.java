package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class MessageBoardBizException extends JwBlogException {
    public static final MessageBoardBizException HAS_EXIST_EXCEPTION = new MessageBoardBizException(
            ExceptionConstants.BIZ_HAS_EXIST, "The Message Board[%s] already exists.");
    public static final MessageBoardBizException NOT_EXIST_EXCEPTION = new MessageBoardBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The Message Board[%s] does not exist.");
    public static final MessageBoardBizException NOT_EXIST_EXCEPTION_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "留言[%s]不存在!");
    public static final MessageBoardBizException THIS_MESSAGE_NOT_EXIST_EXCEPTION_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "该留言不存在!");
    //Operation without permission
    public static final MessageBoardBizException OPERATION_WITHOUT_PERMISSION = new MessageBoardBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "您没有权限操作!拒绝访问!");
    public static final MessageBoardBizException FREQUENCY_HIGH_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "系统检测到您留言的频率过高, 请稍后再试!");

    public static final MessageBoardBizException MORE_THAN_MAX_MESSAGES_ONE_DAY = new MessageBoardBizException(
            ExceptionConstants.BIZ_OPERATION_FAILED, "您今天最多可以发%s条留言, 请明天再试!");

    public static final MessageBoardBizException BLOG_NOT_ALLOW_MESSAGE_EXCEPTION_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "网站的留言系统已经关闭!");
    public static final MessageBoardBizException NOT_EXISTS_REPLY_MESSAGE_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "所回复的留言不存在!");
    public static final MessageBoardBizException HAS_DELETE_REPLY_MESSAGE_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "所回复的留言已经删除!");

    public static final MessageBoardBizException CREATE_FAILED_EXCEPTION = new MessageBoardBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The Message Board[%s] creation is failed.");
    public static final MessageBoardBizException CREATE_FAILED_EXCEPTION_CN = new MessageBoardBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "留言发表失败!");
    public static final MessageBoardBizException MODIFY_FAILED_EXCEPTION = new MessageBoardBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The Message Board[%s] modification is failed.");
    public static final MessageBoardBizException DELETE_FAILED_EXCEPTION = new MessageBoardBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The Message Board[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(MessageBoardBizException.class);

    public MessageBoardBizException() {
    }


    public MessageBoardBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public MessageBoardBizException(String code, String msg) {
        super(code, msg);
    }


    public MessageBoardBizException(String message) {
        super(message);
    }


    public MessageBoardBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public MessageBoardBizException(Throwable cause) {
        super(cause);
    }


    public MessageBoardBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public MessageBoardBizException print() {
        logger.warn("==>MessageBoardBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public MessageBoardBizException format(Object... args) {
        return new MessageBoardBizException(this.code, this.msg, args);
    }
}
