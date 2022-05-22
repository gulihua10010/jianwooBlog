package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private Long oid;

    private Long articleOid;

    private String articleTitle;

    private String articleAuthor;

    private String articlePushBy;

    private String userName;

    private String clientIp;

    private String userArea;

    private Date commentTime;

    private Long parentOid;

    private Long praiseCount;

    private String contactQq;

    private String contactWechat;

    private String contactWeibo;

    private String contactTel;

    private String avatarSrc;

    private String artDelStauts;

    private String readStatus;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }

    public String getUserArea() {
        return userArea;
    }

    public void setUserArea(String userArea) {
        this.userArea = userArea == null ? null : userArea.trim();
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

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc == null ? null : avatarSrc.trim();
    }

    public String getArtDelStauts() {
        return artDelStauts;
    }

    public void setArtDelStauts(String artDelStauts) {
        this.artDelStauts = artDelStauts == null ? null : artDelStauts.trim();
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus == null ? null : readStatus.trim();
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
        sb.append(", userName=").append(userName);
        sb.append(", clientIp=").append(clientIp);
        sb.append(", userArea=").append(userArea);
        sb.append(", commentTime=").append(commentTime);
        sb.append(", parentOid=").append(parentOid);
        sb.append(", praiseCount=").append(praiseCount);
        sb.append(", contactQq=").append(contactQq);
        sb.append(", contactWechat=").append(contactWechat);
        sb.append(", contactWeibo=").append(contactWeibo);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", avatarSrc=").append(avatarSrc);
        sb.append(", artDelStauts=").append(artDelStauts);
        sb.append(", readStatus=").append(readStatus);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}