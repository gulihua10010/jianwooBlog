package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class IpBlackList implements Serializable {
    private Long oid;

    private String accessIp;

    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", accessIp=").append(accessIp);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}