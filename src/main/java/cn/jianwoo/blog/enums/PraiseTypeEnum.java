package cn.jianwoo.blog.enums;

public enum PraiseTypeEnum {

    /**
     * 文章
     */
    ARTICLE("10"),

    /**
     * 评论
     */
    COMMENT("20"),
    ;

    /**
     * value
     */
    private String value;

    PraiseTypeEnum(String value) {
        this.value = value;
    }


    public static PraiseTypeEnum getEnum(String name) {
        PraiseTypeEnum[] arry = PraiseTypeEnum.values();
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
