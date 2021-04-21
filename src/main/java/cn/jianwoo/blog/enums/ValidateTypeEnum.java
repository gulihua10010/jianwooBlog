package cn.jianwoo.blog.enums;

public enum ValidateTypeEnum {

    /**
     * 字符串最大长度验证
     */
    MAX_LENGTH("maxLength"),


    /**
     * 数字
     */
    NUMBER("number"),

    /**
     * 最小数值验证
     */
    MIN_NUM("minNum"),

    /**
     * 最大数值验证
     */
    MAX_NUM("maxNum"),

    /**
     * 正则验证
     */
    REGEX("regex"),

    ;

    /**
     * value
     */
    private String value;

    ValidateTypeEnum(String value) {
        this.value = value;
    }


    public static ValidateTypeEnum getEnum(Integer name) {
        ValidateTypeEnum[] arry = ValidateTypeEnum.values();
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
