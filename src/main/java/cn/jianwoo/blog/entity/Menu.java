package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long oid;
    private String name;
    private Integer index;
    private Date createDate;
    private Date updateDate;
    private Integer type;
    private String icon;
    private String text;
    private String url;
    private Long parentOid;


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
        this.name = name == null ? null : name.trim();
    }


    public Integer getIndex() {
        return index;
    }


    public void setIndex(Integer index) {
        this.index = index;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Date getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }


    public Long getParentOid() {
        return parentOid;
    }


    public void setParentOid(Long parentOid) {
        this.parentOid = parentOid;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", name=").append(name);
        sb.append(", index=").append(index);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", type=").append(type);
        sb.append(", icon=").append(icon);
        sb.append(", text=").append(text);
        sb.append(", url=").append(url);
        sb.append(", parentOid=").append(parentOid);
        sb.append("]");
        return sb.toString();
    }
}