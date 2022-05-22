package cn.jianwoo.blog.enums;

public enum ArtRecmdTypeEnum {

    /**
     * 最热文章(默认)
     */
    HOT("10"),

    /**
     * 最新文章
     */
    NEWEST("20"),


    /**
     * 随机文章
     */
    RANDOM("20"),
    ;

    /**
     * value
     */
    private String value;

    ArtRecmdTypeEnum(String value) {
        this.value = value;
    }


    public static ArtRecmdTypeEnum getEnum(String name) {
        ArtRecmdTypeEnum[] arry = ArtRecmdTypeEnum.values();
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
