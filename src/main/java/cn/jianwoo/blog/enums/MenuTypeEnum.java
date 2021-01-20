package cn.jianwoo.blog.enums;

public enum MenuTypeEnum {

    /**
     * 前台
     */
    FRONTEND(1),

    /**
     * 后台
     */
    BACKEND(2),

    ;

    /**
     * value
     */
    private Integer value;

    MenuTypeEnum(Integer value) {
        this.value = value;
    }


    public static MenuTypeEnum getEnum(Integer name) {
        MenuTypeEnum[] arry = MenuTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equals(name)) {
                return arry[i];
            }
        }
        return null;
    }


    public Integer getValue() {
        return value;
    }


    public void setValue(Integer value) {
        this.value = value;
    }

}
