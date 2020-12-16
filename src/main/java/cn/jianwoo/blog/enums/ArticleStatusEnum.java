package cn.jianwoo.blog.enums;

public enum ArticleStatusEnum {

    PUBLISHED(1),

    DRAFT(0),

    RECYCLE(-1),

    ;

    /**
     * value
     */
    private Integer value;

    ArticleStatusEnum(Integer value) {
        this.value = value;
    }


    public static ArticleStatusEnum getEnum(Integer name) {
        ArticleStatusEnum[] arry = ArticleStatusEnum.values();
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
