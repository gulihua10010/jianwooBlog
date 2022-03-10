package cn.jianwoo.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class WebconfFacade implements Serializable {
    private Long oid;

    private String cfgKey;

    private String desc;

    private String titleDsp;

    private String tipsDsp;

    private String formType;

    private String tabType;

    private String tabTypeDsp;

    private Boolean required;

    private String validateType;

    private String validateValue;

    private Integer index;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey == null ? null : cfgKey.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getTitleDsp() {
        return titleDsp;
    }

    public void setTitleDsp(String titleDsp) {
        this.titleDsp = titleDsp == null ? null : titleDsp.trim();
    }

    public String getTipsDsp() {
        return tipsDsp;
    }

    public void setTipsDsp(String tipsDsp) {
        this.tipsDsp = tipsDsp == null ? null : tipsDsp.trim();
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType == null ? null : formType.trim();
    }

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType == null ? null : tabType.trim();
    }

    public String getTabTypeDsp() {
        return tabTypeDsp;
    }

    public void setTabTypeDsp(String tabTypeDsp) {
        this.tabTypeDsp = tabTypeDsp == null ? null : tabTypeDsp.trim();
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
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
        sb.append(", cfgKey=").append(cfgKey);
        sb.append(", desc=").append(desc);
        sb.append(", titleDsp=").append(titleDsp);
        sb.append(", tipsDsp=").append(tipsDsp);
        sb.append(", formType=").append(formType);
        sb.append(", tabType=").append(tabType);
        sb.append(", tabTypeDsp=").append(tabTypeDsp);
        sb.append(", required=").append(required);
        sb.append(", validateType=").append(validateType);
        sb.append(", validateValue=").append(validateValue);
        sb.append(", index=").append(index);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}