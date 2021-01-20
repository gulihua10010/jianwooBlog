package cn.jianwoo.blog.enums;

public enum CommReadEnum {

    /**
     * 已读
     */
    READ(1),
    /**
     * 未读的
     */
    UNREAD(0),

    ;

    /**
     * value
     */
    private Integer value;

    CommReadEnum(Integer value) {
        this.value = value;
    }


    public static CommReadEnum getEnum(Integer name) {
        CommReadEnum[] arry = CommReadEnum.values();
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
