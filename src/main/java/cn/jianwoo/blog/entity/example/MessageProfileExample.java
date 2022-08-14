package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageProfileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public MessageProfileExample() {
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

        public Criteria andBusiSceneCodeIsNull() {
            addCriterion("BUSI_SCENE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeIsNotNull() {
            addCriterion("BUSI_SCENE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeEqualTo(String value) {
            addCriterion("BUSI_SCENE_CODE =", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeNotEqualTo(String value) {
            addCriterion("BUSI_SCENE_CODE <>", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeGreaterThan(String value) {
            addCriterion("BUSI_SCENE_CODE >", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSI_SCENE_CODE >=", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeLessThan(String value) {
            addCriterion("BUSI_SCENE_CODE <", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeLessThanOrEqualTo(String value) {
            addCriterion("BUSI_SCENE_CODE <=", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeLike(String value) {
            addCriterion("BUSI_SCENE_CODE like", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeNotLike(String value) {
            addCriterion("BUSI_SCENE_CODE not like", value, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeIn(List<String> values) {
            addCriterion("BUSI_SCENE_CODE in", values, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeNotIn(List<String> values) {
            addCriterion("BUSI_SCENE_CODE not in", values, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeBetween(String value1, String value2) {
            addCriterion("BUSI_SCENE_CODE between", value1, value2, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiSceneCodeNotBetween(String value1, String value2) {
            addCriterion("BUSI_SCENE_CODE not between", value1, value2, "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIsNull() {
            addCriterion("BUSI_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIsNotNull() {
            addCriterion("BUSI_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBusiTypeEqualTo(String value) {
            addCriterion("BUSI_TYPE =", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotEqualTo(String value) {
            addCriterion("BUSI_TYPE <>", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeGreaterThan(String value) {
            addCriterion("BUSI_TYPE >", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSI_TYPE >=", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLessThan(String value) {
            addCriterion("BUSI_TYPE <", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLessThanOrEqualTo(String value) {
            addCriterion("BUSI_TYPE <=", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLike(String value) {
            addCriterion("BUSI_TYPE like", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotLike(String value) {
            addCriterion("BUSI_TYPE not like", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIn(List<String> values) {
            addCriterion("BUSI_TYPE in", values, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotIn(List<String> values) {
            addCriterion("BUSI_TYPE not in", values, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeBetween(String value1, String value2) {
            addCriterion("BUSI_TYPE between", value1, value2, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotBetween(String value1, String value2) {
            addCriterion("BUSI_TYPE not between", value1, value2, "busiType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNull() {
            addCriterion("MSG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNotNull() {
            addCriterion("MSG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeEqualTo(String value) {
            addCriterion("MSG_TYPE =", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotEqualTo(String value) {
            addCriterion("MSG_TYPE <>", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThan(String value) {
            addCriterion("MSG_TYPE >", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TYPE >=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThan(String value) {
            addCriterion("MSG_TYPE <", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThanOrEqualTo(String value) {
            addCriterion("MSG_TYPE <=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLike(String value) {
            addCriterion("MSG_TYPE like", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotLike(String value) {
            addCriterion("MSG_TYPE not like", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIn(List<String> values) {
            addCriterion("MSG_TYPE in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotIn(List<String> values) {
            addCriterion("MSG_TYPE not in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeBetween(String value1, String value2) {
            addCriterion("MSG_TYPE between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotBetween(String value1, String value2) {
            addCriterion("MSG_TYPE not between", value1, value2, "msgType");
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

        public Criteria andReceiverTypeIsNull() {
            addCriterion("RECEIVER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeIsNotNull() {
            addCriterion("RECEIVER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeEqualTo(String value) {
            addCriterion("RECEIVER_TYPE =", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotEqualTo(String value) {
            addCriterion("RECEIVER_TYPE <>", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeGreaterThan(String value) {
            addCriterion("RECEIVER_TYPE >", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_TYPE >=", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLessThan(String value) {
            addCriterion("RECEIVER_TYPE <", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_TYPE <=", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLike(String value) {
            addCriterion("RECEIVER_TYPE like", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotLike(String value) {
            addCriterion("RECEIVER_TYPE not like", value, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeIn(List<String> values) {
            addCriterion("RECEIVER_TYPE in", values, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotIn(List<String> values) {
            addCriterion("RECEIVER_TYPE not in", values, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeBetween(String value1, String value2) {
            addCriterion("RECEIVER_TYPE between", value1, value2, "receiverType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_TYPE not between", value1, value2, "receiverType");
            return (Criteria) this;
        }

        public Criteria andMsgTitleIsNull() {
            addCriterion("MSG_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andMsgTitleIsNotNull() {
            addCriterion("MSG_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTitleEqualTo(String value) {
            addCriterion("MSG_TITLE =", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotEqualTo(String value) {
            addCriterion("MSG_TITLE <>", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleGreaterThan(String value) {
            addCriterion("MSG_TITLE >", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TITLE >=", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLessThan(String value) {
            addCriterion("MSG_TITLE <", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLessThanOrEqualTo(String value) {
            addCriterion("MSG_TITLE <=", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLike(String value) {
            addCriterion("MSG_TITLE like", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotLike(String value) {
            addCriterion("MSG_TITLE not like", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleIn(List<String> values) {
            addCriterion("MSG_TITLE in", values, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotIn(List<String> values) {
            addCriterion("MSG_TITLE not in", values, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleBetween(String value1, String value2) {
            addCriterion("MSG_TITLE between", value1, value2, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotBetween(String value1, String value2) {
            addCriterion("MSG_TITLE not between", value1, value2, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgLinkIsNull() {
            addCriterion("MSG_LINK is null");
            return (Criteria) this;
        }

        public Criteria andMsgLinkIsNotNull() {
            addCriterion("MSG_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andMsgLinkEqualTo(String value) {
            addCriterion("MSG_LINK =", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkNotEqualTo(String value) {
            addCriterion("MSG_LINK <>", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkGreaterThan(String value) {
            addCriterion("MSG_LINK >", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_LINK >=", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkLessThan(String value) {
            addCriterion("MSG_LINK <", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkLessThanOrEqualTo(String value) {
            addCriterion("MSG_LINK <=", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkLike(String value) {
            addCriterion("MSG_LINK like", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkNotLike(String value) {
            addCriterion("MSG_LINK not like", value, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkIn(List<String> values) {
            addCriterion("MSG_LINK in", values, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkNotIn(List<String> values) {
            addCriterion("MSG_LINK not in", values, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkBetween(String value1, String value2) {
            addCriterion("MSG_LINK between", value1, value2, "msgLink");
            return (Criteria) this;
        }

        public Criteria andMsgLinkNotBetween(String value1, String value2) {
            addCriterion("MSG_LINK not between", value1, value2, "msgLink");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNull() {
            addCriterion("BIZ_ID is null");
            return (Criteria) this;
        }

        public Criteria andBizIdIsNotNull() {
            addCriterion("BIZ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBizIdEqualTo(String value) {
            addCriterion("BIZ_ID =", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotEqualTo(String value) {
            addCriterion("BIZ_ID <>", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThan(String value) {
            addCriterion("BIZ_ID >", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdGreaterThanOrEqualTo(String value) {
            addCriterion("BIZ_ID >=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThan(String value) {
            addCriterion("BIZ_ID <", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLessThanOrEqualTo(String value) {
            addCriterion("BIZ_ID <=", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdLike(String value) {
            addCriterion("BIZ_ID like", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotLike(String value) {
            addCriterion("BIZ_ID not like", value, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdIn(List<String> values) {
            addCriterion("BIZ_ID in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotIn(List<String> values) {
            addCriterion("BIZ_ID not in", values, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdBetween(String value1, String value2) {
            addCriterion("BIZ_ID between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andBizIdNotBetween(String value1, String value2) {
            addCriterion("BIZ_ID not between", value1, value2, "bizId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNull() {
            addCriterion("RECEIVER_ID is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIsNotNull() {
            addCriterion("RECEIVER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverIdEqualTo(String value) {
            addCriterion("RECEIVER_ID =", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotEqualTo(String value) {
            addCriterion("RECEIVER_ID <>", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThan(String value) {
            addCriterion("RECEIVER_ID >", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_ID >=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThan(String value) {
            addCriterion("RECEIVER_ID <", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_ID <=", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLike(String value) {
            addCriterion("RECEIVER_ID like", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotLike(String value) {
            addCriterion("RECEIVER_ID not like", value, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdIn(List<String> values) {
            addCriterion("RECEIVER_ID in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotIn(List<String> values) {
            addCriterion("RECEIVER_ID not in", values, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdBetween(String value1, String value2) {
            addCriterion("RECEIVER_ID between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_ID not between", value1, value2, "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNull() {
            addCriterion("RECEIVER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIsNotNull() {
            addCriterion("RECEIVER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverNameEqualTo(String value) {
            addCriterion("RECEIVER_NAME =", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotEqualTo(String value) {
            addCriterion("RECEIVER_NAME <>", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThan(String value) {
            addCriterion("RECEIVER_NAME >", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_NAME >=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThan(String value) {
            addCriterion("RECEIVER_NAME <", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_NAME <=", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLike(String value) {
            addCriterion("RECEIVER_NAME like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotLike(String value) {
            addCriterion("RECEIVER_NAME not like", value, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameIn(List<String> values) {
            addCriterion("RECEIVER_NAME in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotIn(List<String> values) {
            addCriterion("RECEIVER_NAME not in", values, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameBetween(String value1, String value2) {
            addCriterion("RECEIVER_NAME between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverNameNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_NAME not between", value1, value2, "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailIsNull() {
            addCriterion("RECEIVER_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailIsNotNull() {
            addCriterion("RECEIVER_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailEqualTo(String value) {
            addCriterion("RECEIVER_EMAIL =", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailNotEqualTo(String value) {
            addCriterion("RECEIVER_EMAIL <>", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailGreaterThan(String value) {
            addCriterion("RECEIVER_EMAIL >", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_EMAIL >=", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailLessThan(String value) {
            addCriterion("RECEIVER_EMAIL <", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_EMAIL <=", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailLike(String value) {
            addCriterion("RECEIVER_EMAIL like", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailNotLike(String value) {
            addCriterion("RECEIVER_EMAIL not like", value, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailIn(List<String> values) {
            addCriterion("RECEIVER_EMAIL in", values, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailNotIn(List<String> values) {
            addCriterion("RECEIVER_EMAIL not in", values, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailBetween(String value1, String value2) {
            addCriterion("RECEIVER_EMAIL between", value1, value2, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_EMAIL not between", value1, value2, "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoIsNull() {
            addCriterion("RECEIVER_MOBILE_NO is null");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoIsNotNull() {
            addCriterion("RECEIVER_MOBILE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoEqualTo(String value) {
            addCriterion("RECEIVER_MOBILE_NO =", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoNotEqualTo(String value) {
            addCriterion("RECEIVER_MOBILE_NO <>", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoGreaterThan(String value) {
            addCriterion("RECEIVER_MOBILE_NO >", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_MOBILE_NO >=", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoLessThan(String value) {
            addCriterion("RECEIVER_MOBILE_NO <", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_MOBILE_NO <=", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoLike(String value) {
            addCriterion("RECEIVER_MOBILE_NO like", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoNotLike(String value) {
            addCriterion("RECEIVER_MOBILE_NO not like", value, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoIn(List<String> values) {
            addCriterion("RECEIVER_MOBILE_NO in", values, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoNotIn(List<String> values) {
            addCriterion("RECEIVER_MOBILE_NO not in", values, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoBetween(String value1, String value2) {
            addCriterion("RECEIVER_MOBILE_NO between", value1, value2, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_MOBILE_NO not between", value1, value2, "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyIsNull() {
            addCriterion("EMAIL_NOTIFY is null");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyIsNotNull() {
            addCriterion("EMAIL_NOTIFY is not null");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyEqualTo(Boolean value) {
            addCriterion("EMAIL_NOTIFY =", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyNotEqualTo(Boolean value) {
            addCriterion("EMAIL_NOTIFY <>", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyGreaterThan(Boolean value) {
            addCriterion("EMAIL_NOTIFY >", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyGreaterThanOrEqualTo(Boolean value) {
            addCriterion("EMAIL_NOTIFY >=", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyLessThan(Boolean value) {
            addCriterion("EMAIL_NOTIFY <", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyLessThanOrEqualTo(Boolean value) {
            addCriterion("EMAIL_NOTIFY <=", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyLike(Boolean value) {
            addCriterion("EMAIL_NOTIFY like", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyNotLike(Boolean value) {
            addCriterion("EMAIL_NOTIFY not like", value, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyIn(List<Boolean> values) {
            addCriterion("EMAIL_NOTIFY in", values, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyNotIn(List<Boolean> values) {
            addCriterion("EMAIL_NOTIFY not in", values, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyBetween(Boolean value1, Boolean value2) {
            addCriterion("EMAIL_NOTIFY between", value1, value2, "emailNotify");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyNotBetween(Boolean value1, Boolean value2) {
            addCriterion("EMAIL_NOTIFY not between", value1, value2, "emailNotify");
            return (Criteria) this;
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

        public Criteria andEmailSendResultIsNull() {
            addCriterion("EMAIL_SEND_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultIsNotNull() {
            addCriterion("EMAIL_SEND_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultEqualTo(String value) {
            addCriterion("EMAIL_SEND_RESULT =", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultNotEqualTo(String value) {
            addCriterion("EMAIL_SEND_RESULT <>", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultGreaterThan(String value) {
            addCriterion("EMAIL_SEND_RESULT >", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_SEND_RESULT >=", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultLessThan(String value) {
            addCriterion("EMAIL_SEND_RESULT <", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_SEND_RESULT <=", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultLike(String value) {
            addCriterion("EMAIL_SEND_RESULT like", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultNotLike(String value) {
            addCriterion("EMAIL_SEND_RESULT not like", value, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultIn(List<String> values) {
            addCriterion("EMAIL_SEND_RESULT in", values, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultNotIn(List<String> values) {
            addCriterion("EMAIL_SEND_RESULT not in", values, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultBetween(String value1, String value2) {
            addCriterion("EMAIL_SEND_RESULT between", value1, value2, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultNotBetween(String value1, String value2) {
            addCriterion("EMAIL_SEND_RESULT not between", value1, value2, "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andFlagReadIsNull() {
            addCriterion("FLAG_READ is null");
            return (Criteria) this;
        }

        public Criteria andFlagReadIsNotNull() {
            addCriterion("FLAG_READ is not null");
            return (Criteria) this;
        }

        public Criteria andFlagReadEqualTo(Boolean value) {
            addCriterion("FLAG_READ =", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadNotEqualTo(Boolean value) {
            addCriterion("FLAG_READ <>", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadGreaterThan(Boolean value) {
            addCriterion("FLAG_READ >", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_READ >=", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadLessThan(Boolean value) {
            addCriterion("FLAG_READ <", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadLessThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_READ <=", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadLike(Boolean value) {
            addCriterion("FLAG_READ like", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadNotLike(Boolean value) {
            addCriterion("FLAG_READ not like", value, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadIn(List<Boolean> values) {
            addCriterion("FLAG_READ in", values, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadNotIn(List<Boolean> values) {
            addCriterion("FLAG_READ not in", values, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_READ between", value1, value2, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagReadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_READ not between", value1, value2, "flagRead");
            return (Criteria) this;
        }

        public Criteria andFlagPopupIsNull() {
            addCriterion("FLAG_POPUP is null");
            return (Criteria) this;
        }

        public Criteria andFlagPopupIsNotNull() {
            addCriterion("FLAG_POPUP is not null");
            return (Criteria) this;
        }

        public Criteria andFlagPopupEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP =", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupNotEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP <>", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupGreaterThan(Boolean value) {
            addCriterion("FLAG_POPUP >", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP >=", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupLessThan(Boolean value) {
            addCriterion("FLAG_POPUP <", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupLessThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP <=", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupLike(Boolean value) {
            addCriterion("FLAG_POPUP like", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupNotLike(Boolean value) {
            addCriterion("FLAG_POPUP not like", value, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupIn(List<Boolean> values) {
            addCriterion("FLAG_POPUP in", values, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupNotIn(List<Boolean> values) {
            addCriterion("FLAG_POPUP not in", values, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_POPUP between", value1, value2, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_POPUP not between", value1, value2, "flagPopup");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainIsNull() {
            addCriterion("FLAG_POPUP_MAIN is null");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainIsNotNull() {
            addCriterion("FLAG_POPUP_MAIN is not null");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN =", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainNotEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN <>", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainGreaterThan(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN >", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN >=", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainLessThan(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN <", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainLessThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN <=", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainLike(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN like", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainNotLike(Boolean value) {
            addCriterion("FLAG_POPUP_MAIN not like", value, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainIn(List<Boolean> values) {
            addCriterion("FLAG_POPUP_MAIN in", values, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainNotIn(List<Boolean> values) {
            addCriterion("FLAG_POPUP_MAIN not in", values, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_POPUP_MAIN between", value1, value2, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andFlagPopupMainNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_POPUP_MAIN not between", value1, value2, "flagPopupMain");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNull() {
            addCriterion("READ_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReadTimeIsNotNull() {
            addCriterion("READ_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReadTimeEqualTo(Date value) {
            addCriterion("READ_TIME =", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotEqualTo(Date value) {
            addCriterion("READ_TIME <>", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThan(Date value) {
            addCriterion("READ_TIME >", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("READ_TIME >=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThan(Date value) {
            addCriterion("READ_TIME <", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeLessThanOrEqualTo(Date value) {
            addCriterion("READ_TIME <=", value, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeIn(List<Date> values) {
            addCriterion("READ_TIME in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotIn(List<Date> values) {
            addCriterion("READ_TIME not in", values, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeBetween(Date value1, Date value2) {
            addCriterion("READ_TIME between", value1, value2, "readTime");
            return (Criteria) this;
        }

        public Criteria andReadTimeNotBetween(Date value1, Date value2) {
            addCriterion("READ_TIME not between", value1, value2, "readTime");
            return (Criteria) this;
        }

        public Criteria andProcRsltIsNull() {
            addCriterion("PROC_RSLT is null");
            return (Criteria) this;
        }

        public Criteria andProcRsltIsNotNull() {
            addCriterion("PROC_RSLT is not null");
            return (Criteria) this;
        }

        public Criteria andProcRsltEqualTo(String value) {
            addCriterion("PROC_RSLT =", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltNotEqualTo(String value) {
            addCriterion("PROC_RSLT <>", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltGreaterThan(String value) {
            addCriterion("PROC_RSLT >", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_RSLT >=", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltLessThan(String value) {
            addCriterion("PROC_RSLT <", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltLessThanOrEqualTo(String value) {
            addCriterion("PROC_RSLT <=", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltLike(String value) {
            addCriterion("PROC_RSLT like", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltNotLike(String value) {
            addCriterion("PROC_RSLT not like", value, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltIn(List<String> values) {
            addCriterion("PROC_RSLT in", values, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltNotIn(List<String> values) {
            addCriterion("PROC_RSLT not in", values, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltBetween(String value1, String value2) {
            addCriterion("PROC_RSLT between", value1, value2, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcRsltNotBetween(String value1, String value2) {
            addCriterion("PROC_RSLT not between", value1, value2, "procRslt");
            return (Criteria) this;
        }

        public Criteria andProcTimeIsNull() {
            addCriterion("PROC_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProcTimeIsNotNull() {
            addCriterion("PROC_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProcTimeEqualTo(Date value) {
            addCriterion("PROC_TIME =", value, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeNotEqualTo(Date value) {
            addCriterion("PROC_TIME <>", value, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeGreaterThan(Date value) {
            addCriterion("PROC_TIME >", value, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PROC_TIME >=", value, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeLessThan(Date value) {
            addCriterion("PROC_TIME <", value, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeLessThanOrEqualTo(Date value) {
            addCriterion("PROC_TIME <=", value, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeIn(List<Date> values) {
            addCriterion("PROC_TIME in", values, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeNotIn(List<Date> values) {
            addCriterion("PROC_TIME not in", values, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeBetween(Date value1, Date value2) {
            addCriterion("PROC_TIME between", value1, value2, "procTime");
            return (Criteria) this;
        }

        public Criteria andProcTimeNotBetween(Date value1, Date value2) {
            addCriterion("PROC_TIME not between", value1, value2, "procTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
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

        public Criteria andBusiSceneCodeLikeInsensitive(String value) {
            addCriterion("upper(BUSI_SCENE_CODE) like", value.toUpperCase(), "busiSceneCode");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLikeInsensitive(String value) {
            addCriterion("upper(BUSI_TYPE) like", value.toUpperCase(), "busiType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLikeInsensitive(String value) {
            addCriterion("upper(MSG_TYPE) like", value.toUpperCase(), "msgType");
            return (Criteria) this;
        }

        public Criteria andOptTypeLikeInsensitive(String value) {
            addCriterion("upper(OPT_TYPE) like", value.toUpperCase(), "optType");
            return (Criteria) this;
        }

        public Criteria andReceiverTypeLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_TYPE) like", value.toUpperCase(), "receiverType");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLikeInsensitive(String value) {
            addCriterion("upper(MSG_TITLE) like", value.toUpperCase(), "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgLinkLikeInsensitive(String value) {
            addCriterion("upper(MSG_LINK) like", value.toUpperCase(), "msgLink");
            return (Criteria) this;
        }

        public Criteria andBizIdLikeInsensitive(String value) {
            addCriterion("upper(BIZ_ID) like", value.toUpperCase(), "bizId");
            return (Criteria) this;
        }

        public Criteria andReceiverIdLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_ID) like", value.toUpperCase(), "receiverId");
            return (Criteria) this;
        }

        public Criteria andReceiverNameLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_NAME) like", value.toUpperCase(), "receiverName");
            return (Criteria) this;
        }

        public Criteria andReceiverEmailLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_EMAIL) like", value.toUpperCase(), "receiverEmail");
            return (Criteria) this;
        }

        public Criteria andReceiverMobileNoLikeInsensitive(String value) {
            addCriterion("upper(RECEIVER_MOBILE_NO) like", value.toUpperCase(), "receiverMobileNo");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeLikeInsensitive(String value) {
            addCriterion("upper(EMAIL_TPL_CODE) like", value.toUpperCase(), "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andEmailSendResultLikeInsensitive(String value) {
            addCriterion("upper(EMAIL_SEND_RESULT) like", value.toUpperCase(), "emailSendResult");
            return (Criteria) this;
        }

        public Criteria andProcRsltLikeInsensitive(String value) {
            addCriterion("upper(PROC_RSLT) like", value.toUpperCase(), "procRslt");
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