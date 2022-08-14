package cn.jianwoo.blog.enums;

public enum ReceiverTypeEnum {

    /**
     * 博主
     */
    ADMIN("00"),

    /**
     * 用户
     */
    USER("10"),
    ;

    /**
     * value
     */
    private String value;

    ReceiverTypeEnum(String value) {
        this.value = value;
    }


    public static ReceiverTypeEnum getEnum(String name) {
        ReceiverTypeEnum[] arry = ReceiverTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].value.equals(name)) {
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
