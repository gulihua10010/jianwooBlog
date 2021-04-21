package cn.jianwoo.blog.entity.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebconfExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public WebconfExample() {
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

        public Criteria andKeyIsNull() {
            addCriterion("`KEY` is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("`KEY` is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("`KEY` =", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("`KEY` <>", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("`KEY` >", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("`KEY` >=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("`KEY` <", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("`KEY` <=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            addCriterion("`KEY` like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("`KEY` not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("`KEY` in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("`KEY` not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("`KEY` between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("`KEY` not between", value1, value2, "key");
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

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNull() {
            addCriterion("VALUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("VALUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(String value) {
            addCriterion("VALUE_TYPE =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(String value) {
            addCriterion("VALUE_TYPE <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(String value) {
            addCriterion("VALUE_TYPE >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE_TYPE >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(String value) {
            addCriterion("VALUE_TYPE <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(String value) {
            addCriterion("VALUE_TYPE <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLike(String value) {
            addCriterion("VALUE_TYPE like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotLike(String value) {
            addCriterion("VALUE_TYPE not like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<String> values) {
            addCriterion("VALUE_TYPE in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<String> values) {
            addCriterion("VALUE_TYPE not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(String value1, String value2) {
            addCriterion("VALUE_TYPE between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(String value1, String value2) {
            addCriterion("VALUE_TYPE not between", value1, value2, "valueType");
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

        public Criteria andStringValueIsNull() {
            addCriterion("STRING_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andStringValueIsNotNull() {
            addCriterion("STRING_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andStringValueEqualTo(String value) {
            addCriterion("STRING_VALUE =", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotEqualTo(String value) {
            addCriterion("STRING_VALUE <>", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueGreaterThan(String value) {
            addCriterion("STRING_VALUE >", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueGreaterThanOrEqualTo(String value) {
            addCriterion("STRING_VALUE >=", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueLessThan(String value) {
            addCriterion("STRING_VALUE <", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueLessThanOrEqualTo(String value) {
            addCriterion("STRING_VALUE <=", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueLike(String value) {
            addCriterion("STRING_VALUE like", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotLike(String value) {
            addCriterion("STRING_VALUE not like", value, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueIn(List<String> values) {
            addCriterion("STRING_VALUE in", values, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotIn(List<String> values) {
            addCriterion("STRING_VALUE not in", values, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueBetween(String value1, String value2) {
            addCriterion("STRING_VALUE between", value1, value2, "stringValue");
            return (Criteria) this;
        }

        public Criteria andStringValueNotBetween(String value1, String value2) {
            addCriterion("STRING_VALUE not between", value1, value2, "stringValue");
            return (Criteria) this;
        }

        public Criteria andNumValueIsNull() {
            addCriterion("NUM_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andNumValueIsNotNull() {
            addCriterion("NUM_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andNumValueEqualTo(BigDecimal value) {
            addCriterion("NUM_VALUE =", value, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueNotEqualTo(BigDecimal value) {
            addCriterion("NUM_VALUE <>", value, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueGreaterThan(BigDecimal value) {
            addCriterion("NUM_VALUE >", value, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("NUM_VALUE >=", value, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueLessThan(BigDecimal value) {
            addCriterion("NUM_VALUE <", value, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("NUM_VALUE <=", value, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueIn(List<BigDecimal> values) {
            addCriterion("NUM_VALUE in", values, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueNotIn(List<BigDecimal> values) {
            addCriterion("NUM_VALUE not in", values, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NUM_VALUE between", value1, value2, "numValue");
            return (Criteria) this;
        }

        public Criteria andNumValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("NUM_VALUE not between", value1, value2, "numValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueIsNull() {
            addCriterion("BOOLEAN_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andBooleanValueIsNotNull() {
            addCriterion("BOOLEAN_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andBooleanValueEqualTo(Boolean value) {
            addCriterion("BOOLEAN_VALUE =", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotEqualTo(Boolean value) {
            addCriterion("BOOLEAN_VALUE <>", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueGreaterThan(Boolean value) {
            addCriterion("BOOLEAN_VALUE >", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueGreaterThanOrEqualTo(Boolean value) {
            addCriterion("BOOLEAN_VALUE >=", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueLessThan(Boolean value) {
            addCriterion("BOOLEAN_VALUE <", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueLessThanOrEqualTo(Boolean value) {
            addCriterion("BOOLEAN_VALUE <=", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueLike(Boolean value) {
            addCriterion("BOOLEAN_VALUE like", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotLike(Boolean value) {
            addCriterion("BOOLEAN_VALUE not like", value, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueIn(List<Boolean> values) {
            addCriterion("BOOLEAN_VALUE in", values, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotIn(List<Boolean> values) {
            addCriterion("BOOLEAN_VALUE not in", values, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueBetween(Boolean value1, Boolean value2) {
            addCriterion("BOOLEAN_VALUE between", value1, value2, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andBooleanValueNotBetween(Boolean value1, Boolean value2) {
            addCriterion("BOOLEAN_VALUE not between", value1, value2, "booleanValue");
            return (Criteria) this;
        }

        public Criteria andDateValueIsNull() {
            addCriterion("DATE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDateValueIsNotNull() {
            addCriterion("DATE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDateValueEqualTo(Date value) {
            addCriterion("DATE_VALUE =", value, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueNotEqualTo(Date value) {
            addCriterion("DATE_VALUE <>", value, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueGreaterThan(Date value) {
            addCriterion("DATE_VALUE >", value, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueGreaterThanOrEqualTo(Date value) {
            addCriterion("DATE_VALUE >=", value, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueLessThan(Date value) {
            addCriterion("DATE_VALUE <", value, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueLessThanOrEqualTo(Date value) {
            addCriterion("DATE_VALUE <=", value, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueIn(List<Date> values) {
            addCriterion("DATE_VALUE in", values, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueNotIn(List<Date> values) {
            addCriterion("DATE_VALUE not in", values, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueBetween(Date value1, Date value2) {
            addCriterion("DATE_VALUE between", value1, value2, "dateValue");
            return (Criteria) this;
        }

        public Criteria andDateValueNotBetween(Date value1, Date value2) {
            addCriterion("DATE_VALUE not between", value1, value2, "dateValue");
            return (Criteria) this;
        }

        public Criteria andMandatoryIsNull() {
            addCriterion("MANDATORY is null");
            return (Criteria) this;
        }

        public Criteria andMandatoryIsNotNull() {
            addCriterion("MANDATORY is not null");
            return (Criteria) this;
        }

        public Criteria andMandatoryEqualTo(Boolean value) {
            addCriterion("MANDATORY =", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryNotEqualTo(Boolean value) {
            addCriterion("MANDATORY <>", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryGreaterThan(Boolean value) {
            addCriterion("MANDATORY >", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("MANDATORY >=", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryLessThan(Boolean value) {
            addCriterion("MANDATORY <", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryLessThanOrEqualTo(Boolean value) {
            addCriterion("MANDATORY <=", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryLike(Boolean value) {
            addCriterion("MANDATORY like", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryNotLike(Boolean value) {
            addCriterion("MANDATORY not like", value, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryIn(List<Boolean> values) {
            addCriterion("MANDATORY in", values, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryNotIn(List<Boolean> values) {
            addCriterion("MANDATORY not in", values, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryBetween(Boolean value1, Boolean value2) {
            addCriterion("MANDATORY between", value1, value2, "mandatory");
            return (Criteria) this;
        }

        public Criteria andMandatoryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("MANDATORY not between", value1, value2, "mandatory");
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

        public Criteria andValidIsNull() {
            addCriterion("`VALID` is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("`VALID` is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Boolean value) {
            addCriterion("`VALID` =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Boolean value) {
            addCriterion("`VALID` <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Boolean value) {
            addCriterion("`VALID` >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`VALID` >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Boolean value) {
            addCriterion("`VALID` <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Boolean value) {
            addCriterion("`VALID` <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(Boolean value) {
            addCriterion("`VALID` like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(Boolean value) {
            addCriterion("`VALID` not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Boolean> values) {
            addCriterion("`VALID` in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Boolean> values) {
            addCriterion("`VALID` not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Boolean value1, Boolean value2) {
            addCriterion("`VALID` between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`VALID` not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andKeyLikeInsensitive(String value) {
            addCriterion("upper(`KEY`) like", value.toUpperCase(), "key");
            return (Criteria) this;
        }

        public Criteria andDescLikeInsensitive(String value) {
            addCriterion("upper(`DESC`) like", value.toUpperCase(), "desc");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(TITLE) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andValueTypeLikeInsensitive(String value) {
            addCriterion("upper(VALUE_TYPE) like", value.toUpperCase(), "valueType");
            return (Criteria) this;
        }

        public Criteria andFormTypeLikeInsensitive(String value) {
            addCriterion("upper(FORM_TYPE) like", value.toUpperCase(), "formType");
            return (Criteria) this;
        }

        public Criteria andStringValueLikeInsensitive(String value) {
            addCriterion("upper(STRING_VALUE) like", value.toUpperCase(), "stringValue");
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