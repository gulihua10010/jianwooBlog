package cn.jianwoo.blog.enums;

public enum ArticleAccessEnum {


    /**
     * 私密的
     */
    PRIVATE("10"),

    /**
     * 密码
     */
    PASSWORD("11"),

    /**
     * 公开的
     */
    PUBLIC("20"),

    /**
     * 文章置顶
     */
    TOP("21"),

    ;

    /**
     * value
     */
    private String value;

    ArticleAccessEnum(String value) {
        this.value = value;
    }


    public static ArticleAccessEnum getEnum(String name) {
        ArticleAccessEnum[] arry = ArticleAccessEnum.values();
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
