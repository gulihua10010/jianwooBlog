package cn.jianwoo.blog.enums;

public enum OrderWayEnum {

    /**
     * 热度排序(默认)
     */
    HOT("10"),

    /**
     * 最新排序
     */
    NEW("20"),
    ;

    /**
     * value
     */
    private String value;

    OrderWayEnum(String value) {
        this.value = value;
    }


    public static OrderWayEnum getEnum(String name) {
        OrderWayEnum[] arry = OrderWayEnum.values();
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
