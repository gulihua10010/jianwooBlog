package cn.jianwoo.blog.enums;

public enum CfgTypeEnum {

    /**
     * email
     */
    EMAIL("EMAIL"),
    /**
     * system
     */
    SYS("SYS"),
    /**
     * 七牛云
     */
    QINIUYUN("QINIUYUN"),

    ;


    /**
     * name
     */
    private String value;

    CfgTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
