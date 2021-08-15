package cn.jianwoo.blog.enums;

public enum ArticleStatusEnum {

    /**
     * 已发布
     */
    PUBLISHED("90"),

    /**
     * 草稿
     */
    DRAFT("00"),

    /**
     * 回收站
     */
    RECYCLE("91"),

    /**
     * 所有
     */
    ALL("100"),


    /**
     * 文章删除
     */
    DELETE("99"),

    ;

    /**
     * value
     */
    private String value;

    ArticleStatusEnum(String value) {
        this.value = value;
    }


    public static ArticleStatusEnum getEnum(String name) {
        ArticleStatusEnum[] arry = ArticleStatusEnum.values();
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
