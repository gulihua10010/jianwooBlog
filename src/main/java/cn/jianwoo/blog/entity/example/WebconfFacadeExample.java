package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebconfFacadeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public WebconfFacadeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setStart(Integer start) {
        this.start=start;
    }

    public Integer getStart() {
        return start;
    }

    public void setRows(Integer rows) {
        this.rows=rows;
    }

    public Integer getRows() {
        return rows;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOidIsNull() {
            addCriterion("OID is null");
            return (Criteria) this;
        }

        public Criteria andOidIsNotNull() {
            addCriterion("OID is not null");
            return (Criteria) this;
        }

        public Criteria andOidEqualTo(Long value) {
            addCriterion("OID =", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotEqualTo(Long value) {
            addCriterion("OID <>", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThan(Long value) {
            addCriterion("OID >", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidGreaterThanOrEqualTo(Long value) {
            addCriterion("OID >=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThan(Long value) {
            addCriterion("OID <", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidLessThanOrEqualTo(Long value) {
            addCriterion("OID <=", value, "oid");
            return (Criteria) this;
        }

        public Criteria andOidIn(List<Long> values) {
            addCriterion("OID in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotIn(List<Long> values) {
            addCriterion("OID not in", values, "oid");
            return (Criteria) this;
        }

        public Criteria andOidBetween(Long value1, Long value2) {
            addCriterion("OID between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andOidNotBetween(Long value1, Long value2) {
            addCriterion("OID not between", value1, value2, "oid");
            return (Criteria) this;
        }

        public Criteria andCfgKeyIsNull() {
            addCriterion("CFG_KEY is null");
            return (Criteria) this;
        }

        public Criteria andCfgKeyIsNotNull() {
            addCriterion("CFG_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andCfgKeyEqualTo(String value) {
            addCriterion("CFG_KEY =", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotEqualTo(String value) {
            addCriterion("CFG_KEY <>", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyGreaterThan(String value) {
            addCriterion("CFG_KEY >", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_KEY >=", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLessThan(String value) {
            addCriterion("CFG_KEY <", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLessThanOrEqualTo(String value) {
            addCriterion("CFG_KEY <=", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLike(String value) {
            addCriterion("CFG_KEY like", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotLike(String value) {
            addCriterion("CFG_KEY not like", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyIn(List<String> values) {
            addCriterion("CFG_KEY in", values, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotIn(List<String> values) {
            addCriterion("CFG_KEY not in", values, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyBetween(String value1, String value2) {
            addCriterion("CFG_KEY between", value1, value2, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotBetween(String value1, String value2) {
            addCriterion("CFG_KEY not between", value1, value2, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("`DESC` is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("`DESC` is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("`DESC` =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("`DESC` <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("`DESC` >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("`DESC` >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("`DESC` <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("`DESC` <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("`DESC` like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("`DESC` not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("`DESC` in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("`DESC` not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("`DESC` between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("`DESC` not between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andTitleDspIsNull() {
            addCriterion("TITLE_DSP is null");
            return (Criteria) this;
        }

        public Criteria andTitleDspIsNotNull() {
            addCriterion("TITLE_DSP is not null");
            return (Criteria) this;
        }

        public Criteria andTitleDspEqualTo(String value) {
            addCriterion("TITLE_DSP =", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspNotEqualTo(String value) {
            addCriterion("TITLE_DSP <>", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspGreaterThan(String value) {
            addCriterion("TITLE_DSP >", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_DSP >=", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspLessThan(String value) {
            addCriterion("TITLE_DSP <", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspLessThanOrEqualTo(String value) {
            addCriterion("TITLE_DSP <=", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspLike(String value) {
            addCriterion("TITLE_DSP like", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspNotLike(String value) {
            addCriterion("TITLE_DSP not like", value, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspIn(List<String> values) {
            addCriterion("TITLE_DSP in", values, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspNotIn(List<String> values) {
            addCriterion("TITLE_DSP not in", values, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspBetween(String value1, String value2) {
            addCriterion("TITLE_DSP between", value1, value2, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTitleDspNotBetween(String value1, String value2) {
            addCriterion("TITLE_DSP not between", value1, value2, "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspIsNull() {
            addCriterion("TIPS_DSP is null");
            return (Criteria) this;
        }

        public Criteria andTipsDspIsNotNull() {
            addCriterion("TIPS_DSP is not null");
            return (Criteria) this;
        }

        public Criteria andTipsDspEqualTo(String value) {
            addCriterion("TIPS_DSP =", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspNotEqualTo(String value) {
            addCriterion("TIPS_DSP <>", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspGreaterThan(String value) {
            addCriterion("TIPS_DSP >", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspGreaterThanOrEqualTo(String value) {
            addCriterion("TIPS_DSP >=", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspLessThan(String value) {
            addCriterion("TIPS_DSP <", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspLessThanOrEqualTo(String value) {
            addCriterion("TIPS_DSP <=", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspLike(String value) {
            addCriterion("TIPS_DSP like", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspNotLike(String value) {
            addCriterion("TIPS_DSP not like", value, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspIn(List<String> values) {
            addCriterion("TIPS_DSP in", values, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspNotIn(List<String> values) {
            addCriterion("TIPS_DSP not in", values, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspBetween(String value1, String value2) {
            addCriterion("TIPS_DSP between", value1, value2, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspNotBetween(String value1, String value2) {
            addCriterion("TIPS_DSP not between", value1, value2, "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNull() {
            addCriterion("FORM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFormTypeIsNotNull() {
            addCriterion("FORM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFormTypeEqualTo(String value) {
            addCriterion("FORM_TYPE =", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotEqualTo(String value) {
            addCriterion("FORM_TYPE <>", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThan(String value) {
            addCriterion("FORM_TYPE >", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FORM_TYPE >=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThan(String value) {
            addCriterion("FORM_TYPE <", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLessThanOrEqualTo(String value) {
            addCriterion("FORM_TYPE <=", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLike(String value) {
            addCriterion("FORM_TYPE like", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotLike(String value) {
            addCriterion("FORM_TYPE not like", value, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeIn(List<String> values) {
            addCriterion("FORM_TYPE in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotIn(List<String> values) {
            addCriterion("FORM_TYPE not in", values, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeBetween(String value1, String value2) {
            addCriterion("FORM_TYPE between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andFormTypeNotBetween(String value1, String value2) {
            addCriterion("FORM_TYPE not between", value1, value2, "formType");
            return (Criteria) this;
        }

        public Criteria andTabTypeIsNull() {
            addCriterion("TAB_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTabTypeIsNotNull() {
            addCriterion("TAB_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTabTypeEqualTo(String value) {
            addCriterion("TAB_TYPE =", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeNotEqualTo(String value) {
            addCriterion("TAB_TYPE <>", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeGreaterThan(String value) {
            addCriterion("TAB_TYPE >", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TAB_TYPE >=", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeLessThan(String value) {
            addCriterion("TAB_TYPE <", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeLessThanOrEqualTo(String value) {
            addCriterion("TAB_TYPE <=", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeLike(String value) {
            addCriterion("TAB_TYPE like", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeNotLike(String value) {
            addCriterion("TAB_TYPE not like", value, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeIn(List<String> values) {
            addCriterion("TAB_TYPE in", values, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeNotIn(List<String> values) {
            addCriterion("TAB_TYPE not in", values, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeBetween(String value1, String value2) {
            addCriterion("TAB_TYPE between", value1, value2, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeNotBetween(String value1, String value2) {
            addCriterion("TAB_TYPE not between", value1, value2, "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspIsNull() {
            addCriterion("TAB_TYPE_DSP is null");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspIsNotNull() {
            addCriterion("TAB_TYPE_DSP is not null");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspEqualTo(String value) {
            addCriterion("TAB_TYPE_DSP =", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspNotEqualTo(String value) {
            addCriterion("TAB_TYPE_DSP <>", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspGreaterThan(String value) {
            addCriterion("TAB_TYPE_DSP >", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspGreaterThanOrEqualTo(String value) {
            addCriterion("TAB_TYPE_DSP >=", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspLessThan(String value) {
            addCriterion("TAB_TYPE_DSP <", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspLessThanOrEqualTo(String value) {
            addCriterion("TAB_TYPE_DSP <=", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspLike(String value) {
            addCriterion("TAB_TYPE_DSP like", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspNotLike(String value) {
            addCriterion("TAB_TYPE_DSP not like", value, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspIn(List<String> values) {
            addCriterion("TAB_TYPE_DSP in", values, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspNotIn(List<String> values) {
            addCriterion("TAB_TYPE_DSP not in", values, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspBetween(String value1, String value2) {
            addCriterion("TAB_TYPE_DSP between", value1, value2, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspNotBetween(String value1, String value2) {
            addCriterion("TAB_TYPE_DSP not between", value1, value2, "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNull() {
            addCriterion("REQUIRED is null");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNotNull() {
            addCriterion("REQUIRED is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredEqualTo(Boolean value) {
            addCriterion("REQUIRED =", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotEqualTo(Boolean value) {
            addCriterion("REQUIRED <>", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThan(Boolean value) {
            addCriterion("REQUIRED >", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThanOrEqualTo(Boolean value) {
            addCriterion("REQUIRED >=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThan(Boolean value) {
            addCriterion("REQUIRED <", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThanOrEqualTo(Boolean value) {
            addCriterion("REQUIRED <=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLike(Boolean value) {
            addCriterion("REQUIRED like", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotLike(Boolean value) {
            addCriterion("REQUIRED not like", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredIn(List<Boolean> values) {
            addCriterion("REQUIRED in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotIn(List<Boolean> values) {
            addCriterion("REQUIRED not in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredBetween(Boolean value1, Boolean value2) {
            addCriterion("REQUIRED between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotBetween(Boolean value1, Boolean value2) {
            addCriterion("REQUIRED not between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andValidateTypeIsNull() {
            addCriterion("VALIDATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andValidateTypeIsNotNull() {
            addCriterion("VALIDATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andValidateTypeEqualTo(String value) {
            addCriterion("VALIDATE_TYPE =", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeNotEqualTo(String value) {
            addCriterion("VALIDATE_TYPE <>", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeGreaterThan(String value) {
            addCriterion("VALIDATE_TYPE >", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VALIDATE_TYPE >=", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeLessThan(String value) {
            addCriterion("VALIDATE_TYPE <", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeLessThanOrEqualTo(String value) {
            addCriterion("VALIDATE_TYPE <=", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeLike(String value) {
            addCriterion("VALIDATE_TYPE like", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeNotLike(String value) {
            addCriterion("VALIDATE_TYPE not like", value, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeIn(List<String> values) {
            addCriterion("VALIDATE_TYPE in", values, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeNotIn(List<String> values) {
            addCriterion("VALIDATE_TYPE not in", values, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeBetween(String value1, String value2) {
            addCriterion("VALIDATE_TYPE between", value1, value2, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateTypeNotBetween(String value1, String value2) {
            addCriterion("VALIDATE_TYPE not between", value1, value2, "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateValueIsNull() {
            addCriterion("VALIDATE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andValidateValueIsNotNull() {
            addCriterion("VALIDATE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andValidateValueEqualTo(String value) {
            addCriterion("VALIDATE_VALUE =", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueNotEqualTo(String value) {
            addCriterion("VALIDATE_VALUE <>", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueGreaterThan(String value) {
            addCriterion("VALIDATE_VALUE >", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueGreaterThanOrEqualTo(String value) {
            addCriterion("VALIDATE_VALUE >=", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueLessThan(String value) {
            addCriterion("VALIDATE_VALUE <", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueLessThanOrEqualTo(String value) {
            addCriterion("VALIDATE_VALUE <=", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueLike(String value) {
            addCriterion("VALIDATE_VALUE like", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueNotLike(String value) {
            addCriterion("VALIDATE_VALUE not like", value, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueIn(List<String> values) {
            addCriterion("VALIDATE_VALUE in", values, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueNotIn(List<String> values) {
            addCriterion("VALIDATE_VALUE not in", values, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueBetween(String value1, String value2) {
            addCriterion("VALIDATE_VALUE between", value1, value2, "validateValue");
            return (Criteria) this;
        }

        public Criteria andValidateValueNotBetween(String value1, String value2) {
            addCriterion("VALIDATE_VALUE not between", value1, value2, "validateValue");
            return (Criteria) this;
        }

        public Criteria andIndexIsNull() {
            addCriterion("`INDEX` is null");
            return (Criteria) this;
        }

        public Criteria andIndexIsNotNull() {
            addCriterion("`INDEX` is not null");
            return (Criteria) this;
        }

        public Criteria andIndexEqualTo(Integer value) {
            addCriterion("`INDEX` =", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotEqualTo(Integer value) {
            addCriterion("`INDEX` <>", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThan(Integer value) {
            addCriterion("`INDEX` >", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("`INDEX` >=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThan(Integer value) {
            addCriterion("`INDEX` <", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThanOrEqualTo(Integer value) {
            addCriterion("`INDEX` <=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexIn(List<Integer> values) {
            addCriterion("`INDEX` in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotIn(List<Integer> values) {
            addCriterion("`INDEX` not in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexBetween(Integer value1, Integer value2) {
            addCriterion("`INDEX` between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("`INDEX` not between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLikeInsensitive(String value) {
            addCriterion("upper(CFG_KEY) like", value.toUpperCase(), "cfgKey");
            return (Criteria) this;
        }

        public Criteria andDescLikeInsensitive(String value) {
            addCriterion("upper(`DESC`) like", value.toUpperCase(), "desc");
            return (Criteria) this;
        }

        public Criteria andTitleDspLikeInsensitive(String value) {
            addCriterion("upper(TITLE_DSP) like", value.toUpperCase(), "titleDsp");
            return (Criteria) this;
        }

        public Criteria andTipsDspLikeInsensitive(String value) {
            addCriterion("upper(TIPS_DSP) like", value.toUpperCase(), "tipsDsp");
            return (Criteria) this;
        }

        public Criteria andFormTypeLikeInsensitive(String value) {
            addCriterion("upper(FORM_TYPE) like", value.toUpperCase(), "formType");
            return (Criteria) this;
        }

        public Criteria andTabTypeLikeInsensitive(String value) {
            addCriterion("upper(TAB_TYPE) like", value.toUpperCase(), "tabType");
            return (Criteria) this;
        }

        public Criteria andTabTypeDspLikeInsensitive(String value) {
            addCriterion("upper(TAB_TYPE_DSP) like", value.toUpperCase(), "tabTypeDsp");
            return (Criteria) this;
        }

        public Criteria andValidateTypeLikeInsensitive(String value) {
            addCriterion("upper(VALIDATE_TYPE) like", value.toUpperCase(), "validateType");
            return (Criteria) this;
        }

        public Criteria andValidateValueLikeInsensitive(String value) {
            addCriterion("upper(VALIDATE_VALUE) like", value.toUpperCase(), "validateValue");
            return (Criteria) this;
        }

        public Criteria andIsNull(String filed) {
            addCriterion(filed + " is null");
            return (Criteria)this;
        }

        public Criteria andIsNotNull(String filed) {
            addCriterion(filed + " is not null");
            return (Criteria)this;
        }

        public Criteria andEqualTo(String filed, String value) {
            addCriterion(filed + " =", value, filed);
            return (Criteria)this;
        }

        public Criteria andNotEqualTo(String filed, String value) {
            addCriterion(filed + " <>", value, filed);
            return (Criteria)this;
        }

        public Criteria andGreaterThan(String filed, String value) {
            addCriterion(filed + " > ", value, filed);
            return (Criteria)this;
        }

        public Criteria andGreaterThanOrEqualTo(String filed, String value) {
            addCriterion(filed + " >=", value, filed);
            return (Criteria)this;
        }

        public Criteria andLessThan(String filed, String value) {
            addCriterion(filed + " <", value, filed);
            return (Criteria)this;
        }

        public Criteria andLessThanOrEqualTo(String filed, String value) {
            addCriterion(filed + " <=", value, filed);
            return (Criteria)this;
        }

        public Criteria andIn(String filed, List value) {
            addCriterion(filed + " in", value, filed);
            return (Criteria)this;
        }

        public Criteria andNotIn(String filed, List value) {
            addCriterion(filed + " not in", value, filed);
            return (Criteria)this;
        }

        public Criteria andBetween(String filed, String value1, String value2) {
            addCriterion(filed + " between", value1, value2, filed);
            return (Criteria)this;
        }

        public Criteria andNotBetween(String filed, String value1, String value2) {
            addCriterion(filed + " not between", value1, value2, filed);
            return (Criteria)this;
        }

        public Criteria andLike(String filed, String value) {
            addCriterion(filed + " like", value, filed);
            return (Criteria)this;
        }

        public Criteria andNotLike(String filed, String value) {
            addCriterion(filed + " not like", value, filed);
            return (Criteria)this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}