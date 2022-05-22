package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class ArticleAccess implements Serializable {
    private Long oid;

    private String accessIp;

    private String accessArea;

    private Long articleOid;

    private String articleTitle;

    private Date accessTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp == null ? null : accessIp.trim();
    }

    public String getAccessArea() {
        return accessArea;
    }

    public void setAccessArea(String accessArea) {
        this.accessArea = accessArea == null ? null : accessArea.trim();
    }

    public Long getArticleOid() {
        return articleOid;
    }

    public void setArticleOid(Long articleOid) {
        this.articleOid = articleOid;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
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
        sb.append(", accessIp=").append(accessIp);
        sb.append(", accessArea=").append(accessArea);
        sb.append(", articleOid=").append(articleOid);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", accessTime=").append(accessTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}