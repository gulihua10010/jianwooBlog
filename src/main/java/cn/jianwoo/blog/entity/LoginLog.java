package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
    private Long oid;

    private String loginId;

    private String userName;

    private String eventType;

    private Date triggerTime;

    private String triggerDesc;

    private String triggerIp;

    private String triggerRegion;

    private String triggerDevice;

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

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getTriggerDesc() {
        return triggerDesc;
    }

    public void setTriggerDesc(String triggerDesc) {
        this.triggerDesc = triggerDesc == null ? null : triggerDesc.trim();
    }

    public String getTriggerIp() {
        return triggerIp;
    }

    public void setTriggerIp(String triggerIp) {
        this.triggerIp = triggerIp == null ? null : triggerIp.trim();
    }

    public String getTriggerRegion() {
        return triggerRegion;
    }

    public void setTriggerRegion(String triggerRegion) {
        this.triggerRegion = triggerRegion == null ? null : triggerRegion.trim();
    }

    public String getTriggerDevice() {
        return triggerDevice;
    }

    public void setTriggerDevice(String triggerDevice) {
        this.triggerDevice = triggerDevice == null ? null : triggerDevice.trim();
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
        sb.append(", triggerTime=").append(triggerTime);
        sb.append(", triggerDesc=").append(triggerDesc);
        sb.append(", triggerIp=").append(triggerIp);
        sb.append(", triggerRegion=").append(triggerRegion);
        sb.append(", triggerDevice=").append(triggerDevice);
        sb.append(", processStatus=").append(processStatus);
        sb.append(", failedReason=").append(failedReason);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}