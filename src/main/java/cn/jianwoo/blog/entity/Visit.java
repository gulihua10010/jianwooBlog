package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Visit implements Serializable {
    private Long oid;

    private String visitIp;

    private String visitArea;

    private Long articleOid;

    private Date visitTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp == null ? null : visitIp.trim();
    }

    public String getVisitArea() {
        return visitArea;
    }

    public void setVisitArea(String visitArea) {
        this.visitArea = visitArea == null ? null : visitArea.trim();
    }

    public Long getArticleOid() {
        return articleOid;
    }

    public void setArticleOid(Long articleOid) {
        this.articleOid = articleOid;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
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
        sb.append(", visitIp=").append(visitIp);
        sb.append(", visitArea=").append(visitArea);
        sb.append(", articleOid=").append(articleOid);
        sb.append(", visitTime=").append(visitTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}