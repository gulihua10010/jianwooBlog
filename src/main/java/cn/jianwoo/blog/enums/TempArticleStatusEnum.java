package cn.jianwoo.blog.enums;

public enum TempArticleStatusEnum {

    /**
     * 1已经恢复
     */
    RESTORE(1),

    /**
     * 0临时缓存状态
     */
    TEMP(0),

    /**
     * -1作废
     */
    VOID(-1),


    ;

    /**
     * value
     */
    private Integer value;

    TempArticleStatusEnum(Integer value) {
        this.value = value;
    }


    public static TempArticleStatusEnum getEnum(Integer name) {
        TempArticleStatusEnum[] arry = TempArticleStatusEnum.values();
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
