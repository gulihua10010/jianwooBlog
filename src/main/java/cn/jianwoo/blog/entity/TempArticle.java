package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class TempArticle implements Serializable {
    private Long oid;

    private String author;

    private String title;

    private Integer menuId;

    private Boolean isComment;

    private String imgSrc;

    private String visitType;

    private String password;

    private Long oldOid;

    private String tags;

    private String pageType;

    private String status;

    private Long restoreOid;

    private Date createTime;

    private Date updateTime;

    private String content;

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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Boolean getIsComment() {
        return isComment;
    }

    public void setIsComment(Boolean isComment) {
        this.isComment = isComment;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc == null ? null : imgSrc.trim();
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType == null ? null : visitType.trim();
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

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType == null ? null : pageType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getRestoreOid() {
        return restoreOid;
    }

    public void setRestoreOid(Long restoreOid) {
        this.restoreOid = restoreOid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        sb.append(", menuId=").append(menuId);
        sb.append(", isComment=").append(isComment);
        sb.append(", imgSrc=").append(imgSrc);
        sb.append(", visitType=").append(visitType);
        sb.append(", password=").append(password);
        sb.append(", oldOid=").append(oldOid);
        sb.append(", tags=").append(tags);
        sb.append(", pageType=").append(pageType);
        sb.append(", status=").append(status);
        sb.append(", restoreOid=").append(restoreOid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}