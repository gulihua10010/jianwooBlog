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


    public String getOrderByClause() {
        return orderByClause;
    }


    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }


    public boolean isDistinct() {
        return distinct;
    }


    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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


    public Integer getStart() {
        return start;
    }


    public void setStart(Integer start) {
        this.start = start;
    }


    public Integer getRows() {
        return rows;
    }


    public void setRows(Integer rows) {
        this.rows = rows;
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


        public Criteria andBooleanValueLike(String value) {
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


        public Criteria andIsNull(String filed) {
            addCriterion(filed + " is null");
            return (Criteria) this;
        }


        public Criteria andIsNotNull(String filed) {
            addCriterion(filed + " is not null");
            return (Criteria) this;
        }


        public Criteria andEqualTo(String filed, String value) {
            addCriterion(filed + " =", value, filed);
            return (Criteria) this;
        }


        public Criteria andNotEqualTo(String filed, String value) {
            addCriterion(filed + " <>", value, filed);
            return (Criteria) this;
        }


        public Criteria andGreaterThan(String filed, String value) {
            addCriterion(filed + " > ", value, filed);
            return (Criteria) this;
        }


        public Criteria andGreaterThanOrEqualTo(String filed, String value) {
            addCriterion(filed + " >=", value, filed);
            return (Criteria) this;
        }


        public Criteria andLessThan(String filed, String value) {
            addCriterion(filed + " <", value, filed);
            return (Criteria) this;
        }


        public Criteria andLessThanOrEqualTo(String filed, String value) {
            addCriterion(filed + " <=", value, filed);
            return (Criteria) this;
        }


        public Criteria andIn(String filed, List value) {
            addCriterion(filed + " in", value, filed);
            return (Criteria) this;
        }


        public Criteria andNotIn(String filed, List value) {
            addCriterion(filed + " not in", value, filed);
            return (Criteria) this;
        }


        public Criteria andBetween(String filed, String value1, String value2) {
            addCriterion(filed + " between", value1, value2, filed);
            return (Criteria) this;
        }


        public Criteria andNotBetween(String filed, String value1, String value2) {
            addCriterion(filed + " not between", value1, value2, filed);
            return (Criteria) this;
        }


        public Criteria andLike(String filed, String value) {
            addCriterion(filed + " like", value, filed);
            return (Criteria) this;
        }


        public Criteria andNotLike(String filed, String value) {
            addCriterion(filed + " not like", value, filed);
            return (Criteria) this;
        }


        public Criteria andKeyLikeInsensitive(String value) {
            addCriterion("upper(KEY) like", value.toUpperCase(), "key");
            return (Criteria) this;
        }


        public Criteria andStringValueLikeInsensitive(String value) {
            addCriterion("upper(STRING_VALUE) like", value.toUpperCase(), "stringValue");
            return (Criteria) this;
        }


        public Criteria andBooleanValueLikeInsensitive(String value) {
            addCriterion("upper(BOOLEAN_VALUE) like", value.toUpperCase(), "booleanValue");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private final String condition;
        private final String typeHandler;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;

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
    }
}