package cn.jianwoo.blog.enums;

public enum TempArticlePageEnum {

    /**
     * 1发布页面
     */
    PUBLISH(1),

    /**
     * 2编辑页面
     */
    EDIT(2),


    ;

    /**
     * value
     */
    private Integer value;

    TempArticlePageEnum(Integer value) {
        this.value = value;
    }


    public static TempArticlePageEnum getEnum(Integer name) {
        TempArticlePageEnum[] arry = TempArticlePageEnum.values();
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
