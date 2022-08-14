package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class MessageTemplate implements Serializable {
    private String busiSceneCode;

    private String busiType;

    private String msgType;

    private String optType;

    private String receiverType;

    private String msgDesc;

    private String msgTitleTemplate;

    private String msgLinkTemplate;

    private Boolean statusUsed;

    private Boolean emailNotify;

    private String emailTplCode;

    private Date createTime;

    private String createdBy;

    private Date updateTime;

    private String updateBy;

    private byte[] msgContentTemplate;

    private static final long serialVersionUID = 1L;

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

    public String getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(String msgDesc) {
        this.msgDesc = msgDesc == null ? null : msgDesc.trim();
    }

    public String getMsgTitleTemplate() {
        return msgTitleTemplate;
    }

    public void setMsgTitleTemplate(String msgTitleTemplate) {
        this.msgTitleTemplate = msgTitleTemplate == null ? null : msgTitleTemplate.trim();
    }

    public String getMsgLinkTemplate() {
        return msgLinkTemplate;
    }

    public void setMsgLinkTemplate(String msgLinkTemplate) {
        this.msgLinkTemplate = msgLinkTemplate == null ? null : msgLinkTemplate.trim();
    }

    public Boolean getStatusUsed() {
        return statusUsed;
    }

    public void setStatusUsed(Boolean statusUsed) {
        this.statusUsed = statusUsed;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public byte[] getMsgContentTemplate() {
        return msgContentTemplate;
    }

    public void setMsgContentTemplate(byte[] msgContentTemplate) {
        this.msgContentTemplate = msgContentTemplate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", busiSceneCode=").append(busiSceneCode);
        sb.append(", busiType=").append(busiType);
        sb.append(", msgType=").append(msgType);
        sb.append(", optType=").append(optType);
        sb.append(", receiverType=").append(receiverType);
        sb.append(", msgDesc=").append(msgDesc);
        sb.append(", msgTitleTemplate=").append(msgTitleTemplate);
        sb.append(", msgLinkTemplate=").append(msgLinkTemplate);
        sb.append(", statusUsed=").append(statusUsed);
        sb.append(", emailNotify=").append(emailNotify);
        sb.append(", emailTplCode=").append(emailTplCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", msgContentTemplate=").append(msgContentTemplate);
        sb.append("]");
        return sb.toString();
    }
}