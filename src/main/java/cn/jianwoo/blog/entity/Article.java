package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Long oid;

    private String author;

    private Date pushTime;

    private String pushBy;

    private String pushIp;

    private String pushRegion;

    private String title;

    private Date modifiedTime;

    private Integer categoryId;

    private String categoryName;

    private Long readCount;

    private Long praiseCount;

    private Boolean isComment;

    private Boolean flagOriginal;

    private String originalUrl;

    private String imgSrc;

    private String accessType;

    private String topPlaceStatus;

    private Long commentCount;

    private Long totalCommentFloors;

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

    public String getPushBy() {
        return pushBy;
    }

    public void setPushBy(String pushBy) {
        this.pushBy = pushBy == null ? null : pushBy.trim();
    }

    public String getPushIp() {
        return pushIp;
    }

    public void setPushIp(String pushIp) {
        this.pushIp = pushIp == null ? null : pushIp.trim();
    }

    public String getPushRegion() {
        return pushRegion;
    }

    public void setPushRegion(String pushRegion) {
        this.pushRegion = pushRegion == null ? null : pushRegion.trim();
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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

    public Boolean getFlagOriginal() {
        return flagOriginal;
    }

    public void setFlagOriginal(Boolean flagOriginal) {
        this.flagOriginal = flagOriginal;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl == null ? null : originalUrl.trim();
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc == null ? null : imgSrc.trim();
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType == null ? null : accessType.trim();
    }

    public String getTopPlaceStatus() {
        return topPlaceStatus;
    }

    public void setTopPlaceStatus(String topPlaceStatus) {
        this.topPlaceStatus = topPlaceStatus == null ? null : topPlaceStatus.trim();
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getTotalCommentFloors() {
        return totalCommentFloors;
    }

    public void setTotalCommentFloors(Long totalCommentFloors) {
        this.totalCommentFloors = totalCommentFloors;
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
        sb.append(", pushBy=").append(pushBy);
        sb.append(", pushIp=").append(pushIp);
        sb.append(", pushRegion=").append(pushRegion);
        sb.append(", title=").append(title);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", readCount=").append(readCount);
        sb.append(", praiseCount=").append(praiseCount);
        sb.append(", isComment=").append(isComment);
        sb.append(", flagOriginal=").append(flagOriginal);
        sb.append(", originalUrl=").append(originalUrl);
        sb.append(", imgSrc=").append(imgSrc);
        sb.append(", accessType=").append(accessType);
        sb.append(", topPlaceStatus=").append(topPlaceStatus);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", totalCommentFloors=").append(totalCommentFloors);
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