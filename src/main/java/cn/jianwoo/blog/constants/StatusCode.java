package cn.jianwoo.blog.constants;

public enum StatusCode {
    Success("000000", "SUCCESS"),
    Fail("999999", "SYSTEM ERROR"),
    InvalidParams("100001", "INVALID PARAMS");

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
