package cn.jianwoo.blog.enums;

public enum TempArticlePageEnum {

    /**
     * 10发布页面
     */
    PUBLISH("10"),

    /**
     * 20编辑页面
     */
    EDIT("20"),


    ;

    /**
     * value
     */
    private String value;

    TempArticlePageEnum(String value) {
        this.value = value;
    }


    public static TempArticlePageEnum getEnum(String name) {
        TempArticlePageEnum[] arry = TempArticlePageEnum.values();
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
