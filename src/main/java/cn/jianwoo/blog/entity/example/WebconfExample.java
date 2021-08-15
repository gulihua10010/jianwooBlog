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

        public Criteria andIntValueIsNull() {
            addCriterion("INT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andIntValueIsNotNull() {
            addCriterion("INT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andIntValueEqualTo(Integer value) {
            addCriterion("INT_VALUE =", value, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueNotEqualTo(Integer value) {
            addCriterion("INT_VALUE <>", value, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueGreaterThan(Integer value) {
            addCriterion("INT_VALUE >", value, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("INT_VALUE >=", value, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueLessThan(Integer value) {
            addCriterion("INT_VALUE <", value, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueLessThanOrEqualTo(Integer value) {
            addCriterion("INT_VALUE <=", value, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueIn(List<Integer> values) {
            addCriterion("INT_VALUE in", values, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueNotIn(List<Integer> values) {
            addCriterion("INT_VALUE not in", values, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueBetween(Integer value1, Integer value2) {
            addCriterion("INT_VALUE between", value1, value2, "intValue");
            return (Criteria) this;
        }

        public Criteria andIntValueNotBetween(Integer value1, Integer value2) {
            addCriterion("INT_VALUE not between", value1, value2, "intValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueIsNull() {
            addCriterion("FLOAT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andFloatValueIsNotNull() {
            addCriterion("FLOAT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andFloatValueEqualTo(BigDecimal value) {
            addCriterion("FLOAT_VALUE =", value, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueNotEqualTo(BigDecimal value) {
            addCriterion("FLOAT_VALUE <>", value, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueGreaterThan(BigDecimal value) {
            addCriterion("FLOAT_VALUE >", value, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FLOAT_VALUE >=", value, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueLessThan(BigDecimal value) {
            addCriterion("FLOAT_VALUE <", value, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FLOAT_VALUE <=", value, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueIn(List<BigDecimal> values) {
            addCriterion("FLOAT_VALUE in", values, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueNotIn(List<BigDecimal> values) {
            addCriterion("FLOAT_VALUE not in", values, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FLOAT_VALUE between", value1, value2, "floatValue");
            return (Criteria) this;
        }

        public Criteria andFloatValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FLOAT_VALUE not between", value1, value2, "floatValue");
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

        public Criteria andKeyLikeInsensitive(String value) {
            addCriterion("upper(`KEY`) like", value.toUpperCase(), "key");
            return (Criteria) this;
        }

        public Criteria andDescLikeInsensitive(String value) {
            addCriterion("upper(`DESC`) like", value.toUpperCase(), "desc");
            return (Criteria) this;
        }

        public Criteria andValueTypeLikeInsensitive(String value) {
            addCriterion("upper(VALUE_TYPE) like", value.toUpperCase(), "valueType");
            return (Criteria) this;
        }

        public Criteria andStringValueLikeInsensitive(String value) {
            addCriterion("upper(STRING_VALUE) like", value.toUpperCase(), "stringValue");
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