package cn.jianwoo.blog.enums;

public enum CaptchaTypeEnum {

    /**
     * 登录页面
     */
    LOGIN("10"),

    /**
     * 忘记密码页面
     */
    FORGET_PASSWORD("20"),
    ;

    /**
     * value
     */
    private String value;

    CaptchaTypeEnum(String value) {
        this.value = value;
    }


    public static CaptchaTypeEnum getEnum(String name) {
        CaptchaTypeEnum[] arry = CaptchaTypeEnum.values();
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
