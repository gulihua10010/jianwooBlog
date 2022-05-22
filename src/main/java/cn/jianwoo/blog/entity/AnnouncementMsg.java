package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class AnnouncementMsg implements Serializable {
    private Long oid;

    private String title;

    private Date pushTime;

    private String pushBy;

    private Date expiationTime;

    private String status;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public Date getExpiationTime() {
        return expiationTime;
    }

    public void setExpiationTime(Date expiationTime) {
        this.expiationTime = expiationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        sb.append(", title=").append(title);
        sb.append(", pushTime=").append(pushTime);
        sb.append(", pushBy=").append(pushBy);
        sb.append(", expiationTime=").append(expiationTime);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}