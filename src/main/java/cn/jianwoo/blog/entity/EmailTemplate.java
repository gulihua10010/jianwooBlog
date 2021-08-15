package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class EmailTemplate implements Serializable {
    private Long oid;

    private String emailTplCode;

    private String desc;

    private String subject;

    private String testJsonData;

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

    public String getEmailTplCode() {
        return emailTplCode;
    }

    public void setEmailTplCode(String emailTplCode) {
        this.emailTplCode = emailTplCode == null ? null : emailTplCode.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getTestJsonData() {
        return testJsonData;
    }

    public void setTestJsonData(String testJsonData) {
        this.testJsonData = testJsonData == null ? null : testJsonData.trim();
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
        sb.append(", emailTplCode=").append(emailTplCode);
        sb.append(", desc=").append(desc);
        sb.append(", subject=").append(subject);
        sb.append(", testJsonData=").append(testJsonData);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}