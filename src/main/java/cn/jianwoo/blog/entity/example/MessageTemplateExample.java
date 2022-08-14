package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public MessageTemplateExample() {
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

        public Criteria andMsgDescIsNull() {
            addCriterion("MSG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andMsgDescIsNotNull() {
            addCriterion("MSG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andMsgDescEqualTo(String value) {
            addCriterion("MSG_DESC =", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescNotEqualTo(String value) {
            addCriterion("MSG_DESC <>", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescGreaterThan(String value) {
            addCriterion("MSG_DESC >", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_DESC >=", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescLessThan(String value) {
            addCriterion("MSG_DESC <", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescLessThanOrEqualTo(String value) {
            addCriterion("MSG_DESC <=", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescLike(String value) {
            addCriterion("MSG_DESC like", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescNotLike(String value) {
            addCriterion("MSG_DESC not like", value, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescIn(List<String> values) {
            addCriterion("MSG_DESC in", values, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescNotIn(List<String> values) {
            addCriterion("MSG_DESC not in", values, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescBetween(String value1, String value2) {
            addCriterion("MSG_DESC between", value1, value2, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgDescNotBetween(String value1, String value2) {
            addCriterion("MSG_DESC not between", value1, value2, "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateIsNull() {
            addCriterion("MSG_TITLE_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateIsNotNull() {
            addCriterion("MSG_TITLE_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateEqualTo(String value) {
            addCriterion("MSG_TITLE_TEMPLATE =", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateNotEqualTo(String value) {
            addCriterion("MSG_TITLE_TEMPLATE <>", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateGreaterThan(String value) {
            addCriterion("MSG_TITLE_TEMPLATE >", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TITLE_TEMPLATE >=", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateLessThan(String value) {
            addCriterion("MSG_TITLE_TEMPLATE <", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateLessThanOrEqualTo(String value) {
            addCriterion("MSG_TITLE_TEMPLATE <=", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateLike(String value) {
            addCriterion("MSG_TITLE_TEMPLATE like", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateNotLike(String value) {
            addCriterion("MSG_TITLE_TEMPLATE not like", value, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateIn(List<String> values) {
            addCriterion("MSG_TITLE_TEMPLATE in", values, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateNotIn(List<String> values) {
            addCriterion("MSG_TITLE_TEMPLATE not in", values, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateBetween(String value1, String value2) {
            addCriterion("MSG_TITLE_TEMPLATE between", value1, value2, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateNotBetween(String value1, String value2) {
            addCriterion("MSG_TITLE_TEMPLATE not between", value1, value2, "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateIsNull() {
            addCriterion("MSG_LINK_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateIsNotNull() {
            addCriterion("MSG_LINK_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateEqualTo(String value) {
            addCriterion("MSG_LINK_TEMPLATE =", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateNotEqualTo(String value) {
            addCriterion("MSG_LINK_TEMPLATE <>", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateGreaterThan(String value) {
            addCriterion("MSG_LINK_TEMPLATE >", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_LINK_TEMPLATE >=", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateLessThan(String value) {
            addCriterion("MSG_LINK_TEMPLATE <", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateLessThanOrEqualTo(String value) {
            addCriterion("MSG_LINK_TEMPLATE <=", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateLike(String value) {
            addCriterion("MSG_LINK_TEMPLATE like", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateNotLike(String value) {
            addCriterion("MSG_LINK_TEMPLATE not like", value, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateIn(List<String> values) {
            addCriterion("MSG_LINK_TEMPLATE in", values, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateNotIn(List<String> values) {
            addCriterion("MSG_LINK_TEMPLATE not in", values, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateBetween(String value1, String value2) {
            addCriterion("MSG_LINK_TEMPLATE between", value1, value2, "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateNotBetween(String value1, String value2) {
            addCriterion("MSG_LINK_TEMPLATE not between", value1, value2, "msgLinkTemplate");
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

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
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

        public Criteria andMsgDescLikeInsensitive(String value) {
            addCriterion("upper(MSG_DESC) like", value.toUpperCase(), "msgDesc");
            return (Criteria) this;
        }

        public Criteria andMsgTitleTemplateLikeInsensitive(String value) {
            addCriterion("upper(MSG_TITLE_TEMPLATE) like", value.toUpperCase(), "msgTitleTemplate");
            return (Criteria) this;
        }

        public Criteria andMsgLinkTemplateLikeInsensitive(String value) {
            addCriterion("upper(MSG_LINK_TEMPLATE) like", value.toUpperCase(), "msgLinkTemplate");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeLikeInsensitive(String value) {
            addCriterion("upper(EMAIL_TPL_CODE) like", value.toUpperCase(), "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andCreatedByLikeInsensitive(String value) {
            addCriterion("upper(CREATED_BY) like", value.toUpperCase(), "createdBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLikeInsensitive(String value) {
            addCriterion("upper(UPDATE_BY) like", value.toUpperCase(), "updateBy");
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