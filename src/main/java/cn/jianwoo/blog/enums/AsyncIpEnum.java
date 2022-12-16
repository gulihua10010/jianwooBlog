package cn.jianwoo.blog.enums;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.entity.Article;
import cn.jianwoo.blog.entity.ArticleAccess;
import cn.jianwoo.blog.entity.ArticleWithBLOBs;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.entity.UserProfile;

public enum AsyncIpEnum {

    /**
     * 评论
     */
    COMMENT("Comment", "commentTransDao", "oid", "userRegion", Comment.class),

    /**
     * 访问
     */
    ACCESS("ArticleAccess", "articleAccessTransDao", "oid", "accessRegion", ArticleAccess.class),

    /**
     * 管理员注册
     */
    ADMIN_REG("Admin", "adminTransDao", "oid", "registerRegion", Admin.class),

    /**
     * 管理员登录
     */
    ADMIN_LOGIN("Admin", "adminTransDao", "oid", "lastLoginRegion", Admin.class),

    /**
     * 登录日志
     */
    LOGIN_LOG("LoginLog", "loginLogTransDao", "oid", "triggerRegion", LoginLog.class),

    /**
     * 用户注册
     */
    USER_REG("UserProfile", "userProfileTransDao", "oid", "registerRegion", UserProfile.class),


    /**
     * 留言
     */
    MESSAGE_BOARD("MessageBoard", "messageBoardTransDao", "oid", "userRegion", MessageBoard.class),

    /**
     * 文章
     */
    ARTICLE("ArticleWithBLOBs", "articleTransDao", "oid", "pushRegion", ArticleWithBLOBs.class),

    ;


    /**
     * 实体
     */
    private String entity;
    /**
     * 服务bean
     */
    private String svcBean;
    /**
     * 主键
     */
    private String primaryKey;
    /**
     * 字段
     */
    private String field;
    /**
     * class
     */
    private Class<?> clazz;

    AsyncIpEnum(String entity, String svcBean, String primaryKey, String field, Class clazz) {
        this.entity = entity;
        this.svcBean = svcBean;
        this.primaryKey = primaryKey;
        this.field = field;
        this.clazz = clazz;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getSvcBean() {
        return this.svcBean;
    }

    public void setSvcBean(String svcBean) {
        this.svcBean = svcBean;
    }

    public String getPrimaryKey() {
        return this.primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Class getClazz() {
        return this.clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }


    public static AsyncIpEnum getEnum(String name) {
        AsyncIpEnum[] arry = AsyncIpEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].name().equals(name)) {
                return arry[i];
            }
        }
        return null;
    }
}
