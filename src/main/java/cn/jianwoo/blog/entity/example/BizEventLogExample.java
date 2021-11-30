package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BizEventLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public BizEventLogExample() {
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

        public Criteria andLoginIdIsNull() {
            addCriterion("LOGIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andLoginIdIsNotNull() {
            addCriterion("LOGIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLoginIdEqualTo(String value) {
            addCriterion("LOGIN_ID =", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdNotEqualTo(String value) {
            addCriterion("LOGIN_ID <>", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdGreaterThan(String value) {
            addCriterion("LOGIN_ID >", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_ID >=", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdLessThan(String value) {
            addCriterion("LOGIN_ID <", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_ID <=", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdLike(String value) {
            addCriterion("LOGIN_ID like", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdNotLike(String value) {
            addCriterion("LOGIN_ID not like", value, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdIn(List<String> values) {
            addCriterion("LOGIN_ID in", values, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdNotIn(List<String> values) {
            addCriterion("LOGIN_ID not in", values, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdBetween(String value1, String value2) {
            addCriterion("LOGIN_ID between", value1, value2, "loginId");
            return (Criteria) this;
        }

        public Criteria andLoginIdNotBetween(String value1, String value2) {
            addCriterion("LOGIN_ID not between", value1, value2, "loginId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andEventTypeIsNull() {
            addCriterion("EVENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEventTypeIsNotNull() {
            addCriterion("EVENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEventTypeEqualTo(String value) {
            addCriterion("EVENT_TYPE =", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotEqualTo(String value) {
            addCriterion("EVENT_TYPE <>", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeGreaterThan(String value) {
            addCriterion("EVENT_TYPE >", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_TYPE >=", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeLessThan(String value) {
            addCriterion("EVENT_TYPE <", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeLessThanOrEqualTo(String value) {
            addCriterion("EVENT_TYPE <=", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeLike(String value) {
            addCriterion("EVENT_TYPE like", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotLike(String value) {
            addCriterion("EVENT_TYPE not like", value, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeIn(List<String> values) {
            addCriterion("EVENT_TYPE in", values, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotIn(List<String> values) {
            addCriterion("EVENT_TYPE not in", values, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeBetween(String value1, String value2) {
            addCriterion("EVENT_TYPE between", value1, value2, "eventType");
            return (Criteria) this;
        }

        public Criteria andEventTypeNotBetween(String value1, String value2) {
            addCriterion("EVENT_TYPE not between", value1, value2, "eventType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNull() {
            addCriterion("OPT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOptTypeIsNotNull() {
            addCriterion("OPT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOptTypeEqualTo(String value) {
            addCriterion("OPT_TYPE =", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotEqualTo(String value) {
            addCriterion("OPT_TYPE <>", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThan(String value) {
            addCriterion("OPT_TYPE >", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OPT_TYPE >=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThan(String value) {
            addCriterion("OPT_TYPE <", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLessThanOrEqualTo(String value) {
            addCriterion("OPT_TYPE <=", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLike(String value) {
            addCriterion("OPT_TYPE like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotLike(String value) {
            addCriterion("OPT_TYPE not like", value, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeIn(List<String> values) {
            addCriterion("OPT_TYPE in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotIn(List<String> values) {
            addCriterion("OPT_TYPE not in", values, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeBetween(String value1, String value2) {
            addCriterion("OPT_TYPE between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptTypeNotBetween(String value1, String value2) {
            addCriterion("OPT_TYPE not between", value1, value2, "optType");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidIsNull() {
            addCriterion("OPT_ENTITY_OID is null");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidIsNotNull() {
            addCriterion("OPT_ENTITY_OID is not null");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidEqualTo(String value) {
            addCriterion("OPT_ENTITY_OID =", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidNotEqualTo(String value) {
            addCriterion("OPT_ENTITY_OID <>", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidGreaterThan(String value) {
            addCriterion("OPT_ENTITY_OID >", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidGreaterThanOrEqualTo(String value) {
            addCriterion("OPT_ENTITY_OID >=", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidLessThan(String value) {
            addCriterion("OPT_ENTITY_OID <", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidLessThanOrEqualTo(String value) {
            addCriterion("OPT_ENTITY_OID <=", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidLike(String value) {
            addCriterion("OPT_ENTITY_OID like", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidNotLike(String value) {
            addCriterion("OPT_ENTITY_OID not like", value, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidIn(List<String> values) {
            addCriterion("OPT_ENTITY_OID in", values, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidNotIn(List<String> values) {
            addCriterion("OPT_ENTITY_OID not in", values, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidBetween(String value1, String value2) {
            addCriterion("OPT_ENTITY_OID between", value1, value2, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidNotBetween(String value1, String value2) {
            addCriterion("OPT_ENTITY_OID not between", value1, value2, "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescIsNull() {
            addCriterion("OPT_ENTITY_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescIsNotNull() {
            addCriterion("OPT_ENTITY_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescEqualTo(String value) {
            addCriterion("OPT_ENTITY_DESC =", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescNotEqualTo(String value) {
            addCriterion("OPT_ENTITY_DESC <>", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescGreaterThan(String value) {
            addCriterion("OPT_ENTITY_DESC >", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescGreaterThanOrEqualTo(String value) {
            addCriterion("OPT_ENTITY_DESC >=", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescLessThan(String value) {
            addCriterion("OPT_ENTITY_DESC <", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescLessThanOrEqualTo(String value) {
            addCriterion("OPT_ENTITY_DESC <=", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescLike(String value) {
            addCriterion("OPT_ENTITY_DESC like", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescNotLike(String value) {
            addCriterion("OPT_ENTITY_DESC not like", value, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescIn(List<String> values) {
            addCriterion("OPT_ENTITY_DESC in", values, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescNotIn(List<String> values) {
            addCriterion("OPT_ENTITY_DESC not in", values, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescBetween(String value1, String value2) {
            addCriterion("OPT_ENTITY_DESC between", value1, value2, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescNotBetween(String value1, String value2) {
            addCriterion("OPT_ENTITY_DESC not between", value1, value2, "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNull() {
            addCriterion("TRIGGER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNotNull() {
            addCriterion("TRIGGER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeEqualTo(Date value) {
            addCriterion("TRIGGER_TIME =", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotEqualTo(Date value) {
            addCriterion("TRIGGER_TIME <>", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThan(Date value) {
            addCriterion("TRIGGER_TIME >", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TRIGGER_TIME >=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThan(Date value) {
            addCriterion("TRIGGER_TIME <", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThanOrEqualTo(Date value) {
            addCriterion("TRIGGER_TIME <=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIn(List<Date> values) {
            addCriterion("TRIGGER_TIME in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotIn(List<Date> values) {
            addCriterion("TRIGGER_TIME not in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeBetween(Date value1, Date value2) {
            addCriterion("TRIGGER_TIME between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotBetween(Date value1, Date value2) {
            addCriterion("TRIGGER_TIME not between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerIpIsNull() {
            addCriterion("TRIGGER_IP is null");
            return (Criteria) this;
        }

        public Criteria andTriggerIpIsNotNull() {
            addCriterion("TRIGGER_IP is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerIpEqualTo(String value) {
            addCriterion("TRIGGER_IP =", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpNotEqualTo(String value) {
            addCriterion("TRIGGER_IP <>", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpGreaterThan(String value) {
            addCriterion("TRIGGER_IP >", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpGreaterThanOrEqualTo(String value) {
            addCriterion("TRIGGER_IP >=", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpLessThan(String value) {
            addCriterion("TRIGGER_IP <", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpLessThanOrEqualTo(String value) {
            addCriterion("TRIGGER_IP <=", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpLike(String value) {
            addCriterion("TRIGGER_IP like", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpNotLike(String value) {
            addCriterion("TRIGGER_IP not like", value, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpIn(List<String> values) {
            addCriterion("TRIGGER_IP in", values, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpNotIn(List<String> values) {
            addCriterion("TRIGGER_IP not in", values, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpBetween(String value1, String value2) {
            addCriterion("TRIGGER_IP between", value1, value2, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andTriggerIpNotBetween(String value1, String value2) {
            addCriterion("TRIGGER_IP not between", value1, value2, "triggerIp");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNull() {
            addCriterion("PROCESS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIsNotNull() {
            addCriterion("PROCESS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStatusEqualTo(String value) {
            addCriterion("PROCESS_STATUS =", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotEqualTo(String value) {
            addCriterion("PROCESS_STATUS <>", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThan(String value) {
            addCriterion("PROCESS_STATUS >", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_STATUS >=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThan(String value) {
            addCriterion("PROCESS_STATUS <", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_STATUS <=", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLike(String value) {
            addCriterion("PROCESS_STATUS like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotLike(String value) {
            addCriterion("PROCESS_STATUS not like", value, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusIn(List<String> values) {
            addCriterion("PROCESS_STATUS in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotIn(List<String> values) {
            addCriterion("PROCESS_STATUS not in", values, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusBetween(String value1, String value2) {
            addCriterion("PROCESS_STATUS between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andProcessStatusNotBetween(String value1, String value2) {
            addCriterion("PROCESS_STATUS not between", value1, value2, "processStatus");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIsNull() {
            addCriterion("FAILED_REASON is null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIsNotNull() {
            addCriterion("FAILED_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andFailedReasonEqualTo(String value) {
            addCriterion("FAILED_REASON =", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotEqualTo(String value) {
            addCriterion("FAILED_REASON <>", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonGreaterThan(String value) {
            addCriterion("FAILED_REASON >", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonGreaterThanOrEqualTo(String value) {
            addCriterion("FAILED_REASON >=", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLessThan(String value) {
            addCriterion("FAILED_REASON <", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLessThanOrEqualTo(String value) {
            addCriterion("FAILED_REASON <=", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLike(String value) {
            addCriterion("FAILED_REASON like", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotLike(String value) {
            addCriterion("FAILED_REASON not like", value, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonIn(List<String> values) {
            addCriterion("FAILED_REASON in", values, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotIn(List<String> values) {
            addCriterion("FAILED_REASON not in", values, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonBetween(String value1, String value2) {
            addCriterion("FAILED_REASON between", value1, value2, "failedReason");
            return (Criteria) this;
        }

        public Criteria andFailedReasonNotBetween(String value1, String value2) {
            addCriterion("FAILED_REASON not between", value1, value2, "failedReason");
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

        public Criteria andLoginIdLikeInsensitive(String value) {
            addCriterion("upper(LOGIN_ID) like", value.toUpperCase(), "loginId");
            return (Criteria) this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(USER_NAME) like", value.toUpperCase(), "userName");
            return (Criteria) this;
        }

        public Criteria andEventTypeLikeInsensitive(String value) {
            addCriterion("upper(EVENT_TYPE) like", value.toUpperCase(), "eventType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLikeInsensitive(String value) {
            addCriterion("upper(OPT_TYPE) like", value.toUpperCase(), "optType");
            return (Criteria) this;
        }

        public Criteria andOptEntityOidLikeInsensitive(String value) {
            addCriterion("upper(OPT_ENTITY_OID) like", value.toUpperCase(), "optEntityOid");
            return (Criteria) this;
        }

        public Criteria andOptEntityDescLikeInsensitive(String value) {
            addCriterion("upper(OPT_ENTITY_DESC) like", value.toUpperCase(), "optEntityDesc");
            return (Criteria) this;
        }

        public Criteria andTriggerIpLikeInsensitive(String value) {
            addCriterion("upper(TRIGGER_IP) like", value.toUpperCase(), "triggerIp");
            return (Criteria) this;
        }

        public Criteria andProcessStatusLikeInsensitive(String value) {
            addCriterion("upper(PROCESS_STATUS) like", value.toUpperCase(), "processStatus");
            return (Criteria) this;
        }

        public Criteria andFailedReasonLikeInsensitive(String value) {
            addCriterion("upper(FAILED_REASON) like", value.toUpperCase(), "failedReason");
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