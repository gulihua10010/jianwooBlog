package cn.jianwoo.blog.enums;

public enum AnnounceStatusEnum {

    /**
     * 有效
     */
    VALID("00"),

    /**
     * 作废
     */
    VOID("91"),
    ;

    /**
     * value
     */
    private String value;

    AnnounceStatusEnum(String value) {
        this.value = value;
    }


    public static AnnounceStatusEnum getEnum(String name) {
        AnnounceStatusEnum[] arry = AnnounceStatusEnum.values();
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
