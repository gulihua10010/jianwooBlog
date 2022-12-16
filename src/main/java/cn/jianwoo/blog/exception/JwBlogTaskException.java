package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:21
 */
public class JwBlogTaskException extends Exception {
    public static final JwBlogTaskException SYSTEM_ERROR = new JwBlogTaskException(ExceptionConstants.SYSTEM_EXCEPTION,
            ExceptionConstants.SYSTEM_EXCEPTION_DESC);
    protected static final Logger logger = LoggerFactory.getLogger(JwBlogTaskException.class);
    private static final long serialVersionUID = -4668522971027224346L;
    protected String code;
    protected String msg;

    public JwBlogTaskException() {
    }


    public JwBlogTaskException(String code, String msg, Object... args) {
        super(args == null || args.length == 0 ? msg : String.format(msg, args));
        this.code = code;
        this.msg = args == null || args.length == 0 ? msg : String.format(msg, args);
    }


    public JwBlogTaskException(String message) {
        super(message);
        this.msg = message;
    }


    public JwBlogTaskException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;

    }


    public JwBlogTaskException(Throwable cause) {
        super(cause);
    }

    public JwBlogTaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = message;

    }


    public JwBlogTaskException getNewInstance(String code, String msg, Object... args) {
        return new JwBlogTaskException(code, msg, args);
    }

    public JwBlogTaskException formatMsg(String msg, Object... args) {
        return new JwBlogTaskException(code, msg, args);
    }


    public JwBlogTaskException print() {
        logger.error("======>  System Error, code : {},  msg : {}.", code, msg);
        return this;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public JwBlogTaskException format(Object... args) {
        return new JwBlogTaskException(this.code, this.msg, args);
    }
}
