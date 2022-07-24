package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
    private Long oid;

    private String name;

    private Integer index;

    private String type;

    private String icon;

    private String text;

    private String url;

    private Boolean flagCategory;

    private Boolean valid;

    private Long parentOid;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Boolean getFlagCategory() {
        return flagCategory;
    }

    public void setFlagCategory(Boolean flagCategory) {
        this.flagCategory = flagCategory;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Long getParentOid() {
        return parentOid;
    }

    public void setParentOid(Long parentOid) {
        this.parentOid = parentOid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", type=").append(type);
        sb.append(", icon=").append(icon);
        sb.append(", text=").append(text);
        sb.append(", url=").append(url);
        sb.append(", flagCategory=").append(flagCategory);
        sb.append(", valid=").append(valid);
        sb.append(", parentOid=").append(parentOid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}