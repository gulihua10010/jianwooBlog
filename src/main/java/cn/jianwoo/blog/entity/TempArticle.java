package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class TempArticle implements Serializable {
    private Long oid;

    private String author;

    private String title;

    private String content;

    private Integer typeId;

    private Integer isComment;

    private String imgSrc;

    private Integer visitType;

    private String password;

    private Long oldOid;

    private String tags;

    private Integer page;

    private Integer status;

    private Long restoreOid;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc == null ? null : imgSrc.trim();
    }

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getOldOid() {
        return oldOid;
    }

    public void setOldOid(Long oldOid) {
        this.oldOid = oldOid;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRestoreOid() {
        return restoreOid;
    }

    public void setRestoreOid(Long restoreOid) {
        this.restoreOid = restoreOid;
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
        sb.append(", author=").append(author);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", typeId=").append(typeId);
        sb.append(", isComment=").append(isComment);
        sb.append(", imgSrc=").append(imgSrc);
        sb.append(", visitType=").append(visitType);
        sb.append(", password=").append(password);
        sb.append(", oldOid=").append(oldOid);
        sb.append(", tags=").append(tags);
        sb.append(", page=").append(page);
        sb.append(", status=").append(status);
        sb.append(", restoreOid=").append(restoreOid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}