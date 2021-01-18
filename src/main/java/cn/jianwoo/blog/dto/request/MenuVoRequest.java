package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:23
 */
public class MenuVoRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 4600458570269532916L;
    /**
     * 菜单oid
     */
    private Long oid;
    /**
     * 菜单父oid
     */
    private Long parentOid;
    /**
     * 菜单显示文本
     */
    private String text;
    /**
     * 菜单跳转路由
     */
    private String url;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;

    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Long getParentOid() {
        return parentOid;
    }


    public void setParentOid(Long parentOid) {
        this.parentOid = parentOid;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
