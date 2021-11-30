package cn.jianwoo.blog.enums;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:10
 */
public enum ProcessStatusEnum {


    /**
     * 成功
     */
    SUCCESS("90", "成功"),

    /**
     * 失败
     */
    FAILED("91", "失败"),


    ;

    /**
     * 描述
     */
    private String desc;
    /**
     * value
     */
    private String value;

    ProcessStatusEnum(String value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    ProcessStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String valueOfBoolean(boolean isSuccess) {
        return isSuccess ? SUCCESS.value : FAILED.getValue();
    }


    public static String descOfValue(String value) {
        ProcessStatusEnum[] arry = ProcessStatusEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(value)) {
                return arry[i].desc;
            }
        }
        return SUCCESS.desc;
    }
}
