package cn.jianwoo.blog.enums;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:10
 */
public enum BizEventTypeEnum {


    /**
     * 文章
     */
    ARTICLE("10", "文章"),

    /**
     * 评论
     */
    COMMENT("20", "评论"),
    /**
     * 标签
     */
    TAGS("30", "标签"),

    /**
     * 菜单
     */
    MENU("40", "菜单"),

    /**
     * 网站配置
     */
    WEBCONF("50", "网站配置"),

    /**
     * 邮件模板
     */
    EMAIL_TPL("60", "邮件模板"),


    /**
     * 公告
     */
    ANNOUNCEMENT_TPL("70", "公告"),

    ;

    /**
     * value
     */
    private String value;

    /**
     * 描述
     */
    private String desc;

    BizEventTypeEnum(String value) {
        this.value = value;
    }

    BizEventTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static String descOfValue(String value) {
        BizEventTypeEnum[] arry = BizEventTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(value)) {
                return arry[i].desc;
            }
        }
        return null;
    }
}
