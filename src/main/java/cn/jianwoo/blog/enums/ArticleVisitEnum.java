package cn.jianwoo.blog.enums;

public enum ArticleVisitEnum {

    PASSWORD(-1),

    PUBLIC(1),

    PRIVATE(0),

    TOP(2),

    ;

    /**
     * value
     */
    private Integer value;

    ArticleVisitEnum(Integer value) {
        this.value = value;
    }


    public static ArticleVisitEnum getEnum(Integer name) {
        ArticleVisitEnum[] arry = ArticleVisitEnum.values();
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
