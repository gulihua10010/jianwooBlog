package cn.jianwoo.blog.enums;

public enum TempArticleStatusEnum {

    /**
     * 90已经恢复
     */
    RESTORE("90"),

    /**
     * 00临时缓存状态
     */
    TEMP("00"),

    /**
     * 91作废
     */
    VOID("91"),


    ;

    /**
     * value
     */
    private String value;

    TempArticleStatusEnum(String value) {
        this.value = value;
    }


    public static TempArticleStatusEnum getEnum(String name) {
        TempArticleStatusEnum[] arry = TempArticleStatusEnum.values();
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
