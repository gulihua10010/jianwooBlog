package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class BizEventLog implements Serializable {
    private Long oid;

    private String loginId;

    private String userName;

    private String eventType;

    private String optType;

    private String optEntityOid;

    private String optEntityDesc;

    private Date triggerTime;

    private String triggerIp;

    private String processStatus;

    private String failedReason;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType == null ? null : optType.trim();
    }

    public String getOptEntityOid() {
        return optEntityOid;
    }

    public void setOptEntityOid(String optEntityOid) {
        this.optEntityOid = optEntityOid == null ? null : optEntityOid.trim();
    }

    public String getOptEntityDesc() {
        return optEntityDesc;
    }

    public void setOptEntityDesc(String optEntityDesc) {
        this.optEntityDesc = optEntityDesc == null ? null : optEntityDesc.trim();
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getTriggerIp() {
        return triggerIp;
    }

    public void setTriggerIp(String triggerIp) {
        this.triggerIp = triggerIp == null ? null : triggerIp.trim();
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus == null ? null : processStatus.trim();
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason == null ? null : failedReason.trim();
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
        sb.append(", loginId=").append(loginId);
        sb.append(", userName=").append(userName);
        sb.append(", eventType=").append(eventType);
        sb.append(", optType=").append(optType);
        sb.append(", optEntityOid=").append(optEntityOid);
        sb.append(", optEntityDesc=").append(optEntityDesc);
        sb.append(", triggerTime=").append(triggerTime);
        sb.append(", triggerIp=").append(triggerIp);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", failedReason=").append(failedReason);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}