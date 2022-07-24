package cn.jianwoo.blog.enums;

public enum ArticleDelStatusEnum {

    /**
     * 未删除
     */
    NOT_REMOVE("00"),
    /**
     * 已删除(回收站:STATUS=91 / 删除:STATUS=99)
     */
    REMOVE("91"),

    ;

    /**
     * value
     */
    private String value;

    ArticleDelStatusEnum(String value) {
        this.value = value;
    }


    public static ArticleDelStatusEnum getEnum(String name) {
        ArticleDelStatusEnum[] arry = ArticleDelStatusEnum.values();
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
