package cn.jianwoo.blog.enums;

public enum FormTypeEnum {

    /**
     * 文本输入框 input[type='text']
     */
    INPUT_TEXT("INPUT_TEXT"),

    /**
     * 文本框 textarea
     */
    TEXTAREA("TEXTAREA"),

    /**
     * 图片上传按钮 input[type='file']
     */
    INPUT_FILE_IMAGE("INPUT_FILE_IMAGE"),

    /**
     * 数字输入框  input[type='text'](number)
     */
    INPUT_TEXT_NUMBER("INPUT_TEXT_NUMBER"),

    /**
     * checkBox 开关  input[type='checkbox']
     */
    INPUT_CHECKBOX("INPUT_CHECKBOX"),

    /**
     * select选择框 select
     */
    SELECT("SELECT"),

    /**
     * 单选框  input[type='radio']
     */
    RADIO("RADIO"),
    ;

    /**
     * value
     */
    private String value;

    FormTypeEnum(String value) {
        this.value = value;
    }


    public static FormTypeEnum getEnum(Integer name) {
        FormTypeEnum[] arry = FormTypeEnum.values();
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
