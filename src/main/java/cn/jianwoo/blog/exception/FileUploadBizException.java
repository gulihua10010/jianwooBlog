package cn.jianwoo.blog.exception;

import cn.jianwoo.blog.constants.ExceptionConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 14:56
 */
public class FileUploadBizException extends JwBlogException {
    public static final FileUploadBizException HAS_EXIST_EXCEPTION = new FileUploadBizException(
            ExceptionConstants.BIZ_HAS_EXIST, "The fileUpload[%s] already exists.");
    public static final FileUploadBizException NOT_EXIST_EXCEPTION = new FileUploadBizException(
            ExceptionConstants.BIZ_NOT_EXIST, "The fileUpload[%s] does not exist.");
    public static final FileUploadBizException CREATE_FAILED_EXCEPTION = new FileUploadBizException(
            ExceptionConstants.BIZ_CREATE_FAIL, "The fileUpload[%s] creation is failed.");
    public static final FileUploadBizException MODIFY_FAILED_EXCEPTION = new FileUploadBizException(
            ExceptionConstants.BIZ_MODIFY_FAIL, "The fileUpload[%s] modification is failed.");
    public static final FileUploadBizException DELETE_FAILED_EXCEPTION = new FileUploadBizException(
            ExceptionConstants.BIZ_DELETE_FAIL, "The fileUpload[%s] deletion is failed.");
    private static final long serialVersionUID = -4477787493913372810L;
    private final Logger logger = LoggerFactory.getLogger(FileUploadBizException.class);

    public FileUploadBizException() {
    }


    public FileUploadBizException(String code, String msg, Object... args) {
        super(code, msg, args);
    }


    public FileUploadBizException(String code, String msg) {
        super(code, msg);
    }


    public FileUploadBizException(String message) {
        super(message);
    }


    public FileUploadBizException(String message, Throwable cause) {
        super(message, cause);
    }


    public FileUploadBizException(Throwable cause) {
        super(cause);
    }


    public FileUploadBizException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    @Override
    public FileUploadBizException print() {
        logger.warn("==>WebconfBizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }


    @Override
    public FileUploadBizException format(Object... args) {
        return new FileUploadBizException(this.code, this.msg, args);
    }

}
