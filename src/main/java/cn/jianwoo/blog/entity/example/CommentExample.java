package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public CommentExample() {
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

        public Criteria andArticleOidIsNull() {
            addCriterion("ARTICLE_OID is null");
            return (Criteria) this;
        }

        public Criteria andArticleOidIsNotNull() {
            addCriterion("ARTICLE_OID is not null");
            return (Criteria) this;
        }

        public Criteria andArticleOidEqualTo(Long value) {
            addCriterion("ARTICLE_OID =", value, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidNotEqualTo(Long value) {
            addCriterion("ARTICLE_OID <>", value, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidGreaterThan(Long value) {
            addCriterion("ARTICLE_OID >", value, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidGreaterThanOrEqualTo(Long value) {
            addCriterion("ARTICLE_OID >=", value, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidLessThan(Long value) {
            addCriterion("ARTICLE_OID <", value, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidLessThanOrEqualTo(Long value) {
            addCriterion("ARTICLE_OID <=", value, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidIn(List<Long> values) {
            addCriterion("ARTICLE_OID in", values, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidNotIn(List<Long> values) {
            addCriterion("ARTICLE_OID not in", values, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidBetween(Long value1, Long value2) {
            addCriterion("ARTICLE_OID between", value1, value2, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleOidNotBetween(Long value1, Long value2) {
            addCriterion("ARTICLE_OID not between", value1, value2, "articleOid");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNull() {
            addCriterion("ARTICLE_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIsNotNull() {
            addCriterion("ARTICLE_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andArticleTitleEqualTo(String value) {
            addCriterion("ARTICLE_TITLE =", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotEqualTo(String value) {
            addCriterion("ARTICLE_TITLE <>", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThan(String value) {
            addCriterion("ARTICLE_TITLE >", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLE_TITLE >=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThan(String value) {
            addCriterion("ARTICLE_TITLE <", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLessThanOrEqualTo(String value) {
            addCriterion("ARTICLE_TITLE <=", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleLike(String value) {
            addCriterion("ARTICLE_TITLE like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotLike(String value) {
            addCriterion("ARTICLE_TITLE not like", value, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleIn(List<String> values) {
            addCriterion("ARTICLE_TITLE in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotIn(List<String> values) {
            addCriterion("ARTICLE_TITLE not in", values, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleBetween(String value1, String value2) {
            addCriterion("ARTICLE_TITLE between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleTitleNotBetween(String value1, String value2) {
            addCriterion("ARTICLE_TITLE not between", value1, value2, "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorIsNull() {
            addCriterion("ARTICLE_AUTHOR is null");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorIsNotNull() {
            addCriterion("ARTICLE_AUTHOR is not null");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorEqualTo(String value) {
            addCriterion("ARTICLE_AUTHOR =", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotEqualTo(String value) {
            addCriterion("ARTICLE_AUTHOR <>", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorGreaterThan(String value) {
            addCriterion("ARTICLE_AUTHOR >", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLE_AUTHOR >=", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLessThan(String value) {
            addCriterion("ARTICLE_AUTHOR <", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLessThanOrEqualTo(String value) {
            addCriterion("ARTICLE_AUTHOR <=", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLike(String value) {
            addCriterion("ARTICLE_AUTHOR like", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotLike(String value) {
            addCriterion("ARTICLE_AUTHOR not like", value, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorIn(List<String> values) {
            addCriterion("ARTICLE_AUTHOR in", values, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotIn(List<String> values) {
            addCriterion("ARTICLE_AUTHOR not in", values, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorBetween(String value1, String value2) {
            addCriterion("ARTICLE_AUTHOR between", value1, value2, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorNotBetween(String value1, String value2) {
            addCriterion("ARTICLE_AUTHOR not between", value1, value2, "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticlePushByIsNull() {
            addCriterion("ARTICLE_PUSH_BY is null");
            return (Criteria) this;
        }

        public Criteria andArticlePushByIsNotNull() {
            addCriterion("ARTICLE_PUSH_BY is not null");
            return (Criteria) this;
        }

        public Criteria andArticlePushByEqualTo(String value) {
            addCriterion("ARTICLE_PUSH_BY =", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByNotEqualTo(String value) {
            addCriterion("ARTICLE_PUSH_BY <>", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByGreaterThan(String value) {
            addCriterion("ARTICLE_PUSH_BY >", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLE_PUSH_BY >=", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByLessThan(String value) {
            addCriterion("ARTICLE_PUSH_BY <", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByLessThanOrEqualTo(String value) {
            addCriterion("ARTICLE_PUSH_BY <=", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByLike(String value) {
            addCriterion("ARTICLE_PUSH_BY like", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByNotLike(String value) {
            addCriterion("ARTICLE_PUSH_BY not like", value, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByIn(List<String> values) {
            addCriterion("ARTICLE_PUSH_BY in", values, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByNotIn(List<String> values) {
            addCriterion("ARTICLE_PUSH_BY not in", values, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByBetween(String value1, String value2) {
            addCriterion("ARTICLE_PUSH_BY between", value1, value2, "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andArticlePushByNotBetween(String value1, String value2) {
            addCriterion("ARTICLE_PUSH_BY not between", value1, value2, "articlePushBy");
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

        public Criteria andUserAreaIsNull() {
            addCriterion("USER_AREA is null");
            return (Criteria) this;
        }

        public Criteria andUserAreaIsNotNull() {
            addCriterion("USER_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andUserAreaEqualTo(String value) {
            addCriterion("USER_AREA =", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaNotEqualTo(String value) {
            addCriterion("USER_AREA <>", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaGreaterThan(String value) {
            addCriterion("USER_AREA >", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaGreaterThanOrEqualTo(String value) {
            addCriterion("USER_AREA >=", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaLessThan(String value) {
            addCriterion("USER_AREA <", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaLessThanOrEqualTo(String value) {
            addCriterion("USER_AREA <=", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaLike(String value) {
            addCriterion("USER_AREA like", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaNotLike(String value) {
            addCriterion("USER_AREA not like", value, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaIn(List<String> values) {
            addCriterion("USER_AREA in", values, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaNotIn(List<String> values) {
            addCriterion("USER_AREA not in", values, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaBetween(String value1, String value2) {
            addCriterion("USER_AREA between", value1, value2, "userArea");
            return (Criteria) this;
        }

        public Criteria andUserAreaNotBetween(String value1, String value2) {
            addCriterion("USER_AREA not between", value1, value2, "userArea");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNull() {
            addCriterion("COMMENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIsNotNull() {
            addCriterion("COMMENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCommentTimeEqualTo(Date value) {
            addCriterion("COMMENT_TIME =", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotEqualTo(Date value) {
            addCriterion("COMMENT_TIME <>", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThan(Date value) {
            addCriterion("COMMENT_TIME >", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("COMMENT_TIME >=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThan(Date value) {
            addCriterion("COMMENT_TIME <", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeLessThanOrEqualTo(Date value) {
            addCriterion("COMMENT_TIME <=", value, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeIn(List<Date> values) {
            addCriterion("COMMENT_TIME in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotIn(List<Date> values) {
            addCriterion("COMMENT_TIME not in", values, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeBetween(Date value1, Date value2) {
            addCriterion("COMMENT_TIME between", value1, value2, "commentTime");
            return (Criteria) this;
        }

        public Criteria andCommentTimeNotBetween(Date value1, Date value2) {
            addCriterion("COMMENT_TIME not between", value1, value2, "commentTime");
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

        public Criteria andArtDelStautsIsNull() {
            addCriterion("ART_DEL_STAUTS is null");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsIsNotNull() {
            addCriterion("ART_DEL_STAUTS is not null");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsEqualTo(String value) {
            addCriterion("ART_DEL_STAUTS =", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsNotEqualTo(String value) {
            addCriterion("ART_DEL_STAUTS <>", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsGreaterThan(String value) {
            addCriterion("ART_DEL_STAUTS >", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsGreaterThanOrEqualTo(String value) {
            addCriterion("ART_DEL_STAUTS >=", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsLessThan(String value) {
            addCriterion("ART_DEL_STAUTS <", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsLessThanOrEqualTo(String value) {
            addCriterion("ART_DEL_STAUTS <=", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsLike(String value) {
            addCriterion("ART_DEL_STAUTS like", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsNotLike(String value) {
            addCriterion("ART_DEL_STAUTS not like", value, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsIn(List<String> values) {
            addCriterion("ART_DEL_STAUTS in", values, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsNotIn(List<String> values) {
            addCriterion("ART_DEL_STAUTS not in", values, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsBetween(String value1, String value2) {
            addCriterion("ART_DEL_STAUTS between", value1, value2, "artDelStauts");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsNotBetween(String value1, String value2) {
            addCriterion("ART_DEL_STAUTS not between", value1, value2, "artDelStauts");
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

        public Criteria andArticleTitleLikeInsensitive(String value) {
            addCriterion("upper(ARTICLE_TITLE) like", value.toUpperCase(), "articleTitle");
            return (Criteria) this;
        }

        public Criteria andArticleAuthorLikeInsensitive(String value) {
            addCriterion("upper(ARTICLE_AUTHOR) like", value.toUpperCase(), "articleAuthor");
            return (Criteria) this;
        }

        public Criteria andArticlePushByLikeInsensitive(String value) {
            addCriterion("upper(ARTICLE_PUSH_BY) like", value.toUpperCase(), "articlePushBy");
            return (Criteria) this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(USER_NAME) like", value.toUpperCase(), "userName");
            return (Criteria) this;
        }

        public Criteria andClientIpLikeInsensitive(String value) {
            addCriterion("upper(CLIENT_IP) like", value.toUpperCase(), "clientIp");
            return (Criteria) this;
        }

        public Criteria andUserAreaLikeInsensitive(String value) {
            addCriterion("upper(USER_AREA) like", value.toUpperCase(), "userArea");
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

        public Criteria andAvatarSrcLikeInsensitive(String value) {
            addCriterion("upper(AVATAR_SRC) like", value.toUpperCase(), "avatarSrc");
            return (Criteria) this;
        }

        public Criteria andArtDelStautsLikeInsensitive(String value) {
            addCriterion("upper(ART_DEL_STAUTS) like", value.toUpperCase(), "artDelStauts");
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