package cn.jianwoo.blog.enums;

public enum ArtCommStatusEnum {
    /**
     * 可以评论
     */
    HAS_COMMENT(1),

    /**
     * 不可以评论
     */
    NO_COMMENT(0),

    ;

    /**
     * value
     */
    private Integer value;

    ArtCommStatusEnum(Integer value) {
        this.value = value;
    }


    public static ArtCommStatusEnum getEnum(Integer name) {
        ArtCommStatusEnum[] arry = ArtCommStatusEnum.values();
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
