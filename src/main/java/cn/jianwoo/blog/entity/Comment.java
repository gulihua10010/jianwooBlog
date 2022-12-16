package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Long oid;

    private Long articleOid;

    private String articleTitle;

    private String articleAuthor;

    private String articlePushBy;

    private String userId;

    private String userName;

    private String userNick;

    private String clientIp;

    private String userRegion;

    private Date commentTime;

    private Long parentOid;

    private Long replyRootOid;

    private String replyToUserId;

    private String replyToUserName;

    private String replyToUserNick;

    private Long praiseCount;

    private String contactQq;

    private String contactWechat;

    private String contactWeibo;

    private String contactTel;

    private String contactEmail;

    private String avatarSrc;

    private String artDelStatus;

    private String readStatus;

    private Long floorNumber;

    private Long replyCount;

    private Long totalReplyCount;

    private Boolean flagAdmin;

    private Boolean isDelete;

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

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
    }

    public String getArticlePushBy() {
        return articlePushBy;
    }

    public void setArticlePushBy(String articlePushBy) {
        this.articlePushBy = articlePushBy == null ? null : articlePushBy.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion == null ? null : userRegion.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Long getParentOid() {
        return parentOid;
    }

    public void setParentOid(Long parentOid) {
        this.parentOid = parentOid;
    }

    public Long getReplyRootOid() {
        return replyRootOid;
    }

    public void setReplyRootOid(Long replyRootOid) {
        this.replyRootOid = replyRootOid;
    }

    public String getReplyToUserId() {
        return replyToUserId;
    }

    public void setReplyToUserId(String replyToUserId) {
        this.replyToUserId = replyToUserId == null ? null : replyToUserId.trim();
    }

    public String getReplyToUserName() {
        return replyToUserName;
    }

    public void setReplyToUserName(String replyToUserName) {
        this.replyToUserName = replyToUserName == null ? null : replyToUserName.trim();
    }

    public String getReplyToUserNick() {
        return replyToUserNick;
    }

    public void setReplyToUserNick(String replyToUserNick) {
        this.replyToUserNick = replyToUserNick == null ? null : replyToUserNick.trim();
    }

    public Long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getContactQq() {
        return contactQq;
    }

    public void setContactQq(String contactQq) {
        this.contactQq = contactQq == null ? null : contactQq.trim();
    }

    public String getContactWechat() {
        return contactWechat;
    }

    public void setContactWechat(String contactWechat) {
        this.contactWechat = contactWechat == null ? null : contactWechat.trim();
    }

    public String getContactWeibo() {
        return contactWeibo;
    }

    public void setContactWeibo(String contactWeibo) {
        this.contactWeibo = contactWeibo == null ? null : contactWeibo.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc == null ? null : avatarSrc.trim();
    }

    public String getArtDelStatus() {
        return artDelStatus;
    }

    public void setArtDelStatus(String artDelStatus) {
        this.artDelStatus = artDelStatus == null ? null : artDelStatus.trim();
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus == null ? null : readStatus.trim();
    }

    public Long getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Long floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    public Long getTotalReplyCount() {
        return totalReplyCount;
    }

    public void setTotalReplyCount(Long totalReplyCount) {
        this.totalReplyCount = totalReplyCount;
    }

    public Boolean getFlagAdmin() {
        return flagAdmin;
    }

    public void setFlagAdmin(Boolean flagAdmin) {
        this.flagAdmin = flagAdmin;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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
        sb.append(", articleOid=").append(articleOid);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleAuthor=").append(articleAuthor);
        sb.append(", articlePushBy=").append(articlePushBy);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userNick=").append(userNick);
        sb.append(", clientIp=").append(clientIp);
        sb.append(", userRegion=").append(userRegion);
        sb.append(", commentTime=").append(commentTime);
        sb.append(", parentOid=").append(parentOid);
        sb.append(", replyRootOid=").append(replyRootOid);
        sb.append(", replyToUserId=").append(replyToUserId);
        sb.append(", replyToUserName=").append(replyToUserName);
        sb.append(", replyToUserNick=").append(replyToUserNick);
        sb.append(", praiseCount=").append(praiseCount);
        sb.append(", contactQq=").append(contactQq);
        sb.append(", contactWechat=").append(contactWechat);
        sb.append(", contactWeibo=").append(contactWeibo);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", avatarSrc=").append(avatarSrc);
        sb.append(", artDelStatus=").append(artDelStatus);
        sb.append(", readStatus=").append(readStatus);
        sb.append(", floorNumber=").append(floorNumber);
        sb.append(", replyCount=").append(replyCount);
        sb.append(", totalReplyCount=").append(totalReplyCount);
        sb.append(", flagAdmin=").append(flagAdmin);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}