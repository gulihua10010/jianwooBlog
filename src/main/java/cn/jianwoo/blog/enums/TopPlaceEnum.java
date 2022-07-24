package cn.jianwoo.blog.enums;

public enum TopPlaceEnum {



    /**
     * 文章未置顶
     */
    NOT_TOP("00"),

    /**
     * 文章置顶
     */
    TOP("50"),

    ;

    /**
     * value
     */
    private String value;

    TopPlaceEnum(String value) {
        this.value = value;
    }


    public static TopPlaceEnum getEnum(String name) {
        TopPlaceEnum[] arry = TopPlaceEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(name)) {
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
