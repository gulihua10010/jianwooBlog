package cn.jianwoo.blog.enums;

public enum LoginFailedEventStatusEnum {

    /**
     * 有效
     */
    VALID("00"),
    /**
     * 废弃
     */
    VOID("91"),

    ;


    /**
     * value
     */
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    LoginFailedEventStatusEnum(String value) {
        this.value = value;
    }
}
