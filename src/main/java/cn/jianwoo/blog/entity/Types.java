package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Types implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long oid;
    private String content;
    private Integer menuOid;
    private Date createDate;
    private Date updateDate;


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


    public Integer getMenuOid() {
        return menuOid;
    }


    public void setMenuOid(Integer menuOid) {
        this.menuOid = menuOid;
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
        sb.append(", content=").append(content);
        sb.append(", menuOid=").append(menuOid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}