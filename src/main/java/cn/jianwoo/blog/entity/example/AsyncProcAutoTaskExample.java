package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AsyncProcAutoTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public AsyncProcAutoTaskExample() {
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

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Long value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Long value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Long value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Long value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Long value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Long> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Long> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Long value1, Long value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Long value1, Long value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (Criteria) this;
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

        public Criteria andTaskDataIsNull() {
            addCriterion("TASK_DATA is null");
            return (Criteria) this;
        }

        public Criteria andTaskDataIsNotNull() {
            addCriterion("TASK_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andTaskDataEqualTo(String value) {
            addCriterion("TASK_DATA =", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataNotEqualTo(String value) {
            addCriterion("TASK_DATA <>", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataGreaterThan(String value) {
            addCriterion("TASK_DATA >", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_DATA >=", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataLessThan(String value) {
            addCriterion("TASK_DATA <", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataLessThanOrEqualTo(String value) {
            addCriterion("TASK_DATA <=", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataLike(String value) {
            addCriterion("TASK_DATA like", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataNotLike(String value) {
            addCriterion("TASK_DATA not like", value, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataIn(List<String> values) {
            addCriterion("TASK_DATA in", values, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataNotIn(List<String> values) {
            addCriterion("TASK_DATA not in", values, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataBetween(String value1, String value2) {
            addCriterion("TASK_DATA between", value1, value2, "taskData");
            return (Criteria) this;
        }

        public Criteria andTaskDataNotBetween(String value1, String value2) {
            addCriterion("TASK_DATA not between", value1, value2, "taskData");
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

        public Criteria andStatusProcIsNull() {
            addCriterion("STATUS_PROC is null");
            return (Criteria) this;
        }

        public Criteria andStatusProcIsNotNull() {
            addCriterion("STATUS_PROC is not null");
            return (Criteria) this;
        }

        public Criteria andStatusProcEqualTo(String value) {
            addCriterion("STATUS_PROC =", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcNotEqualTo(String value) {
            addCriterion("STATUS_PROC <>", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcGreaterThan(String value) {
            addCriterion("STATUS_PROC >", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_PROC >=", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcLessThan(String value) {
            addCriterion("STATUS_PROC <", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcLessThanOrEqualTo(String value) {
            addCriterion("STATUS_PROC <=", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcLike(String value) {
            addCriterion("STATUS_PROC like", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcNotLike(String value) {
            addCriterion("STATUS_PROC not like", value, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcIn(List<String> values) {
            addCriterion("STATUS_PROC in", values, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcNotIn(List<String> values) {
            addCriterion("STATUS_PROC not in", values, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcBetween(String value1, String value2) {
            addCriterion("STATUS_PROC between", value1, value2, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcNotBetween(String value1, String value2) {
            addCriterion("STATUS_PROC not between", value1, value2, "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescIsNull() {
            addCriterion("STATUS_PROC_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescIsNotNull() {
            addCriterion("STATUS_PROC_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescEqualTo(String value) {
            addCriterion("STATUS_PROC_DESC =", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescNotEqualTo(String value) {
            addCriterion("STATUS_PROC_DESC <>", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescGreaterThan(String value) {
            addCriterion("STATUS_PROC_DESC >", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_PROC_DESC >=", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescLessThan(String value) {
            addCriterion("STATUS_PROC_DESC <", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescLessThanOrEqualTo(String value) {
            addCriterion("STATUS_PROC_DESC <=", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescLike(String value) {
            addCriterion("STATUS_PROC_DESC like", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescNotLike(String value) {
            addCriterion("STATUS_PROC_DESC not like", value, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescIn(List<String> values) {
            addCriterion("STATUS_PROC_DESC in", values, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescNotIn(List<String> values) {
            addCriterion("STATUS_PROC_DESC not in", values, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescBetween(String value1, String value2) {
            addCriterion("STATUS_PROC_DESC between", value1, value2, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescNotBetween(String value1, String value2) {
            addCriterion("STATUS_PROC_DESC not between", value1, value2, "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeIsNull() {
            addCriterion("PROC_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeIsNotNull() {
            addCriterion("PROC_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeEqualTo(Date value) {
            addCriterion("PROC_START_TIME =", value, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeNotEqualTo(Date value) {
            addCriterion("PROC_START_TIME <>", value, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeGreaterThan(Date value) {
            addCriterion("PROC_START_TIME >", value, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PROC_START_TIME >=", value, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeLessThan(Date value) {
            addCriterion("PROC_START_TIME <", value, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("PROC_START_TIME <=", value, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeIn(List<Date> values) {
            addCriterion("PROC_START_TIME in", values, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeNotIn(List<Date> values) {
            addCriterion("PROC_START_TIME not in", values, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeBetween(Date value1, Date value2) {
            addCriterion("PROC_START_TIME between", value1, value2, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("PROC_START_TIME not between", value1, value2, "procStartTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeIsNull() {
            addCriterion("PROC_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeIsNotNull() {
            addCriterion("PROC_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeEqualTo(Date value) {
            addCriterion("PROC_END_TIME =", value, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeNotEqualTo(Date value) {
            addCriterion("PROC_END_TIME <>", value, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeGreaterThan(Date value) {
            addCriterion("PROC_END_TIME >", value, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PROC_END_TIME >=", value, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeLessThan(Date value) {
            addCriterion("PROC_END_TIME <", value, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("PROC_END_TIME <=", value, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeIn(List<Date> values) {
            addCriterion("PROC_END_TIME in", values, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeNotIn(List<Date> values) {
            addCriterion("PROC_END_TIME not in", values, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeBetween(Date value1, Date value2) {
            addCriterion("PROC_END_TIME between", value1, value2, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andProcEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("PROC_END_TIME not between", value1, value2, "procEndTime");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedIsNull() {
            addCriterion("TIMES_PROC_FAILED is null");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedIsNotNull() {
            addCriterion("TIMES_PROC_FAILED is not null");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedEqualTo(Integer value) {
            addCriterion("TIMES_PROC_FAILED =", value, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedNotEqualTo(Integer value) {
            addCriterion("TIMES_PROC_FAILED <>", value, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedGreaterThan(Integer value) {
            addCriterion("TIMES_PROC_FAILED >", value, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedGreaterThanOrEqualTo(Integer value) {
            addCriterion("TIMES_PROC_FAILED >=", value, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedLessThan(Integer value) {
            addCriterion("TIMES_PROC_FAILED <", value, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedLessThanOrEqualTo(Integer value) {
            addCriterion("TIMES_PROC_FAILED <=", value, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedIn(List<Integer> values) {
            addCriterion("TIMES_PROC_FAILED in", values, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedNotIn(List<Integer> values) {
            addCriterion("TIMES_PROC_FAILED not in", values, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedBetween(Integer value1, Integer value2) {
            addCriterion("TIMES_PROC_FAILED between", value1, value2, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andTimesProcFailedNotBetween(Integer value1, Integer value2) {
            addCriterion("TIMES_PROC_FAILED not between", value1, value2, "timesProcFailed");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeIsNull() {
            addCriterion("FAILED_REASON_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeIsNotNull() {
            addCriterion("FAILED_REASON_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeEqualTo(String value) {
            addCriterion("FAILED_REASON_CODE =", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeNotEqualTo(String value) {
            addCriterion("FAILED_REASON_CODE <>", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeGreaterThan(String value) {
            addCriterion("FAILED_REASON_CODE >", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FAILED_REASON_CODE >=", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeLessThan(String value) {
            addCriterion("FAILED_REASON_CODE <", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeLessThanOrEqualTo(String value) {
            addCriterion("FAILED_REASON_CODE <=", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeLike(String value) {
            addCriterion("FAILED_REASON_CODE like", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeNotLike(String value) {
            addCriterion("FAILED_REASON_CODE not like", value, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeIn(List<String> values) {
            addCriterion("FAILED_REASON_CODE in", values, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeNotIn(List<String> values) {
            addCriterion("FAILED_REASON_CODE not in", values, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeBetween(String value1, String value2) {
            addCriterion("FAILED_REASON_CODE between", value1, value2, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeNotBetween(String value1, String value2) {
            addCriterion("FAILED_REASON_CODE not between", value1, value2, "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescIsNull() {
            addCriterion("FAILED_REASON_DESC is null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescIsNotNull() {
            addCriterion("FAILED_REASON_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescEqualTo(String value) {
            addCriterion("FAILED_REASON_DESC =", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescNotEqualTo(String value) {
            addCriterion("FAILED_REASON_DESC <>", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescGreaterThan(String value) {
            addCriterion("FAILED_REASON_DESC >", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescGreaterThanOrEqualTo(String value) {
            addCriterion("FAILED_REASON_DESC >=", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescLessThan(String value) {
            addCriterion("FAILED_REASON_DESC <", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescLessThanOrEqualTo(String value) {
            addCriterion("FAILED_REASON_DESC <=", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescLike(String value) {
            addCriterion("FAILED_REASON_DESC like", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescNotLike(String value) {
            addCriterion("FAILED_REASON_DESC not like", value, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescIn(List<String> values) {
            addCriterion("FAILED_REASON_DESC in", values, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescNotIn(List<String> values) {
            addCriterion("FAILED_REASON_DESC not in", values, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescBetween(String value1, String value2) {
            addCriterion("FAILED_REASON_DESC between", value1, value2, "failedReasonDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescNotBetween(String value1, String value2) {
            addCriterion("FAILED_REASON_DESC not between", value1, value2, "failedReasonDesc");
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

        public Criteria andLastUpdTimeIsNull() {
            addCriterion("LAST_UPD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeIsNotNull() {
            addCriterion("LAST_UPD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeEqualTo(Date value) {
            addCriterion("LAST_UPD_TIME =", value, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeNotEqualTo(Date value) {
            addCriterion("LAST_UPD_TIME <>", value, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeGreaterThan(Date value) {
            addCriterion("LAST_UPD_TIME >", value, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_UPD_TIME >=", value, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeLessThan(Date value) {
            addCriterion("LAST_UPD_TIME <", value, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeLessThanOrEqualTo(Date value) {
            addCriterion("LAST_UPD_TIME <=", value, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeIn(List<Date> values) {
            addCriterion("LAST_UPD_TIME in", values, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeNotIn(List<Date> values) {
            addCriterion("LAST_UPD_TIME not in", values, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeBetween(Date value1, Date value2) {
            addCriterion("LAST_UPD_TIME between", value1, value2, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdTimeNotBetween(Date value1, Date value2) {
            addCriterion("LAST_UPD_TIME not between", value1, value2, "lastUpdTime");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLikeInsensitive(String value) {
            addCriterion("upper(TASK_TYPE) like", value.toUpperCase(), "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskDataLikeInsensitive(String value) {
            addCriterion("upper(TASK_DATA) like", value.toUpperCase(), "taskData");
            return (Criteria) this;
        }

        public Criteria andStatusProcLikeInsensitive(String value) {
            addCriterion("upper(STATUS_PROC) like", value.toUpperCase(), "statusProc");
            return (Criteria) this;
        }

        public Criteria andStatusProcDescLikeInsensitive(String value) {
            addCriterion("upper(STATUS_PROC_DESC) like", value.toUpperCase(), "statusProcDesc");
            return (Criteria) this;
        }

        public Criteria andFailedReasonCodeLikeInsensitive(String value) {
            addCriterion("upper(FAILED_REASON_CODE) like", value.toUpperCase(), "failedReasonCode");
            return (Criteria) this;
        }

        public Criteria andFailedReasonDescLikeInsensitive(String value) {
            addCriterion("upper(FAILED_REASON_DESC) like", value.toUpperCase(), "failedReasonDesc");
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