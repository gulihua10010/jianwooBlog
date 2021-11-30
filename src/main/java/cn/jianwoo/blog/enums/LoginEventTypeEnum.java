package cn.jianwoo.blog.enums;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:10
 */
public enum LoginEventTypeEnum {


    /**
     * 登录
     */
    LOGIN("10", "登录"),

    /**
     * 退出
     */
    LOGOUT("20", "退出"),
    /**
     * 修改密码
     */
    CHANGE_PASSWORD("30", "修改密码"),
    ;

    /**
     * value
     */
    private String value;

    /**
     * 描述
     */
    private String desc;

    LoginEventTypeEnum(String value) {
        this.value = value;
    }

    LoginEventTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static String descOfValue(String value) {
        LoginEventTypeEnum[] arry = LoginEventTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(value)) {
                return arry[i].desc;
            }
        }
        return null;
    }
}
