package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoException extends Exception {
    /**
     * database operation,insert result 0
     */
    public static final DaoException DAO_INSERT_RESULT_0 = new DaoException(ExceptionConstants.DAO_INSERT_RESULT_0,
            "database operation,insert result  0");
    /**
     * database operation,update result 0
     */
    public static final DaoException DAO_UPDATE_RESULT_0 = new DaoException(ExceptionConstants.DAO_UPDATE_RESULT_0,
            "database operation,update result  0");
    /**
     * database operation,delete result 0
     */
    public static final DaoException DAO_DELETE_RESULT_0 = new DaoException(ExceptionConstants.DAO_DELETE_RESULT_0,
            "database operation,delete result  0");
    /**
     * database operation,selectOne result null
     */
    public static final DaoException DAO_SELECTONE_IS_NULL = new DaoException(ExceptionConstants.DAO_SELECTONE_IS_NULL,
            "database operation,selectOne result  null");
    /**
     * database operation,list result null
     */
    public static final DaoException DAO_LIST_IS_NULL = new DaoException(ExceptionConstants.DAO_LIST_IS_NULL,
            "database operation,list result  null");
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DaoException.class);
    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected String code;

    public DaoException(String code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }


    public DaoException() {
        super();
    }


    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }


    public DaoException(Throwable cause) {
        super(cause);
    }


    public DaoException(String message) {
        super(message);
    }


    public String getMsg() {
        return msg;
    }


    public String getCode() {
        return code;
    }


    public DaoException newInstance(String msgFormat, Object... args) {
        return new DaoException(this.code, msgFormat, args);
    }


    public DaoException print() {
        logger.warn("==>DaoException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
