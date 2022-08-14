package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public ArticleExample() {
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

        public Criteria andAuthorIsNull() {
            addCriterion("AUTHOR is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("AUTHOR is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("AUTHOR =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("AUTHOR <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("AUTHOR >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("AUTHOR <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("AUTHOR like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("AUTHOR not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("AUTHOR in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("AUTHOR not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("AUTHOR between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("AUTHOR not between", value1, value2, "author");
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

        public Criteria andPushByIsNull() {
            addCriterion("PUSH_BY is null");
            return (Criteria) this;
        }

        public Criteria andPushByIsNotNull() {
            addCriterion("PUSH_BY is not null");
            return (Criteria) this;
        }

        public Criteria andPushByEqualTo(String value) {
            addCriterion("PUSH_BY =", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByNotEqualTo(String value) {
            addCriterion("PUSH_BY <>", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByGreaterThan(String value) {
            addCriterion("PUSH_BY >", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByGreaterThanOrEqualTo(String value) {
            addCriterion("PUSH_BY >=", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByLessThan(String value) {
            addCriterion("PUSH_BY <", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByLessThanOrEqualTo(String value) {
            addCriterion("PUSH_BY <=", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByLike(String value) {
            addCriterion("PUSH_BY like", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByNotLike(String value) {
            addCriterion("PUSH_BY not like", value, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByIn(List<String> values) {
            addCriterion("PUSH_BY in", values, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByNotIn(List<String> values) {
            addCriterion("PUSH_BY not in", values, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByBetween(String value1, String value2) {
            addCriterion("PUSH_BY between", value1, value2, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushByNotBetween(String value1, String value2) {
            addCriterion("PUSH_BY not between", value1, value2, "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushIpIsNull() {
            addCriterion("PUSH_IP is null");
            return (Criteria) this;
        }

        public Criteria andPushIpIsNotNull() {
            addCriterion("PUSH_IP is not null");
            return (Criteria) this;
        }

        public Criteria andPushIpEqualTo(String value) {
            addCriterion("PUSH_IP =", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpNotEqualTo(String value) {
            addCriterion("PUSH_IP <>", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpGreaterThan(String value) {
            addCriterion("PUSH_IP >", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpGreaterThanOrEqualTo(String value) {
            addCriterion("PUSH_IP >=", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpLessThan(String value) {
            addCriterion("PUSH_IP <", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpLessThanOrEqualTo(String value) {
            addCriterion("PUSH_IP <=", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpLike(String value) {
            addCriterion("PUSH_IP like", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpNotLike(String value) {
            addCriterion("PUSH_IP not like", value, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpIn(List<String> values) {
            addCriterion("PUSH_IP in", values, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpNotIn(List<String> values) {
            addCriterion("PUSH_IP not in", values, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpBetween(String value1, String value2) {
            addCriterion("PUSH_IP between", value1, value2, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushIpNotBetween(String value1, String value2) {
            addCriterion("PUSH_IP not between", value1, value2, "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushRegionIsNull() {
            addCriterion("PUSH_REGION is null");
            return (Criteria) this;
        }

        public Criteria andPushRegionIsNotNull() {
            addCriterion("PUSH_REGION is not null");
            return (Criteria) this;
        }

        public Criteria andPushRegionEqualTo(String value) {
            addCriterion("PUSH_REGION =", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionNotEqualTo(String value) {
            addCriterion("PUSH_REGION <>", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionGreaterThan(String value) {
            addCriterion("PUSH_REGION >", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionGreaterThanOrEqualTo(String value) {
            addCriterion("PUSH_REGION >=", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionLessThan(String value) {
            addCriterion("PUSH_REGION <", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionLessThanOrEqualTo(String value) {
            addCriterion("PUSH_REGION <=", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionLike(String value) {
            addCriterion("PUSH_REGION like", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionNotLike(String value) {
            addCriterion("PUSH_REGION not like", value, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionIn(List<String> values) {
            addCriterion("PUSH_REGION in", values, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionNotIn(List<String> values) {
            addCriterion("PUSH_REGION not in", values, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionBetween(String value1, String value2) {
            addCriterion("PUSH_REGION between", value1, value2, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andPushRegionNotBetween(String value1, String value2) {
            addCriterion("PUSH_REGION not between", value1, value2, "pushRegion");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("MODIFIED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("MODIFIED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Date value) {
            addCriterion("MODIFIED_TIME =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Date value) {
            addCriterion("MODIFIED_TIME <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Date value) {
            addCriterion("MODIFIED_TIME >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MODIFIED_TIME >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Date value) {
            addCriterion("MODIFIED_TIME <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("MODIFIED_TIME <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Date> values) {
            addCriterion("MODIFIED_TIME in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Date> values) {
            addCriterion("MODIFIED_TIME not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("MODIFIED_TIME between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("MODIFIED_TIME not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNull() {
            addCriterion("CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNotNull() {
            addCriterion("CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameEqualTo(String value) {
            addCriterion("CATEGORY_NAME =", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotEqualTo(String value) {
            addCriterion("CATEGORY_NAME <>", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThan(String value) {
            addCriterion("CATEGORY_NAME >", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME >=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThan(String value) {
            addCriterion("CATEGORY_NAME <", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME <=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLike(String value) {
            addCriterion("CATEGORY_NAME like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotLike(String value) {
            addCriterion("CATEGORY_NAME not like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIn(List<String> values) {
            addCriterion("CATEGORY_NAME in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotIn(List<String> values) {
            addCriterion("CATEGORY_NAME not in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME not between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNull() {
            addCriterion("READ_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andReadCountIsNotNull() {
            addCriterion("READ_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andReadCountEqualTo(Long value) {
            addCriterion("READ_COUNT =", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotEqualTo(Long value) {
            addCriterion("READ_COUNT <>", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThan(Long value) {
            addCriterion("READ_COUNT >", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountGreaterThanOrEqualTo(Long value) {
            addCriterion("READ_COUNT >=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThan(Long value) {
            addCriterion("READ_COUNT <", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountLessThanOrEqualTo(Long value) {
            addCriterion("READ_COUNT <=", value, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountIn(List<Long> values) {
            addCriterion("READ_COUNT in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotIn(List<Long> values) {
            addCriterion("READ_COUNT not in", values, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountBetween(Long value1, Long value2) {
            addCriterion("READ_COUNT between", value1, value2, "readCount");
            return (Criteria) this;
        }

        public Criteria andReadCountNotBetween(Long value1, Long value2) {
            addCriterion("READ_COUNT not between", value1, value2, "readCount");
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

        public Criteria andIsCommentIsNull() {
            addCriterion("IS_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andIsCommentIsNotNull() {
            addCriterion("IS_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andIsCommentEqualTo(Boolean value) {
            addCriterion("IS_COMMENT =", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotEqualTo(Boolean value) {
            addCriterion("IS_COMMENT <>", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentGreaterThan(Boolean value) {
            addCriterion("IS_COMMENT >", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_COMMENT >=", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLessThan(Boolean value) {
            addCriterion("IS_COMMENT <", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_COMMENT <=", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentLike(Boolean value) {
            addCriterion("IS_COMMENT like", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotLike(Boolean value) {
            addCriterion("IS_COMMENT not like", value, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentIn(List<Boolean> values) {
            addCriterion("IS_COMMENT in", values, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotIn(List<Boolean> values) {
            addCriterion("IS_COMMENT not in", values, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_COMMENT between", value1, value2, "isComment");
            return (Criteria) this;
        }

        public Criteria andIsCommentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_COMMENT not between", value1, value2, "isComment");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalIsNull() {
            addCriterion("FLAG_ORIGINAL is null");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalIsNotNull() {
            addCriterion("FLAG_ORIGINAL is not null");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalEqualTo(Boolean value) {
            addCriterion("FLAG_ORIGINAL =", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalNotEqualTo(Boolean value) {
            addCriterion("FLAG_ORIGINAL <>", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalGreaterThan(Boolean value) {
            addCriterion("FLAG_ORIGINAL >", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_ORIGINAL >=", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalLessThan(Boolean value) {
            addCriterion("FLAG_ORIGINAL <", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalLessThanOrEqualTo(Boolean value) {
            addCriterion("FLAG_ORIGINAL <=", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalLike(Boolean value) {
            addCriterion("FLAG_ORIGINAL like", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalNotLike(Boolean value) {
            addCriterion("FLAG_ORIGINAL not like", value, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalIn(List<Boolean> values) {
            addCriterion("FLAG_ORIGINAL in", values, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalNotIn(List<Boolean> values) {
            addCriterion("FLAG_ORIGINAL not in", values, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_ORIGINAL between", value1, value2, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andFlagOriginalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("FLAG_ORIGINAL not between", value1, value2, "flagOriginal");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlIsNull() {
            addCriterion("ORIGINAL_URL is null");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlIsNotNull() {
            addCriterion("ORIGINAL_URL is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlEqualTo(String value) {
            addCriterion("ORIGINAL_URL =", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotEqualTo(String value) {
            addCriterion("ORIGINAL_URL <>", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlGreaterThan(String value) {
            addCriterion("ORIGINAL_URL >", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ORIGINAL_URL >=", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLessThan(String value) {
            addCriterion("ORIGINAL_URL <", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLessThanOrEqualTo(String value) {
            addCriterion("ORIGINAL_URL <=", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLike(String value) {
            addCriterion("ORIGINAL_URL like", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotLike(String value) {
            addCriterion("ORIGINAL_URL not like", value, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlIn(List<String> values) {
            addCriterion("ORIGINAL_URL in", values, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotIn(List<String> values) {
            addCriterion("ORIGINAL_URL not in", values, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlBetween(String value1, String value2) {
            addCriterion("ORIGINAL_URL between", value1, value2, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlNotBetween(String value1, String value2) {
            addCriterion("ORIGINAL_URL not between", value1, value2, "originalUrl");
            return (Criteria) this;
        }

        public Criteria andImgSrcIsNull() {
            addCriterion("IMG_SRC is null");
            return (Criteria) this;
        }

        public Criteria andImgSrcIsNotNull() {
            addCriterion("IMG_SRC is not null");
            return (Criteria) this;
        }

        public Criteria andImgSrcEqualTo(String value) {
            addCriterion("IMG_SRC =", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcNotEqualTo(String value) {
            addCriterion("IMG_SRC <>", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcGreaterThan(String value) {
            addCriterion("IMG_SRC >", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcGreaterThanOrEqualTo(String value) {
            addCriterion("IMG_SRC >=", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcLessThan(String value) {
            addCriterion("IMG_SRC <", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcLessThanOrEqualTo(String value) {
            addCriterion("IMG_SRC <=", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcLike(String value) {
            addCriterion("IMG_SRC like", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcNotLike(String value) {
            addCriterion("IMG_SRC not like", value, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcIn(List<String> values) {
            addCriterion("IMG_SRC in", values, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcNotIn(List<String> values) {
            addCriterion("IMG_SRC not in", values, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcBetween(String value1, String value2) {
            addCriterion("IMG_SRC between", value1, value2, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andImgSrcNotBetween(String value1, String value2) {
            addCriterion("IMG_SRC not between", value1, value2, "imgSrc");
            return (Criteria) this;
        }

        public Criteria andAccessTypeIsNull() {
            addCriterion("ACCESS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAccessTypeIsNotNull() {
            addCriterion("ACCESS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTypeEqualTo(String value) {
            addCriterion("ACCESS_TYPE =", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotEqualTo(String value) {
            addCriterion("ACCESS_TYPE <>", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeGreaterThan(String value) {
            addCriterion("ACCESS_TYPE >", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_TYPE >=", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeLessThan(String value) {
            addCriterion("ACCESS_TYPE <", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_TYPE <=", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeLike(String value) {
            addCriterion("ACCESS_TYPE like", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotLike(String value) {
            addCriterion("ACCESS_TYPE not like", value, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeIn(List<String> values) {
            addCriterion("ACCESS_TYPE in", values, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotIn(List<String> values) {
            addCriterion("ACCESS_TYPE not in", values, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeBetween(String value1, String value2) {
            addCriterion("ACCESS_TYPE between", value1, value2, "accessType");
            return (Criteria) this;
        }

        public Criteria andAccessTypeNotBetween(String value1, String value2) {
            addCriterion("ACCESS_TYPE not between", value1, value2, "accessType");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusIsNull() {
            addCriterion("TOP_PLACE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusIsNotNull() {
            addCriterion("TOP_PLACE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusEqualTo(String value) {
            addCriterion("TOP_PLACE_STATUS =", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusNotEqualTo(String value) {
            addCriterion("TOP_PLACE_STATUS <>", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusGreaterThan(String value) {
            addCriterion("TOP_PLACE_STATUS >", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TOP_PLACE_STATUS >=", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusLessThan(String value) {
            addCriterion("TOP_PLACE_STATUS <", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusLessThanOrEqualTo(String value) {
            addCriterion("TOP_PLACE_STATUS <=", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusLike(String value) {
            addCriterion("TOP_PLACE_STATUS like", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusNotLike(String value) {
            addCriterion("TOP_PLACE_STATUS not like", value, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusIn(List<String> values) {
            addCriterion("TOP_PLACE_STATUS in", values, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusNotIn(List<String> values) {
            addCriterion("TOP_PLACE_STATUS not in", values, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusBetween(String value1, String value2) {
            addCriterion("TOP_PLACE_STATUS between", value1, value2, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusNotBetween(String value1, String value2) {
            addCriterion("TOP_PLACE_STATUS not between", value1, value2, "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNull() {
            addCriterion("COMMENT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNotNull() {
            addCriterion("COMMENT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCommentCountEqualTo(Long value) {
            addCriterion("COMMENT_COUNT =", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotEqualTo(Long value) {
            addCriterion("COMMENT_COUNT <>", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThan(Long value) {
            addCriterion("COMMENT_COUNT >", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThanOrEqualTo(Long value) {
            addCriterion("COMMENT_COUNT >=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThan(Long value) {
            addCriterion("COMMENT_COUNT <", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThanOrEqualTo(Long value) {
            addCriterion("COMMENT_COUNT <=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIn(List<Long> values) {
            addCriterion("COMMENT_COUNT in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotIn(List<Long> values) {
            addCriterion("COMMENT_COUNT not in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountBetween(Long value1, Long value2) {
            addCriterion("COMMENT_COUNT between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotBetween(Long value1, Long value2) {
            addCriterion("COMMENT_COUNT not between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsIsNull() {
            addCriterion("TOTAL_COMMENT_FLOORS is null");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsIsNotNull() {
            addCriterion("TOTAL_COMMENT_FLOORS is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsEqualTo(Long value) {
            addCriterion("TOTAL_COMMENT_FLOORS =", value, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsNotEqualTo(Long value) {
            addCriterion("TOTAL_COMMENT_FLOORS <>", value, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsGreaterThan(Long value) {
            addCriterion("TOTAL_COMMENT_FLOORS >", value, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsGreaterThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COMMENT_FLOORS >=", value, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsLessThan(Long value) {
            addCriterion("TOTAL_COMMENT_FLOORS <", value, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsLessThanOrEqualTo(Long value) {
            addCriterion("TOTAL_COMMENT_FLOORS <=", value, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsIn(List<Long> values) {
            addCriterion("TOTAL_COMMENT_FLOORS in", values, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsNotIn(List<Long> values) {
            addCriterion("TOTAL_COMMENT_FLOORS not in", values, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COMMENT_FLOORS between", value1, value2, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andTotalCommentFloorsNotBetween(Long value1, Long value2) {
            addCriterion("TOTAL_COMMENT_FLOORS not between", value1, value2, "totalCommentFloors");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`STATUS` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`STATUS` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`STATUS` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`STATUS` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`STATUS` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`STATUS` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`STATUS` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`STATUS` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`STATUS` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`STATUS` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`STATUS` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`STATUS` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`STATUS` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`STATUS` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("`PASSWORD` is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("`PASSWORD` is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("`PASSWORD` =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("`PASSWORD` <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("`PASSWORD` >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("`PASSWORD` >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("`PASSWORD` <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("`PASSWORD` <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("`PASSWORD` like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("`PASSWORD` not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("`PASSWORD` in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("`PASSWORD` not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("`PASSWORD` between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("`PASSWORD` not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andDelTimeIsNull() {
            addCriterion("DEL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDelTimeIsNotNull() {
            addCriterion("DEL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDelTimeEqualTo(Date value) {
            addCriterion("DEL_TIME =", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeNotEqualTo(Date value) {
            addCriterion("DEL_TIME <>", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeGreaterThan(Date value) {
            addCriterion("DEL_TIME >", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DEL_TIME >=", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeLessThan(Date value) {
            addCriterion("DEL_TIME <", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeLessThanOrEqualTo(Date value) {
            addCriterion("DEL_TIME <=", value, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeIn(List<Date> values) {
            addCriterion("DEL_TIME in", values, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeNotIn(List<Date> values) {
            addCriterion("DEL_TIME not in", values, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeBetween(Date value1, Date value2) {
            addCriterion("DEL_TIME between", value1, value2, "delTime");
            return (Criteria) this;
        }

        public Criteria andDelTimeNotBetween(Date value1, Date value2) {
            addCriterion("DEL_TIME not between", value1, value2, "delTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeIsNull() {
            addCriterion("REMOVE_RECYCLE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeIsNotNull() {
            addCriterion("REMOVE_RECYCLE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeEqualTo(Date value) {
            addCriterion("REMOVE_RECYCLE_TIME =", value, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeNotEqualTo(Date value) {
            addCriterion("REMOVE_RECYCLE_TIME <>", value, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeGreaterThan(Date value) {
            addCriterion("REMOVE_RECYCLE_TIME >", value, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REMOVE_RECYCLE_TIME >=", value, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeLessThan(Date value) {
            addCriterion("REMOVE_RECYCLE_TIME <", value, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeLessThanOrEqualTo(Date value) {
            addCriterion("REMOVE_RECYCLE_TIME <=", value, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeIn(List<Date> values) {
            addCriterion("REMOVE_RECYCLE_TIME in", values, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeNotIn(List<Date> values) {
            addCriterion("REMOVE_RECYCLE_TIME not in", values, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeBetween(Date value1, Date value2) {
            addCriterion("REMOVE_RECYCLE_TIME between", value1, value2, "removeRecycleTime");
            return (Criteria) this;
        }

        public Criteria andRemoveRecycleTimeNotBetween(Date value1, Date value2) {
            addCriterion("REMOVE_RECYCLE_TIME not between", value1, value2, "removeRecycleTime");
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

        public Criteria andAuthorLikeInsensitive(String value) {
            addCriterion("upper(AUTHOR) like", value.toUpperCase(), "author");
            return (Criteria) this;
        }

        public Criteria andPushByLikeInsensitive(String value) {
            addCriterion("upper(PUSH_BY) like", value.toUpperCase(), "pushBy");
            return (Criteria) this;
        }

        public Criteria andPushIpLikeInsensitive(String value) {
            addCriterion("upper(PUSH_IP) like", value.toUpperCase(), "pushIp");
            return (Criteria) this;
        }

        public Criteria andPushRegionLikeInsensitive(String value) {
            addCriterion("upper(PUSH_REGION) like", value.toUpperCase(), "pushRegion");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(TITLE) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLikeInsensitive(String value) {
            addCriterion("upper(CATEGORY_NAME) like", value.toUpperCase(), "categoryName");
            return (Criteria) this;
        }

        public Criteria andOriginalUrlLikeInsensitive(String value) {
            addCriterion("upper(ORIGINAL_URL) like", value.toUpperCase(), "originalUrl");
            return (Criteria) this;
        }

        public Criteria andImgSrcLikeInsensitive(String value) {
            addCriterion("upper(IMG_SRC) like", value.toUpperCase(), "imgSrc");
            return (Criteria) this;
        }

        public Criteria andAccessTypeLikeInsensitive(String value) {
            addCriterion("upper(ACCESS_TYPE) like", value.toUpperCase(), "accessType");
            return (Criteria) this;
        }

        public Criteria andTopPlaceStatusLikeInsensitive(String value) {
            addCriterion("upper(TOP_PLACE_STATUS) like", value.toUpperCase(), "topPlaceStatus");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(`STATUS`) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andPasswordLikeInsensitive(String value) {
            addCriterion("upper(`PASSWORD`) like", value.toUpperCase(), "password");
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