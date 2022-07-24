package cn.jianwoo.blog.enums;

public enum ArticleStatusEnum {

    /**
     * 已发布
     */
    PUBLISHED("90", "已发布"),

    /**
     * 草稿
     */
    DRAFT("00", "草稿"),

    /**
     * 回收站
     */
    RECYCLE("91", "回收站"),

    /**
     * 所有
     */
    ALL("100", "所有"),


    /**
     * 文章删除
     */
    DELETE("99", "文章删除"),

    ;

    /**
     * value
     */
    private String value;

    /**
     * 描述
     */
    private String desc;


    ArticleStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
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

    public static String getDesc(String name) {
        ArticleStatusEnum[] arry = ArticleStatusEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(name)) {
                return arry[i].getDesc();
            }
        }
        return "-";
    }

    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
