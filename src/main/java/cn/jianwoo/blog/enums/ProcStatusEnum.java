package cn.jianwoo.blog.enums;

public enum ProcStatusEnum {

    /**
     * 还未发送
     */
    INIT("00"),

    /**
     * 发送中
     */
    SENDING("10"),

    /**
     * 发送成功
     */
    SUCCESS("90"),

    /**
     * 发送失败
     */
    FAILED("91"),

    ;

    /**
     * value
     */
    private String value;

    ProcStatusEnum(String value) {
        this.value = value;
    }


    public static ProcStatusEnum getEnum(Integer name) {
        ProcStatusEnum[] arry = ProcStatusEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equals(name)) {
                return arry[i];
            }
        }
        return null;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

}
