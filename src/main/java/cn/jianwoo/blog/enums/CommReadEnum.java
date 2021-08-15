package cn.jianwoo.blog.enums;

public enum CommReadEnum {

    /**
     * 已读
     */
    READ("10"),
    /**
     * 未读的
     */
    UNREAD("00"),

    ;

    /**
     * value
     */
    private String value;

    CommReadEnum(String value) {
        this.value = value;
    }


    public static CommReadEnum getEnum(String name) {
        CommReadEnum[] arry = CommReadEnum.values();
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
