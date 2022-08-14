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
    ARTICLE_TEMP_SAVE("A17"),
    /**
     * 评论页
     */
    COMMENT_LIST("C10"),
    COMMENT_CREATE("C11"),
    COMMENT_EDIT("C12"),
    COMMENT_VIEW("C13"),
    COMMENT_REPLY("C14"),

    /**
     * 菜单页
     */
    MENU_LIST("M10"),
    MENU_CREATE("M11"),
    MENU_EDIT("M12"),

    /**
     * 标签页
     */
    TAGS_LIST("T10"),
    TAGS_EDIT("T11"),
    TAGS_VIEW("T12"),
    TAGS_CREATE("T13"),
    TAGS_CREATE_LIST("T14"),
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
     * PASSPORT
     */
    PASSPORT("P10"),

    /**
     * 登录
     */
    ADMIN_LOGIN("AL10"),
    /**
     * 用戶配置
     */
    ADMIN_PROFILE("APF10"),
    /**
     * 修改密碼
     */
    ADMIN_PASSWORD("APWD10"),

    /**
     * 邮件模板
     */
    EMAIL_TPL_CREATE("ET10"),
    EMAIL_TPL_UPDATE("ET11"),

    /**
     * 公告
     */
    ANNOUNCEMENT_CREATE("AN10"),
    ANNOUNCEMENT_UPDATE("AN11"),

    BLACK_IP_CREATE("BI10"),
    /**
     * 留言
     */
    MESSAGE_BOARD_REPLY("MB10"),

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
