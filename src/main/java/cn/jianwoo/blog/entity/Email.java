package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Email implements Serializable {
    private Long oid;

    private String toEmail;

    private String emailTplCode;

    private String subject;

    private String jsonData;

    private String files;

    private String procStatus;

    private String procDesc;

    private Date procTime;

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

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail == null ? null : toEmail.trim();
    }

    public String getEmailTplCode() {
        return emailTplCode;
    }

    public void setEmailTplCode(String emailTplCode) {
        this.emailTplCode = emailTplCode == null ? null : emailTplCode.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData == null ? null : jsonData.trim();
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files == null ? null : files.trim();
    }

    public String getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(String procStatus) {
        this.procStatus = procStatus == null ? null : procStatus.trim();
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc == null ? null : procDesc.trim();
    }

    public Date getProcTime() {
        return procTime;
    }

    public void setProcTime(Date procTime) {
        this.procTime = procTime;
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
        sb.append(", toEmail=").append(toEmail);
        sb.append(", emailTplCode=").append(emailTplCode);
        sb.append(", subject=").append(subject);
        sb.append(", jsonData=").append(jsonData);
        sb.append(", files=").append(files);
        sb.append(", procStatus=").append(procStatus);
        sb.append(", procDesc=").append(procDesc);
        sb.append(", procTime=").append(procTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}