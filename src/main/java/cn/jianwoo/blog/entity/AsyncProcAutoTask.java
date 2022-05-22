package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class AsyncProcAutoTask implements Serializable {
    private Long taskId;

    private String taskType;

    private String taskData;

    private Integer timesMaxRetry;

    private String statusProc;

    private String statusProcDesc;

    private Date procStartTime;

    private Date procEndTime;

    private Integer timesProcFailed;

    private String failedReasonCode;

    private String failedReasonDesc;

    private Date createdTime;

    private Date lastUpdTime;

    private static final long serialVersionUID = 1L;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskData() {
        return taskData;
    }

    public void setTaskData(String taskData) {
        this.taskData = taskData == null ? null : taskData.trim();
    }

    public Integer getTimesMaxRetry() {
        return timesMaxRetry;
    }

    public void setTimesMaxRetry(Integer timesMaxRetry) {
        this.timesMaxRetry = timesMaxRetry;
    }

    public String getStatusProc() {
        return statusProc;
    }

    public void setStatusProc(String statusProc) {
        this.statusProc = statusProc == null ? null : statusProc.trim();
    }

    public String getStatusProcDesc() {
        return statusProcDesc;
    }

    public void setStatusProcDesc(String statusProcDesc) {
        this.statusProcDesc = statusProcDesc == null ? null : statusProcDesc.trim();
    }

    public Date getProcStartTime() {
        return procStartTime;
    }

    public void setProcStartTime(Date procStartTime) {
        this.procStartTime = procStartTime;
    }

    public Date getProcEndTime() {
        return procEndTime;
    }

    public void setProcEndTime(Date procEndTime) {
        this.procEndTime = procEndTime;
    }

    public Integer getTimesProcFailed() {
        return timesProcFailed;
    }

    public void setTimesProcFailed(Integer timesProcFailed) {
        this.timesProcFailed = timesProcFailed;
    }

    public String getFailedReasonCode() {
        return failedReasonCode;
    }

    public void setFailedReasonCode(String failedReasonCode) {
        this.failedReasonCode = failedReasonCode == null ? null : failedReasonCode.trim();
    }

    public String getFailedReasonDesc() {
        return failedReasonDesc;
    }

    public void setFailedReasonDesc(String failedReasonDesc) {
        this.failedReasonDesc = failedReasonDesc == null ? null : failedReasonDesc.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUpdTime() {
        return lastUpdTime;
    }

    public void setLastUpdTime(Date lastUpdTime) {
        this.lastUpdTime = lastUpdTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", taskType=").append(taskType);
        sb.append(", taskData=").append(taskData);
        sb.append(", timesMaxRetry=").append(timesMaxRetry);
        sb.append(", statusProc=").append(statusProc);
        sb.append(", statusProcDesc=").append(statusProcDesc);
        sb.append(", procStartTime=").append(procStartTime);
        sb.append(", procEndTime=").append(procEndTime);
        sb.append(", timesProcFailed=").append(timesProcFailed);
        sb.append(", failedReasonCode=").append(failedReasonCode);
        sb.append(", failedReasonDesc=").append(failedReasonDesc);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", lastUpdTime=").append(lastUpdTime);
        sb.append("]");
        return sb.toString();
    }
}