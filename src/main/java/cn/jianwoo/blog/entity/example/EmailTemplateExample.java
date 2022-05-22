package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public EmailTemplateExample() {
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

        public Criteria andEmailTplCodeIsNull() {
            addCriterion("EMAIL_TPL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeIsNotNull() {
            addCriterion("EMAIL_TPL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeEqualTo(String value) {
            addCriterion("EMAIL_TPL_CODE =", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeNotEqualTo(String value) {
            addCriterion("EMAIL_TPL_CODE <>", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeGreaterThan(String value) {
            addCriterion("EMAIL_TPL_CODE >", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_TPL_CODE >=", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeLessThan(String value) {
            addCriterion("EMAIL_TPL_CODE <", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_TPL_CODE <=", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeLike(String value) {
            addCriterion("EMAIL_TPL_CODE like", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeNotLike(String value) {
            addCriterion("EMAIL_TPL_CODE not like", value, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeIn(List<String> values) {
            addCriterion("EMAIL_TPL_CODE in", values, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeNotIn(List<String> values) {
            addCriterion("EMAIL_TPL_CODE not in", values, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeBetween(String value1, String value2) {
            addCriterion("EMAIL_TPL_CODE between", value1, value2, "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeNotBetween(String value1, String value2) {
            addCriterion("EMAIL_TPL_CODE not between", value1, value2, "emailTplCode");
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

        public Criteria andSubjectIsNull() {
            addCriterion("SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("SUBJECT =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("SUBJECT <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("SUBJECT >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("SUBJECT <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("SUBJECT like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("SUBJECT not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("SUBJECT in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("SUBJECT not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("SUBJECT between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("SUBJECT not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataIsNull() {
            addCriterion("TEST_JSON_DATA is null");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataIsNotNull() {
            addCriterion("TEST_JSON_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataEqualTo(String value) {
            addCriterion("TEST_JSON_DATA =", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataNotEqualTo(String value) {
            addCriterion("TEST_JSON_DATA <>", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataGreaterThan(String value) {
            addCriterion("TEST_JSON_DATA >", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_JSON_DATA >=", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataLessThan(String value) {
            addCriterion("TEST_JSON_DATA <", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataLessThanOrEqualTo(String value) {
            addCriterion("TEST_JSON_DATA <=", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataLike(String value) {
            addCriterion("TEST_JSON_DATA like", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataNotLike(String value) {
            addCriterion("TEST_JSON_DATA not like", value, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataIn(List<String> values) {
            addCriterion("TEST_JSON_DATA in", values, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataNotIn(List<String> values) {
            addCriterion("TEST_JSON_DATA not in", values, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataBetween(String value1, String value2) {
            addCriterion("TEST_JSON_DATA between", value1, value2, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataNotBetween(String value1, String value2) {
            addCriterion("TEST_JSON_DATA not between", value1, value2, "testJsonData");
            return (Criteria) this;
        }

        public Criteria andStatusUsedIsNull() {
            addCriterion("STATUS_USED is null");
            return (Criteria) this;
        }

        public Criteria andStatusUsedIsNotNull() {
            addCriterion("STATUS_USED is not null");
            return (Criteria) this;
        }

        public Criteria andStatusUsedEqualTo(Boolean value) {
            addCriterion("STATUS_USED =", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedNotEqualTo(Boolean value) {
            addCriterion("STATUS_USED <>", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedGreaterThan(Boolean value) {
            addCriterion("STATUS_USED >", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("STATUS_USED >=", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedLessThan(Boolean value) {
            addCriterion("STATUS_USED <", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedLessThanOrEqualTo(Boolean value) {
            addCriterion("STATUS_USED <=", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedLike(Boolean value) {
            addCriterion("STATUS_USED like", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedNotLike(Boolean value) {
            addCriterion("STATUS_USED not like", value, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedIn(List<Boolean> values) {
            addCriterion("STATUS_USED in", values, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedNotIn(List<Boolean> values) {
            addCriterion("STATUS_USED not in", values, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedBetween(Boolean value1, Boolean value2) {
            addCriterion("STATUS_USED between", value1, value2, "statusUsed");
            return (Criteria) this;
        }

        public Criteria andStatusUsedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("STATUS_USED not between", value1, value2, "statusUsed");
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

        public Criteria andEmailTplCodeLikeInsensitive(String value) {
            addCriterion("upper(EMAIL_TPL_CODE) like", value.toUpperCase(), "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andDescLikeInsensitive(String value) {
            addCriterion("upper(`DESC`) like", value.toUpperCase(), "desc");
            return (Criteria) this;
        }

        public Criteria andSubjectLikeInsensitive(String value) {
            addCriterion("upper(SUBJECT) like", value.toUpperCase(), "subject");
            return (Criteria) this;
        }

        public Criteria andTestJsonDataLikeInsensitive(String value) {
            addCriterion("upper(TEST_JSON_DATA) like", value.toUpperCase(), "testJsonData");
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