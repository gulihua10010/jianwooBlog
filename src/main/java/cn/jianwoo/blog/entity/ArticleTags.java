package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class ArticleTags implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long oid;
    private Integer tagsOid;
    private Long articleOid;
    private Date createDate;
    private Date updateDate;


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public Integer getTagsOid() {
        return tagsOid;
    }


    public void setTagsOid(Integer tagsOid) {
        this.tagsOid = tagsOid;
    }


    public Long getArticleOid() {
        return articleOid;
    }


    public void setArticleOid(Long articleOid) {
        this.articleOid = articleOid;
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
        sb.append(", tagsOid=").append(tagsOid);
        sb.append(", articleOid=").append(articleOid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}