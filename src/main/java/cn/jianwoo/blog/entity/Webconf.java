package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Webconf implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long oid;
    private String key;
    private String stringValue;
    private BigDecimal numValue;
    private Boolean booleanValue;
    private Date createDate;
    private Date updateDate;


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }


    public String getStringValue() {
        return stringValue;
    }


    public void setStringValue(String stringValue) {
        this.stringValue = stringValue == null ? null : stringValue.trim();
    }


    public BigDecimal getNumValue() {
        return numValue;
    }


    public void setNumValue(BigDecimal numValue) {
        this.numValue = numValue;
    }


    public Boolean getBooleanValue() {
        return booleanValue;
    }


    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }


    public Date getCreateDate() {
        return createDate;
    }


    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Date getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oid=").append(oid);
        sb.append(", key=").append(key);
        sb.append(", stringValue=").append(stringValue);
        sb.append(", numValue=").append(numValue);
        sb.append(", booleanValue=").append(booleanValue);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}