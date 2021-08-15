package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    private Long oid;

    private String username;

    private String password;

    private String userNick;

    private String userPhone;

    private String userEmail;

    private String userSex;

    private String registerIp;

    private String registerArea;

    private String lastLoginIp;

    private String lastLoginArea;

    private Date lastLoginTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea == null ? null : registerArea.trim();
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getLastLoginArea() {
        return lastLoginArea;
    }

    public void setLastLoginArea(String lastLoginArea) {
        this.lastLoginArea = lastLoginArea == null ? null : lastLoginArea.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", userNick=").append(userNick);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userSex=").append(userSex);
        sb.append(", registerIp=").append(registerIp);
        sb.append(", registerArea=").append(registerArea);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLoginArea=").append(lastLoginArea);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}