package cn.jianwoo.blog.enums;

public enum MsgTypeEnum {
    /**
     * 文章评论
     */
    M10100002("10100002"),

    /**
     * 文章赞
     */
    M10100001("10100001"),

    /**
     * 评论赞(博主)
     */
    M20100001("20100001"),

    /**
     * 评论赞(用户)
     */
    M20101001("20101001"),

    /**
     * 评论更新
     */
    M20100003("20100003"),

    /**
     * 评论回复
     */
    M20101004("20101004"),

    /**
     * 博客留言
     */
    M00100002("00100002"),

    /**
     * 留言赞(博主)
     */
    M30100001("30100001"),


    /**
     * 留言赞(用户)
     */
    M30101001("30101001"),

    /**
     * 留言更新
     */
    M30100003("30100003"),

    /**
     * 留言回复
     */
    M30101004("30101004"),

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
