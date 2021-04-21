package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Webconf implements Serializable {
    private Long oid;

    private String key;

    private String desc;

    private String title;

    private String valueType;

    private String formType;

    private String stringValue;

    private BigDecimal numValue;

    private Boolean booleanValue;

    private Date dateValue;

    private Boolean mandatory;

    private String validateType;

    private String validateValue;

    private Integer index;

    private Boolean valid;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType == null ? null : valueType.trim();
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType == null ? null : formType.trim();
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

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getValidateType() {
        return validateType;
    }

    public void setValidateType(String validateType) {
        this.validateType = validateType == null ? null : validateType.trim();
    }

    public String getValidateValue() {
        return validateValue;
    }

    public void setValidateValue(String validateValue) {
        this.validateValue = validateValue == null ? null : validateValue.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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
        sb.append(", desc=").append(desc);
        sb.append(", title=").append(title);
        sb.append(", valueType=").append(valueType);
        sb.append(", formType=").append(formType);
        sb.append(", stringValue=").append(stringValue);
        sb.append(", numValue=").append(numValue);
        sb.append(", booleanValue=").append(booleanValue);
        sb.append(", dateValue=").append(dateValue);
        sb.append(", mandatory=").append(mandatory);
        sb.append(", validateType=").append(validateType);
        sb.append(", validateValue=").append(validateValue);
        sb.append(", index=").append(index);
        sb.append(", valid=").append(valid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}