package cn.jianwoo.blog.enums;

public enum MenuTypeEnum {

    /**
     * 前台
     */
    FRONTEND("10"),

    /**
     * 后台
     */
    BACKEND("20"),

    ;

    /**
     * value
     */
    private String value;

    MenuTypeEnum(String value) {
        this.value = value;
    }


    public static MenuTypeEnum getEnum(String name) {
        MenuTypeEnum[] arry = MenuTypeEnum.values();
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
