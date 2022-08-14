package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class MessageProfile implements Serializable {
    private Long oid;

    private String busiSceneCode;

    private String busiType;

    private String msgType;

    private String optType;

    private String receiverType;

    private String msgTitle;

    private String msgLink;

    private String bizId;

    private String receiverId;

    private String receiverName;

    private String receiverEmail;

    private String receiverMobileNo;

    private Boolean emailNotify;

    private String emailTplCode;

    private String emailSendResult;

    private Boolean flagRead;

    private Boolean flagPopup;

    private Boolean flagPopupMain;

    private Date readTime;

    private String procRslt;

    private Date procTime;

    private Date sendTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getBusiSceneCode() {
        return busiSceneCode;
    }

    public void setBusiSceneCode(String busiSceneCode) {
        this.busiSceneCode = busiSceneCode == null ? null : busiSceneCode.trim();
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType == null ? null : receiverType.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgLink() {
        return msgLink;
    }

    public void setMsgLink(String msgLink) {
        this.msgLink = msgLink == null ? null : msgLink.trim();
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail == null ? null : receiverEmail.trim();
    }

    public String getReceiverMobileNo() {
        return receiverMobileNo;
    }

    public void setReceiverMobileNo(String receiverMobileNo) {
        this.receiverMobileNo = receiverMobileNo == null ? null : receiverMobileNo.trim();
    }

    public Boolean getEmailNotify() {
        return emailNotify;
    }

    public void setEmailNotify(Boolean emailNotify) {
        this.emailNotify = emailNotify;
    }

    public String getEmailTplCode() {
        return emailTplCode;
    }

    public void setEmailTplCode(String emailTplCode) {
        this.emailTplCode = emailTplCode == null ? null : emailTplCode.trim();
    }

    public String getEmailSendResult() {
        return emailSendResult;
    }

    public void setEmailSendResult(String emailSendResult) {
        this.emailSendResult = emailSendResult == null ? null : emailSendResult.trim();
    }

    public Boolean getFlagRead() {
        return flagRead;
    }

    public void setFlagRead(Boolean flagRead) {
        this.flagRead = flagRead;
    }

    public Boolean getFlagPopup() {
        return flagPopup;
    }

    public void setFlagPopup(Boolean flagPopup) {
        this.flagPopup = flagPopup;
    }

    public Boolean getFlagPopupMain() {
        return flagPopupMain;
    }

    public void setFlagPopupMain(Boolean flagPopupMain) {
        this.flagPopupMain = flagPopupMain;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public String getProcRslt() {
        return procRslt;
    }

    public void setProcRslt(String procRslt) {
        this.procRslt = procRslt == null ? null : procRslt.trim();
    }

    public Date getProcTime() {
        return procTime;
    }

    public void setProcTime(Date procTime) {
        this.procTime = procTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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
        sb.append(", busiSceneCode=").append(busiSceneCode);
        sb.append(", busiType=").append(busiType);
        sb.append(", msgType=").append(msgType);
        sb.append(", optType=").append(optType);
        sb.append(", receiverType=").append(receiverType);
        sb.append(", msgTitle=").append(msgTitle);
        sb.append(", msgLink=").append(msgLink);
        sb.append(", bizId=").append(bizId);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", receiverEmail=").append(receiverEmail);
        sb.append(", receiverMobileNo=").append(receiverMobileNo);
        sb.append(", emailNotify=").append(emailNotify);
        sb.append(", emailTplCode=").append(emailTplCode);
        sb.append(", emailSendResult=").append(emailSendResult);
        sb.append(", flagRead=").append(flagRead);
        sb.append(", flagPopup=").append(flagPopup);
        sb.append(", flagPopupMain=").append(flagPopupMain);
        sb.append(", readTime=").append(readTime);
        sb.append(", procRslt=").append(procRslt);
        sb.append(", procTime=").append(procTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}