package cn.jianwoo.blog.service.bo;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 19:45
 */
public class MenuValidateBO implements Serializable {
    private static final long serialVersionUID = 3728070239002639495L;
    /**
     * 是否验证成功 Y or N
     */
    private String isSuccess;

    /**
     * 返回消息 isSuccess=N时必填
     */
    private String resultMsg;

    public MenuValidateBO(String isSuccess, String resultMsg) {
        this.isSuccess = isSuccess;
        this.resultMsg = resultMsg;
    }


    public MenuValidateBO() {
    }


    public String getIsSuccess() {
        return isSuccess;
    }


    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }


    public String getResultMsg() {
        return resultMsg;
    }


    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
