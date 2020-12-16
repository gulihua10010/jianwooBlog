package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:23
 */
public class MenuVoRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 4600458570269532916L;
    private Long oid;
    private Long parentOid;
    private String text;
    private String url;
    private String name;
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
