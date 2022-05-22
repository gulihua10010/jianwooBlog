package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AsyncProcTaskTypeCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public AsyncProcTaskTypeCfgExample() {
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

        public Criteria andTaskTypeIsNull() {
            addCriterion("TASK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("TASK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(String value) {
            addCriterion("TASK_TYPE =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(String value) {
            addCriterion("TASK_TYPE <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(String value) {
            addCriterion("TASK_TYPE >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(String value) {
            addCriterion("TASK_TYPE <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLike(String value) {
            addCriterion("TASK_TYPE like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotLike(String value) {
            addCriterion("TASK_TYPE not like", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<String> values) {
            addCriterion("TASK_TYPE in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<String> values) {
            addCriterion("TASK_TYPE not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(String value1, String value2) {
            addCriterion("TASK_TYPE between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameIsNull() {
            addCriterion("TASK_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameIsNotNull() {
            addCriterion("TASK_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameEqualTo(String value) {
            addCriterion("TASK_TYPE_NAME =", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameNotEqualTo(String value) {
            addCriterion("TASK_TYPE_NAME <>", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameGreaterThan(String value) {
            addCriterion("TASK_TYPE_NAME >", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE_NAME >=", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameLessThan(String value) {
            addCriterion("TASK_TYPE_NAME <", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TASK_TYPE_NAME <=", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameLike(String value) {
            addCriterion("TASK_TYPE_NAME like", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameNotLike(String value) {
            addCriterion("TASK_TYPE_NAME not like", value, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameIn(List<String> values) {
            addCriterion("TASK_TYPE_NAME in", values, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameNotIn(List<String> values) {
            addCriterion("TASK_TYPE_NAME not in", values, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameBetween(String value1, String value2) {
            addCriterion("TASK_TYPE_NAME between", value1, value2, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameNotBetween(String value1, String value2) {
            addCriterion("TASK_TYPE_NAME not between", value1, value2, "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdIsNull() {
            addCriterion("EXEC_SRV_ID is null");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdIsNotNull() {
            addCriterion("EXEC_SRV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdEqualTo(String value) {
            addCriterion("EXEC_SRV_ID =", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdNotEqualTo(String value) {
            addCriterion("EXEC_SRV_ID <>", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdGreaterThan(String value) {
            addCriterion("EXEC_SRV_ID >", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXEC_SRV_ID >=", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdLessThan(String value) {
            addCriterion("EXEC_SRV_ID <", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdLessThanOrEqualTo(String value) {
            addCriterion("EXEC_SRV_ID <=", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdLike(String value) {
            addCriterion("EXEC_SRV_ID like", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdNotLike(String value) {
            addCriterion("EXEC_SRV_ID not like", value, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdIn(List<String> values) {
            addCriterion("EXEC_SRV_ID in", values, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdNotIn(List<String> values) {
            addCriterion("EXEC_SRV_ID not in", values, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdBetween(String value1, String value2) {
            addCriterion("EXEC_SRV_ID between", value1, value2, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdNotBetween(String value1, String value2) {
            addCriterion("EXEC_SRV_ID not between", value1, value2, "execSrvId");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryIsNull() {
            addCriterion("TIMES_MAX_RETRY is null");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryIsNotNull() {
            addCriterion("TIMES_MAX_RETRY is not null");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryEqualTo(Integer value) {
            addCriterion("TIMES_MAX_RETRY =", value, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryNotEqualTo(Integer value) {
            addCriterion("TIMES_MAX_RETRY <>", value, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryGreaterThan(Integer value) {
            addCriterion("TIMES_MAX_RETRY >", value, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIMES_MAX_RETRY >=", value, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryLessThan(Integer value) {
            addCriterion("TIMES_MAX_RETRY <", value, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryLessThanOrEqualTo(Integer value) {
            addCriterion("TIMES_MAX_RETRY <=", value, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryIn(List<Integer> values) {
            addCriterion("TIMES_MAX_RETRY in", values, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryNotIn(List<Integer> values) {
            addCriterion("TIMES_MAX_RETRY not in", values, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryBetween(Integer value1, Integer value2) {
            addCriterion("TIMES_MAX_RETRY between", value1, value2, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andTimesMaxRetryNotBetween(Integer value1, Integer value2) {
            addCriterion("TIMES_MAX_RETRY not between", value1, value2, "timesMaxRetry");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("CREATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("CREATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("CREATED_TIME =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("CREATED_TIME <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("CREATED_TIME >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATED_TIME >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("CREATED_TIME <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATED_TIME <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("CREATED_TIME in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("CREATED_TIME not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("CREATED_TIME between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATED_TIME not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("CREATED_BY like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("CREATED_BY not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLikeInsensitive(String value) {
            addCriterion("upper(TASK_TYPE) like", value.toUpperCase(), "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNameLikeInsensitive(String value) {
            addCriterion("upper(TASK_TYPE_NAME) like", value.toUpperCase(), "taskTypeName");
            return (Criteria) this;
        }

        public Criteria andExecSrvIdLikeInsensitive(String value) {
            addCriterion("upper(EXEC_SRV_ID) like", value.toUpperCase(), "execSrvId");
            return (Criteria) this;
        }

        public Criteria andCreatedByLikeInsensitive(String value) {
            addCriterion("upper(CREATED_BY) like", value.toUpperCase(), "createdBy");
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