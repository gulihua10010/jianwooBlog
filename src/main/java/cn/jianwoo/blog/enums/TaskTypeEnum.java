package cn.jianwoo.blog.enums;

public enum TaskTypeEnum {
    /**
     * 邮件发送
     */
    D0010("D0010"),
    /**
     * 获取IP
     */
    D0020("D0020"),
    /**
     * 获取IP
     */
    D0099("D0099");


    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    TaskTypeEnum(String value) {
        this.value = value;
    }

    private String value;

}
