package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Module implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long oid;
    private String name;
    private Integer isDisplay;
    private Integer index;
    private Date createDate;
    private Date updateDate;


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


    public Integer getIsDisplay() {
        return isDisplay;
    }


    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", name=").append(name);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", index=").append(index);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}