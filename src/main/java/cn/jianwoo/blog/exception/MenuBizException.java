package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class MenuBizException extends JwBlogException {

    public static final MenuBizException HAS_EXIST_EXCEPTION = new MenuBizException(ExceptionConstants.BIZ_HAS_EXIST,
            "The menu[%s] already exists.");
    public static final MenuBizException NOT_EXIST_EXCEPTION = new MenuBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "The menu[%s] does not exist.");
    public static final MenuBizException NOT_EXIST_EXCEPTION_CN = new MenuBizException(ExceptionConstants.BIZ_NOT_EXIST,
            "菜单[%s]不存在!");

    public static final MenuBizException MENU_DEL_ARTICLE_EXITS = new MenuBizException(ExceptionConstants.MENU_DEL_ARTICLE_EXITS,
            ExceptionConstants.MENU_DEL_ARTICLE_EXITS_DESC);
    public static final MenuBizException CREATE_FAILED_EXCEPTION = new MenuBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The menu[%s] creation is failed.");
    public static final MenuBizException MODIFY_FAILED_EXCEPTION = new MenuBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The menu[%s] modification is failed.");
    public static final MenuBizException DELETE_FAILED_EXCEPTION = new MenuBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The menu[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(MenuBizException.class);

    public MenuBizException() {
    }


    public MenuBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public MenuBizException(String code, String msg) {
        super(code, msg);
    }


    public MenuBizException(String message) {
        super(message);
    }


    public MenuBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public MenuBizException(Throwable cause) {
        super(cause);
    }


    public MenuBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public MenuBizException print() {
        logger.warn("==>MenuBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public MenuBizException format(Object... args) {
        return new MenuBizException(this.code, this.msg, args);
    }
}
