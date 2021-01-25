package cn.jianwoo.blog.enums;

public enum PageIdEnum {

    /**
     * 文章页
     */
    ARTICLE_LIST("A10"),
    ARTICLE_PUBLISHED("A11"),
    ARTICLE_EDIT("A12"),
    ARTICLE_QUICK_EDIT("A13"),
    ARTICLE_RECYCLE_BIN("A14"),
    ARTICLE_RECYCLE_BIN_VIEW("A15"),
    /**
     * 评论页
     */
    COMMENT_LIST("C10"),
    COMMENT_ADD("C11"),
    COMMENT_EDIT("C12"),
    COMMENT_VIEW("C13"),
    COMMENT_REPLY("C14"),

    /**
     * 菜单页
     */
    MENU_LIST("M10"),
    MENU_ADD("M11"),
    MENU_EDIT("M12"),

    /**
     * 标签页
     */
    TAGS_LIST("T10"),
    TAGS_EDIT("T11"),
    TAGS_VIEW("T12"),
    TAGS_ADD("T13"),
    TAGS_ADD_LIST("T14"),
    /**
     * 控制台
     */
    CONSOLE("CS10"),

    /**
     * 动态
     */
    DYNAMIC("DN10"),

    /**
     * 清除缓存
     */
    CLEAR_CACHE("CC10"),

    /**
     * 网站配置
     */
    WEB_CONFIG("W10"),

    /**
     * 后台首页
     */
    ADMIN_INDEX("AI10"),

    /**
     * 登录
     */
    ADMIN_LOGIN("AL10"),


    ;
    private String value;

    PageIdEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }

    public static PageIdEnum getEnum(String name) {
        PageIdEnum[] arry = PageIdEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].value.equalsIgnoreCase(name)) {
                return arry[i];
            }
        }
        return null;
    }

}
