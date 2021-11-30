package cn.jianwoo.blog.enums;

import java.util.Objects;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:10
 */
public enum BizEventOptTypeEnum {


    /**
     * 创建
     */
    CREATE("10", "创建"),


    /**
     * 1010:文章创建至发布(article)
     */
    CREATE_PUBLISHED("1010", "文章创建并发布", "90"),


    /**
     * 1020:文章创建保存草稿(article)
     */
    CREATE_DRAFT("1020", "文章创建并保存草稿", "00"),


    /**
     * 1030:文章创建至回收站(article)
     */
    CREATE_RECYCLE("1030", "文章创建并移动至回收站", "91"),


    /**
     * 更新
     */
    UPDATE("20", "更新"),

    /**
     * 2000:编辑文章更新(article)
     */
    UPDATE_ARTICLE("2000", "编辑文章更新"),

    /**
     * 2050:编辑文章信息更新(article)
     */
    UPDATE_INFO_ARTICLE("2050", "编辑文章信息更新"),

    /**
     * 2010:编辑文章并发布(article)
     */
    UPDATE_PUBLISHED("2010", "编辑文章并发布", "00", "90", false),


    /**
     * 2020:编辑文章,发布恢复为草稿(article)
     */
    UPDATE_DRAFT("2020", "编辑文章,发布恢复为草稿", "90", "00", false),


    /**
     * 2030:编辑文章,移动至回收站(article)
     */
    UPDATE_RECYCLE("2030", "编辑文章,移动至回收站", "*", "91", false),

    /**
     * 2010S:草稿发布(article,只针对status修改)
     */
    UPDATE_STATUS_PUBLISHED("2010S", "草稿发布", "00", "90", true),

    /**
     * 2020S:恢复为草稿(article,只针对status修改)
     */
    UPDATE_STATUS_DRAFT("2020S", "恢复为草稿", "90", "00", true),
    /**
     * 2030S:移动至回收站(article,只针对status修改)
     */
    UPDATE_STATUS_RECYCLE("2030S", "移动至回收站", "*", "91", true),

    /**
     * 2040S:回收站恢复(article,只针对status修改)
     */
    UPDATE_STATUS_RESTORE("2040S", "回收站恢复", "91", "00", true),

    /**
     * 2110:菜单排序
     */
    UPDATE_MENU_SORT("2110", "菜单排序"),

    /**
     * 删除
     */
    DELETE("40", "删除"),

    ;

    /**
     * value
     */
    private String value;
    /**
     * desc
     */
    private String desc;
    /**
     * 更新后status, 只针对article
     */
    private String newStatus;
    /**
     * 更新前status, 只针对article
     */
    private String oldStatus;
    /**
     * 是否只是修改状态
     */
    private boolean isOnlyStatusChange;

    BizEventOptTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    BizEventOptTypeEnum(String value, String desc, String oldStatus, String newStatus, boolean isOnlyStatusChange) {
        this.value = value;
        this.desc = desc;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.isOnlyStatusChange = isOnlyStatusChange;
    }

    BizEventOptTypeEnum(String value, String desc, String newStatus) {
        this.value = value;
        this.desc = desc;
        this.newStatus = newStatus;
    }


    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNewStatus() {
        return this.newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public String getOldStatus() {
        return this.oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public boolean getIsOnlyStatusChange() {
        return this.isOnlyStatusChange;
    }

    public void setIsOnlyStatusChange(boolean onlyStatusChange) {
        this.isOnlyStatusChange = onlyStatusChange;
    }

    public static BizEventOptTypeEnum valueOfArticleStatus(String newStatus, String oldStatus, boolean isOnlyStatusChange) {
        BizEventOptTypeEnum[] arry = BizEventOptTypeEnum.values();
        if (null == newStatus) {
            return UPDATE_INFO_ARTICLE;
        }
        if (ArticleStatusEnum.DELETE.getValue().equals(newStatus)) {
            return DELETE;
        } else if (Objects.equals(newStatus, oldStatus)) {
            return UPDATE_ARTICLE;
        } else if (ArticleStatusEnum.RECYCLE.getValue().equals(oldStatus)) {
            return UPDATE_STATUS_RESTORE;
        }

        for (int i = 0; i < arry.length; i++) {
            if (arry[i].equals(CREATE) || arry[i].equals(UPDATE) || arry[i].equals(DELETE) || arry[i].equals(UPDATE_MENU_SORT)) {
                continue;
            } else if (Objects.equals(oldStatus, arry[i].getOldStatus()) && Objects.equals(newStatus, arry[i].getNewStatus())
                    && isOnlyStatusChange == arry[i].getIsOnlyStatusChange()) {
                return arry[i];

            }
        }
        return null;
    }


    public static String descOfValue(String value) {
        BizEventOptTypeEnum[] arry = BizEventOptTypeEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getValue().equals(value)) {
                return arry[i].desc;
            }
        }
        return null;
    }
}
