package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class AccessIpCtrl implements Serializable {
    private Long oid;

    private String accessIp;

    private String interfaceUrl;

    private Date accessTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getAccessIp() {
        return accessIp;
    }

    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp == null ? null : accessIp.trim();
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl == null ? null : interfaceUrl.trim();
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", accessIp=").append(accessIp);
        sb.append(", interfaceUrl=").append(interfaceUrl);
        sb.append(", accessTime=").append(accessTime);
        sb.append("]");
        return sb.toString();
    }
}