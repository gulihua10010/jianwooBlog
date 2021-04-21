package cn.jianwoo.blog.enums;

public enum ArticleStatusEnum {

    /**
     * 已发布
     */
    PUBLISHED(1),

    /**
     * 草稿
     */
    DRAFT(0),

    /**
     * 回收站
     */
    RECYCLE(-1),

    /**
     * 所有
     */
    ALL(2),


    /**
     * 文章删除
     */
    DELETE(-2),

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
