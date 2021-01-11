package cn.jianwoo.blog.enums;

public enum PageIdEnum {

    ARTICLE_LIST("A10"),
    ARTICLE_PUBLISHED("A11"),
    ARTICLE_EDIT("A12"),
    ARTICLE_QUICK_EDIT("A13"),
    ARTICLE_RECYCLE_BIN("A14"),
    ARTICLE_RECYCLE_BIN_VIEW("A15"),

    COMMENT_LIST("C10"),
    COMMENT_ADD("C11"),
    COMMENT_EDIT("C12"),
    COMMENT_VIEW("C13"),
    COMMENT_REPLY("C14"),

    MENU_LIST("M10"),
    MENU_ADD("M11"),
    MENU_EDIT("M12"),

    TAGS_LIST("T10"),
    TAGS_EDIT("T11"),
    TAGS_VIEW("T12"),

    CONSOLE("CS10"),

    DYNAMIC("DN10"),

    CLEAR_CACHE("CC10"),

    WEB_CONFIG("W10"),

    ADMIN_INDEX("AI10"),


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
