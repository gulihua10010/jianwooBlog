package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class AsyncProcTaskTypeCfg implements Serializable {
    private String taskType;

    private String taskTypeName;

    private String execSrvId;

    private Integer timesMaxRetry;

    private Date createdTime;

    private String createdBy;

    private static final long serialVersionUID = 1L;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName == null ? null : taskTypeName.trim();
    }

    public String getExecSrvId() {
        return execSrvId;
    }

    public void setExecSrvId(String execSrvId) {
        this.execSrvId = execSrvId == null ? null : execSrvId.trim();
    }

    public Integer getTimesMaxRetry() {
        return timesMaxRetry;
    }

    public void setTimesMaxRetry(Integer timesMaxRetry) {
        this.timesMaxRetry = timesMaxRetry;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskType=").append(taskType);
        sb.append(", taskTypeName=").append(taskTypeName);
        sb.append(", execSrvId=").append(execSrvId);
        sb.append(", timesMaxRetry=").append(timesMaxRetry);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append("]");
        return sb.toString();
    }
}