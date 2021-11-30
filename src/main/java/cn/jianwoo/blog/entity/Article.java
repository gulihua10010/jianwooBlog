package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Long oid;

    private String author;

    private Date pushTime;

    private String title;

    private Date modifiedTime;

    private Integer menuId;

    private Long readCount;

    private Long praiseCount;

    private Boolean isComment;

    private String imgSrc;

    private String visitType;

    private Long commentCount;

    private String status;

    private String password;

    private Date delTime;

    private Date removeRecycleTime;

    private Date createTime;

    private Date updateTime;

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

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
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

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public Date getRemoveRecycleTime() {
        return removeRecycleTime;
    }

    public void setRemoveRecycleTime(Date removeRecycleTime) {
        this.removeRecycleTime = removeRecycleTime;
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
        sb.append(", author=").append(author);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", title=").append(title);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", menuId=").append(menuId);
        sb.append(", readCount=").append(readCount);
        sb.append(", praiseCount=").append(praiseCount);
        sb.append(", isComment=").append(isComment);
        sb.append(", imgSrc=").append(imgSrc);
        sb.append(", visitType=").append(visitType);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", status=").append(status);
        sb.append(", password=").append(password);
        sb.append(", delTime=").append(delTime);
        sb.append(", removeRecycleTime=").append(removeRecycleTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}