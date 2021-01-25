package cn.jianwoo.blog.base;

import cn.jianwoo.blog.constants.StatusCode;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

public class BaseResponseDto implements Serializable {
    private static final long serialVersionUID = 8005270127305292401L;

    private static final String SUCCESS_REQ_CODE = "000000";
    private static final String SUCCESS_REQ_MSG = "SUCCESS";
    public static final BaseResponseDto SUCCESS = new BaseResponseDto(SUCCESS_REQ_CODE, SUCCESS_REQ_MSG);
    private static final String FAIL_REQ_CODE = "999999";
    private static final String FAIL_REQ_MSG = "SYSTEM ERROR";
    public static final BaseResponseDto SYSTEM_ERROR = new BaseResponseDto(FAIL_REQ_CODE, FAIL_REQ_MSG);
    private String status;
    private String msg;

    public static BaseResponseDto buildResponse(String status, String msg) {
        return new BaseResponseDto(status, msg);
    }

    public BaseResponseDto(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    public BaseResponseDto() {
        this.status = SUCCESS_REQ_CODE;
        this.msg = SUCCESS_REQ_MSG;
    }


    public BaseResponseDto(String msg) {
        this.status = SUCCESS_REQ_CODE;
        this.msg = msg;
    }


    public static BaseResponseDto success() {
        return new BaseResponseDto();
    }


    public static BaseResponseDto error(String msg) {
        return new BaseResponseDto(FAIL_REQ_CODE, msg);
    }


    public static BaseResponseDto error() {
        return new BaseResponseDto(FAIL_REQ_CODE, FAIL_REQ_MSG);
    }


    public static BaseResponseDto invalidParams() {
        return new BaseResponseDto(StatusCode.INVALID_PARAMS.getStatus(), StatusCode.INVALID_PARAMS.getMsg());
    }


    public BaseResponseDto success(String msg) {
        return new BaseResponseDto(msg);
    }


    public Boolean isSuccess() {
        return SUCCESS_REQ_CODE.equals(this.status);
    }


    @Override
    public String toString() {
        try {
            return BeanUtils.describe(this).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }
}
