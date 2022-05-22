package cn.jianwoo.blog.enums;

public enum MsgTypeEnum {
    /**
     * 文章评论
     */
    M101010("101010"),
    /**
     * 文章赞
     */
    M101020("101020"),
    /**
     * 评论赞
     */
    M101021("101021"),
    /**
     * 评论更新
     */
    M101022("101022"),

    ;


    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    MsgTypeEnum(String value) {
        this.value = value;
    }

    private String value;

}
