package cn.jianwoo.blog.enums;

public enum ValueTypeEnum {

    /**
     * 字符串
     */
    STRING("S"),

    /**
     * 浮点型
     */
    FLOAT("F"),


    /**
     * 整型
     */
    INTEGER("I"),

    /**
     * 日期
     */
    DATE("D"),

    /**
     * 布尔
     */
    BOOLEAN("B"),

    ;

    /**
     * value
     */
    private String value;

    ValueTypeEnum(String value) {
        this.value = value;
    }


    public static ValueTypeEnum getEnum(Integer name) {
        ValueTypeEnum[] arry = ValueTypeEnum.values();
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
