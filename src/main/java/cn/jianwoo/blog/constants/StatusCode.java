package cn.jianwoo.blog.constants;

public enum StatusCode {
    /**
     * 成功
     */
    SUCCESS("000000", "SUCCESS"),
    /**
     * 失败
     */
    FAILED("999999", "SYSTEM ERROR"),
    /**
     * 参数无效
     */
    INVALID_PARAMS("100001", "INVALID PARAMS");

    private String status;
    private String msg;

    StatusCode(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    StatusCode() {

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
