package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageBoardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public MessageBoardExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
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

        public Criteria andUserNickIsNull() {
            addCriterion("USER_NICK is null");
            return (Criteria) this;
        }

        public Criteria andUserNickIsNotNull() {
            addCriterion("USER_NICK is not null");
            return (Criteria) this;
        }

        public Criteria andUserNickEqualTo(String value) {
            addCriterion("USER_NICK =", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotEqualTo(String value) {
            addCriterion("USER_NICK <>", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickGreaterThan(String value) {
            addCriterion("USER_NICK >", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NICK >=", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickLessThan(String value) {
            addCriterion("USER_NICK <", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickLessThanOrEqualTo(String value) {
            addCriterion("USER_NICK <=", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickLike(String value) {
            addCriterion("USER_NICK like", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotLike(String value) {
            addCriterion("USER_NICK not like", value, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickIn(List<String> values) {
            addCriterion("USER_NICK in", values, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotIn(List<String> values) {
            addCriterion("USER_NICK not in", values, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickBetween(String value1, String value2) {
            addCriterion("USER_NICK between", value1, value2, "userNick");
            return (Criteria) this;
        }

        public Criteria andUserNickNotBetween(String value1, String value2) {
            addCriterion("USER_NICK not between", value1, value2, "userNick");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNull() {
            addCriterion("CLIENT_IP is null");
            return (Criteria) this;
        }

        public Criteria andClientIpIsNotNull() {
            addCriterion("CLIENT_IP is not null");
            return (Criteria) this;
        }

        public Criteria andClientIpEqualTo(String value) {
            addCriterion("CLIENT_IP =", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotEqualTo(String value) {
            addCriterion("CLIENT_IP <>", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThan(String value) {
            addCriterion("CLIENT_IP >", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_IP >=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThan(String value) {
            addCriterion("CLIENT_IP <", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_IP <=", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpLike(String value) {
            addCriterion("CLIENT_IP like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotLike(String value) {
            addCriterion("CLIENT_IP not like", value, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpIn(List<String> values) {
            addCriterion("CLIENT_IP in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotIn(List<String> values) {
            addCriterion("CLIENT_IP not in", values, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpBetween(String value1, String value2) {
            addCriterion("CLIENT_IP between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andClientIpNotBetween(String value1, String value2) {
            addCriterion("CLIENT_IP not between", value1, value2, "clientIp");
            return (Criteria) this;
        }

        public Criteria andUserRegionIsNull() {
            addCriterion("USER_REGION is null");
            return (Criteria) this;
        }

        public Criteria andUserRegionIsNotNull() {
            addCriterion("USER_REGION is not null");
            return (Criteria) this;
        }

        public Criteria andUserRegionEqualTo(String value) {
            addCriterion("USER_REGION =", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionNotEqualTo(String value) {
            addCriterion("USER_REGION <>", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionGreaterThan(String value) {
            addCriterion("USER_REGION >", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionGreaterThanOrEqualTo(String value) {
            addCriterion("USER_REGION >=", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionLessThan(String value) {
            addCriterion("USER_REGION <", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionLessThanOrEqualTo(String value) {
            addCriterion("USER_REGION <=", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionLike(String value) {
            addCriterion("USER_REGION like", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionNotLike(String value) {
            addCriterion("USER_REGION not like", value, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionIn(List<String> values) {
            addCriterion("USER_REGION in", values, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionNotIn(List<String> values) {
            addCriterion("USER_REGION not in", values, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionBetween(String value1, String value2) {
            addCriterion("USER_REGION between", value1, value2, "userRegion");
            return (Criteria) this;
        }

        public Criteria andUserRegionNotBetween(String value1, String value2) {
            addCriterion("USER_REGION not between", value1, value2, "userRegion");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("PUSH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("PUSH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("PUSH_TIME =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("PUSH_TIME <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("PUSH_TIME >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PUSH_TIME >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("PUSH_TIME <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("PUSH_TIME <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("PUSH_TIME in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("PUSH_TIME not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("PUSH_TIME between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("PUSH_TIME not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andParentOidIsNull() {
            addCriterion("PARENT_OID is null");
            return (Criteria) this;
        }

        public Criteria andParentOidIsNotNull() {
            addCriterion("PARENT_OID is not null");
            return (Criteria) this;
        }

        public Criteria andParentOidEqualTo(Long value) {
            addCriterion("PARENT_OID =", value, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidNotEqualTo(Long value) {
            addCriterion("PARENT_OID <>", value, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidGreaterThan(Long value) {
            addCriterion("PARENT_OID >", value, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_OID >=", value, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidLessThan(Long value) {
            addCriterion("PARENT_OID <", value, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_OID <=", value, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidIn(List<Long> values) {
            addCriterion("PARENT_OID in", values, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidNotIn(List<Long> values) {
            addCriterion("PARENT_OID not in", values, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidBetween(Long value1, Long value2) {
            addCriterion("PARENT_OID between", value1, value2, "parentOid");
            return (Criteria) this;
        }

        public Criteria andParentOidNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_OID not between", value1, value2, "parentOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidIsNull() {
            addCriterion("REPLY_ROOT_OID is null");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidIsNotNull() {
            addCriterion("REPLY_ROOT_OID is not null");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidEqualTo(Long value) {
            addCriterion("REPLY_ROOT_OID =", value, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidNotEqualTo(Long value) {
            addCriterion("REPLY_ROOT_OID <>", value, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidGreaterThan(Long value) {
            addCriterion("REPLY_ROOT_OID >", value, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidGreaterThanOrEqualTo(Long value) {
            addCriterion("REPLY_ROOT_OID >=", value, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidLessThan(Long value) {
            addCriterion("REPLY_ROOT_OID <", value, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidLessThanOrEqualTo(Long value) {
            addCriterion("REPLY_ROOT_OID <=", value, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidIn(List<Long> values) {
            addCriterion("REPLY_ROOT_OID in", values, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidNotIn(List<Long> values) {
            addCriterion("REPLY_ROOT_OID not in", values, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidBetween(Long value1, Long value2) {
            addCriterion("REPLY_ROOT_OID between", value1, value2, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyRootOidNotBetween(Long value1, Long value2) {
            addCriterion("REPLY_ROOT_OID not between", value1, value2, "replyRootOid");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdIsNull() {
            addCriterion("REPLY_TO_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdIsNotNull() {
            addCriterion("REPLY_TO_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdEqualTo(String value) {
            addCriterion("REPLY_TO_USER_ID =", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdNotEqualTo(String value) {
            addCriterion("REPLY_TO_USER_ID <>", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdGreaterThan(String value) {
            addCriterion("REPLY_TO_USER_ID >", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_TO_USER_ID >=", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdLessThan(String value) {
            addCriterion("REPLY_TO_USER_ID <", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdLessThanOrEqualTo(String value) {
            addCriterion("REPLY_TO_USER_ID <=", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdLike(String value) {
            addCriterion("REPLY_TO_USER_ID like", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdNotLike(String value) {
            addCriterion("REPLY_TO_USER_ID not like", value, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdIn(List<String> values) {
            addCriterion("REPLY_TO_USER_ID in", values, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdNotIn(List<String> values) {
            addCriterion("REPLY_TO_USER_ID not in", values, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdBetween(String value1, String value2) {
            addCriterion("REPLY_TO_USER_ID between", value1, value2, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdNotBetween(String value1, String value2) {
            addCriterion("REPLY_TO_USER_ID not between", value1, value2, "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameIsNull() {
            addCriterion("REPLY_TO_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameIsNotNull() {
            addCriterion("REPLY_TO_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NAME =", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameNotEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NAME <>", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameGreaterThan(String value) {
            addCriterion("REPLY_TO_USER_NAME >", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NAME >=", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameLessThan(String value) {
            addCriterion("REPLY_TO_USER_NAME <", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameLessThanOrEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NAME <=", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameLike(String value) {
            addCriterion("REPLY_TO_USER_NAME like", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameNotLike(String value) {
            addCriterion("REPLY_TO_USER_NAME not like", value, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameIn(List<String> values) {
            addCriterion("REPLY_TO_USER_NAME in", values, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameNotIn(List<String> values) {
            addCriterion("REPLY_TO_USER_NAME not in", values, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameBetween(String value1, String value2) {
            addCriterion("REPLY_TO_USER_NAME between", value1, value2, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameNotBetween(String value1, String value2) {
            addCriterion("REPLY_TO_USER_NAME not between", value1, value2, "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickIsNull() {
            addCriterion("REPLY_TO_USER_NICK is null");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickIsNotNull() {
            addCriterion("REPLY_TO_USER_NICK is not null");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NICK =", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickNotEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NICK <>", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickGreaterThan(String value) {
            addCriterion("REPLY_TO_USER_NICK >", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NICK >=", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickLessThan(String value) {
            addCriterion("REPLY_TO_USER_NICK <", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickLessThanOrEqualTo(String value) {
            addCriterion("REPLY_TO_USER_NICK <=", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickLike(String value) {
            addCriterion("REPLY_TO_USER_NICK like", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickNotLike(String value) {
            addCriterion("REPLY_TO_USER_NICK not like", value, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickIn(List<String> values) {
            addCriterion("REPLY_TO_USER_NICK in", values, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickNotIn(List<String> values) {
            addCriterion("REPLY_TO_USER_NICK not in", values, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickBetween(String value1, String value2) {
            addCriterion("REPLY_TO_USER_NICK between", value1, value2, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickNotBetween(String value1, String value2) {
            addCriterion("REPLY_TO_USER_NICK not between", value1, value2, "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andPraiseCountIsNull() {
            addCriterion("PRAISE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andPraiseCountIsNotNull() {
            addCriterion("PRAISE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPraiseCountEqualTo(Long value) {
            addCriterion("PRAISE_COUNT =", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountNotEqualTo(Long value) {
            addCriterion("PRAISE_COUNT <>", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountGreaterThan(Long value) {
            addCriterion("PRAISE_COUNT >", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountGreaterThanOrEqualTo(Long value) {
            addCriterion("PRAISE_COUNT >=", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountLessThan(Long value) {
            addCriterion("PRAISE_COUNT <", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountLessThanOrEqualTo(Long value) {
            addCriterion("PRAISE_COUNT <=", value, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountIn(List<Long> values) {
            addCriterion("PRAISE_COUNT in", values, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountNotIn(List<Long> values) {
            addCriterion("PRAISE_COUNT not in", values, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountBetween(Long value1, Long value2) {
            addCriterion("PRAISE_COUNT between", value1, value2, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andPraiseCountNotBetween(Long value1, Long value2) {
            addCriterion("PRAISE_COUNT not between", value1, value2, "praiseCount");
            return (Criteria) this;
        }

        public Criteria andContactQqIsNull() {
            addCriterion("CONTACT_QQ is null");
            return (Criteria) this;
        }

        public Criteria andContactQqIsNotNull() {
            addCriterion("CONTACT_QQ is not null");
            return (Criteria) this;
        }

        public Criteria andContactQqEqualTo(String value) {
            addCriterion("CONTACT_QQ =", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqNotEqualTo(String value) {
            addCriterion("CONTACT_QQ <>", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqGreaterThan(String value) {
            addCriterion("CONTACT_QQ >", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_QQ >=", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqLessThan(String value) {
            addCriterion("CONTACT_QQ <", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_QQ <=", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqLike(String value) {
            addCriterion("CONTACT_QQ like", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqNotLike(String value) {
            addCriterion("CONTACT_QQ not like", value, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqIn(List<String> values) {
            addCriterion("CONTACT_QQ in", values, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqNotIn(List<String> values) {
            addCriterion("CONTACT_QQ not in", values, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqBetween(String value1, String value2) {
            addCriterion("CONTACT_QQ between", value1, value2, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactQqNotBetween(String value1, String value2) {
            addCriterion("CONTACT_QQ not between", value1, value2, "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactWechatIsNull() {
            addCriterion("CONTACT_WECHAT is null");
            return (Criteria) this;
        }

        public Criteria andContactWechatIsNotNull() {
            addCriterion("CONTACT_WECHAT is not null");
            return (Criteria) this;
        }

        public Criteria andContactWechatEqualTo(String value) {
            addCriterion("CONTACT_WECHAT =", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatNotEqualTo(String value) {
            addCriterion("CONTACT_WECHAT <>", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatGreaterThan(String value) {
            addCriterion("CONTACT_WECHAT >", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_WECHAT >=", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatLessThan(String value) {
            addCriterion("CONTACT_WECHAT <", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_WECHAT <=", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatLike(String value) {
            addCriterion("CONTACT_WECHAT like", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatNotLike(String value) {
            addCriterion("CONTACT_WECHAT not like", value, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatIn(List<String> values) {
            addCriterion("CONTACT_WECHAT in", values, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatNotIn(List<String> values) {
            addCriterion("CONTACT_WECHAT not in", values, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatBetween(String value1, String value2) {
            addCriterion("CONTACT_WECHAT between", value1, value2, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWechatNotBetween(String value1, String value2) {
            addCriterion("CONTACT_WECHAT not between", value1, value2, "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWeiboIsNull() {
            addCriterion("CONTACT_WEIBO is null");
            return (Criteria) this;
        }

        public Criteria andContactWeiboIsNotNull() {
            addCriterion("CONTACT_WEIBO is not null");
            return (Criteria) this;
        }

        public Criteria andContactWeiboEqualTo(String value) {
            addCriterion("CONTACT_WEIBO =", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboNotEqualTo(String value) {
            addCriterion("CONTACT_WEIBO <>", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboGreaterThan(String value) {
            addCriterion("CONTACT_WEIBO >", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_WEIBO >=", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboLessThan(String value) {
            addCriterion("CONTACT_WEIBO <", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_WEIBO <=", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboLike(String value) {
            addCriterion("CONTACT_WEIBO like", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboNotLike(String value) {
            addCriterion("CONTACT_WEIBO not like", value, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboIn(List<String> values) {
            addCriterion("CONTACT_WEIBO in", values, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboNotIn(List<String> values) {
            addCriterion("CONTACT_WEIBO not in", values, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboBetween(String value1, String value2) {
            addCriterion("CONTACT_WEIBO between", value1, value2, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactWeiboNotBetween(String value1, String value2) {
            addCriterion("CONTACT_WEIBO not between", value1, value2, "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNull() {
            addCriterion("CONTACT_TEL is null");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNotNull() {
            addCriterion("CONTACT_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelEqualTo(String value) {
            addCriterion("CONTACT_TEL =", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotEqualTo(String value) {
            addCriterion("CONTACT_TEL <>", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThan(String value) {
            addCriterion("CONTACT_TEL >", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_TEL >=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThan(String value) {
            addCriterion("CONTACT_TEL <", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_TEL <=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLike(String value) {
            addCriterion("CONTACT_TEL like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotLike(String value) {
            addCriterion("CONTACT_TEL not like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelIn(List<String> values) {
            addCriterion("CONTACT_TEL in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotIn(List<String> values) {
            addCriterion("CONTACT_TEL not in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelBetween(String value1, String value2) {
            addCriterion("CONTACT_TEL between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotBetween(String value1, String value2) {
            addCriterion("CONTACT_TEL not between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNull() {
            addCriterion("CONTACT_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNotNull() {
            addCriterion("CONTACT_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andContactEmailEqualTo(String value) {
            addCriterion("CONTACT_EMAIL =", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotEqualTo(String value) {
            addCriterion("CONTACT_EMAIL <>", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThan(String value) {
            addCriterion("CONTACT_EMAIL >", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_EMAIL >=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThan(String value) {
            addCriterion("CONTACT_EMAIL <", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_EMAIL <=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLike(String value) {
            addCriterion("CONTACT_EMAIL like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotLike(String value) {
            addCriterion("CONTACT_EMAIL not like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailIn(List<String> values) {
            addCriterion("CONTACT_EMAIL in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotIn(List<String> values) {
            addCriterion("CONTACT_EMAIL not in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailBetween(String value1, String value2) {
            addCriterion("CONTACT_EMAIL between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotBetween(String value1, String value2) {
            addCriterion("CONTACT_EMAIL not between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcIsNull() {
            addCriterion("AVATAR_SRC is null");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcIsNotNull() {
            addCriterion("AVATAR_SRC is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcEqualTo(String value) {
            addCriterion("AVATAR_SRC =", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcNotEqualTo(String value) {
            addCriterion("AVATAR_SRC <>", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcGreaterThan(String value) {
            addCriterion("AVATAR_SRC >", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcGreaterThanOrEqualTo(String value) {
            addCriterion("AVATAR_SRC >=", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcLessThan(String value) {
            addCriterion("AVATAR_SRC <", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcLessThanOrEqualTo(String value) {
            addCriterion("AVATAR_SRC <=", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcLike(String value) {
            addCriterion("AVATAR_SRC like", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcNotLike(String value) {
            addCriterion("AVATAR_SRC not like", value, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcIn(List<String> values) {
            addCriterion("AVATAR_SRC in", values, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcNotIn(List<String> values) {
            addCriterion("AVATAR_SRC not in", values, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcBetween(String value1, String value2) {
            addCriterion("AVATAR_SRC between", value1, value2, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcNotBetween(String value1, String value2) {
            addCriterion("AVATAR_SRC not between", value1, value2, "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andReadStatusIsNull() {
            addCriterion("READ_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andReadStatusIsNotNull() {
            addCriterion("READ_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andReadStatusEqualTo(String value) {
            addCriterion("READ_STATUS =", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotEqualTo(String value) {
            addCriterion("READ_STATUS <>", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusGreaterThan(String value) {
            addCriterion("READ_STATUS >", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusGreaterThanOrEqualTo(String value) {
            addCriterion("READ_STATUS >=", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusLessThan(String value) {
            addCriterion("READ_STATUS <", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusLessThanOrEqualTo(String value) {
            addCriterion("READ_STATUS <=", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusLike(String value) {
            addCriterion("READ_STATUS like", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotLike(String value) {
            addCriterion("READ_STATUS not like", value, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusIn(List<String> values) {
            addCriterion("READ_STATUS in", values, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotIn(List<String> values) {
            addCriterion("READ_STATUS not in", values, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusBetween(String value1, String value2) {
            addCriterion("READ_STATUS between", value1, value2, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReadStatusNotBetween(String value1, String value2) {
            addCriterion("READ_STATUS not between", value1, value2, "readStatus");
            return (Criteria) this;
        }

        public Criteria andReplyCountIsNull() {
            addCriterion("REPLY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andReplyCountIsNotNull() {
            addCriterion("REPLY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andReplyCountEqualTo(Long value) {
            addCriterion("REPLY_COUNT =", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotEqualTo(Long value) {
            addCriterion("REPLY_COUNT <>", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountGreaterThan(Long value) {
            addCriterion("REPLY_COUNT >", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountGreaterThanOrEqualTo(Long value) {
            addCriterion("REPLY_COUNT >=", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountLessThan(Long value) {
            addCriterion("REPLY_COUNT <", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountLessThanOrEqualTo(Long value) {
            addCriterion("REPLY_COUNT <=", value, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountIn(List<Long> values) {
            addCriterion("REPLY_COUNT in", values, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotIn(List<Long> values) {
            addCriterion("REPLY_COUNT not in", values, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountBetween(Long value1, Long value2) {
            addCriterion("REPLY_COUNT between", value1, value2, "replyCount");
            return (Criteria) this;
        }

        public Criteria andReplyCountNotBetween(Long value1, Long value2) {
            addCriterion("REPLY_COUNT not between", value1, value2, "replyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountIsNull() {
            addCriterion("TOTAL_REPLY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountIsNotNull() {
            addCriterion("TOTAL_REPLY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountEqualTo(Long value) {
            addCriterion("TOTAL_REPLY_COUNT =", value, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountNotEqualTo(Long value) {
            addCriterion("TOTAL_REPLY_COUNT <>", value, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountGreaterThan(Long value) {
            addCriterion("TOTAL_REPLY_COUNT >", value, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_REPLY_COUNT >=", value, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountLessThan(Long value) {
            addCriterion("TOTAL_REPLY_COUNT <", value, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_REPLY_COUNT <=", value, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountIn(List<Long> values) {
            addCriterion("TOTAL_REPLY_COUNT in", values, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountNotIn(List<Long> values) {
            addCriterion("TOTAL_REPLY_COUNT not in", values, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountBetween(Long value1, Long value2) {
            addCriterion("TOTAL_REPLY_COUNT between", value1, value2, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andTotalReplyCountNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_REPLY_COUNT not between", value1, value2, "totalReplyCount");
            return (Criteria) this;
        }

        public Criteria andFlagAdminIsNull() {
            addCriterion("FLAG_ADMIN is null");
            return (Criteria) this;
        }

        public Criteria andFlagAdminIsNotNull() {
            addCriterion("FLAG_ADMIN is not null");
            return (Criteria) this;
        }

        public Criteria andFlagAdminEqualTo(Boolean value) {
            addCriterion("FLAG_ADMIN =", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminNotEqualTo(Boolean value) {
            addCriterion("FLAG_ADMIN <>", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminGreaterThan(Boolean value) {
            addCriterion("FLAG_ADMIN >", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_ADMIN >=", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminLessThan(Boolean value) {
            addCriterion("FLAG_ADMIN <", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminLessThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_ADMIN <=", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminLike(Boolean value) {
            addCriterion("FLAG_ADMIN like", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminNotLike(Boolean value) {
            addCriterion("FLAG_ADMIN not like", value, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminIn(List<Boolean> values) {
            addCriterion("FLAG_ADMIN in", values, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminNotIn(List<Boolean> values) {
            addCriterion("FLAG_ADMIN not in", values, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_ADMIN between", value1, value2, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andFlagAdminNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_ADMIN not between", value1, value2, "flagAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLike(Boolean value) {
            addCriterion("IS_DELETE like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotLike(Boolean value) {
            addCriterion("IS_DELETE not like", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_DELETE not between", value1, value2, "isDelete");
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

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(USER_ID) like", value.toUpperCase(), "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(USER_NAME) like", value.toUpperCase(), "userName");
            return (Criteria) this;
        }

        public Criteria andUserNickLikeInsensitive(String value) {
            addCriterion("upper(USER_NICK) like", value.toUpperCase(), "userNick");
            return (Criteria) this;
        }

        public Criteria andClientIpLikeInsensitive(String value) {
            addCriterion("upper(CLIENT_IP) like", value.toUpperCase(), "clientIp");
            return (Criteria) this;
        }

        public Criteria andUserRegionLikeInsensitive(String value) {
            addCriterion("upper(USER_REGION) like", value.toUpperCase(), "userRegion");
            return (Criteria) this;
        }

        public Criteria andReplyToUserIdLikeInsensitive(String value) {
            addCriterion("upper(REPLY_TO_USER_ID) like", value.toUpperCase(), "replyToUserId");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNameLikeInsensitive(String value) {
            addCriterion("upper(REPLY_TO_USER_NAME) like", value.toUpperCase(), "replyToUserName");
            return (Criteria) this;
        }

        public Criteria andReplyToUserNickLikeInsensitive(String value) {
            addCriterion("upper(REPLY_TO_USER_NICK) like", value.toUpperCase(), "replyToUserNick");
            return (Criteria) this;
        }

        public Criteria andContactQqLikeInsensitive(String value) {
            addCriterion("upper(CONTACT_QQ) like", value.toUpperCase(), "contactQq");
            return (Criteria) this;
        }

        public Criteria andContactWechatLikeInsensitive(String value) {
            addCriterion("upper(CONTACT_WECHAT) like", value.toUpperCase(), "contactWechat");
            return (Criteria) this;
        }

        public Criteria andContactWeiboLikeInsensitive(String value) {
            addCriterion("upper(CONTACT_WEIBO) like", value.toUpperCase(), "contactWeibo");
            return (Criteria) this;
        }

        public Criteria andContactTelLikeInsensitive(String value) {
            addCriterion("upper(CONTACT_TEL) like", value.toUpperCase(), "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactEmailLikeInsensitive(String value) {
            addCriterion("upper(CONTACT_EMAIL) like", value.toUpperCase(), "contactEmail");
            return (Criteria) this;
        }

        public Criteria andAvatarSrcLikeInsensitive(String value) {
            addCriterion("upper(AVATAR_SRC) like", value.toUpperCase(), "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andReadStatusLikeInsensitive(String value) {
            addCriterion("upper(READ_STATUS) like", value.toUpperCase(), "readStatus");
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