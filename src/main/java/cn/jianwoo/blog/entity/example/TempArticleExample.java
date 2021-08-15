package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TempArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public TempArticleExample() {
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

        public Criteria andMenuIdIsNull() {
            addCriterion("MENU_ID is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("MENU_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Integer value) {
            addCriterion("MENU_ID =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Integer value) {
            addCriterion("MENU_ID <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Integer value) {
            addCriterion("MENU_ID >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("MENU_ID >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Integer value) {
            addCriterion("MENU_ID <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("MENU_ID <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Integer> values) {
            addCriterion("MENU_ID in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Integer> values) {
            addCriterion("MENU_ID not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("MENU_ID between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("MENU_ID not between", value1, value2, "menuId");
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

        public Criteria andVisitTypeIsNull() {
            addCriterion("VISIT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNotNull() {
            addCriterion("VISIT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeEqualTo(String value) {
            addCriterion("VISIT_TYPE =", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotEqualTo(String value) {
            addCriterion("VISIT_TYPE <>", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThan(String value) {
            addCriterion("VISIT_TYPE >", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE >=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThan(String value) {
            addCriterion("VISIT_TYPE <", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE <=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLike(String value) {
            addCriterion("VISIT_TYPE like", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotLike(String value) {
            addCriterion("VISIT_TYPE not like", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIn(List<String> values) {
            addCriterion("VISIT_TYPE in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotIn(List<String> values) {
            addCriterion("VISIT_TYPE not in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE between", value1, value2, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE not between", value1, value2, "visitType");
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

        public Criteria andOldOidIsNull() {
            addCriterion("OLD_OID is null");
            return (Criteria) this;
        }

        public Criteria andOldOidIsNotNull() {
            addCriterion("OLD_OID is not null");
            return (Criteria) this;
        }

        public Criteria andOldOidEqualTo(Long value) {
            addCriterion("OLD_OID =", value, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidNotEqualTo(Long value) {
            addCriterion("OLD_OID <>", value, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidGreaterThan(Long value) {
            addCriterion("OLD_OID >", value, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidGreaterThanOrEqualTo(Long value) {
            addCriterion("OLD_OID >=", value, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidLessThan(Long value) {
            addCriterion("OLD_OID <", value, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidLessThanOrEqualTo(Long value) {
            addCriterion("OLD_OID <=", value, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidIn(List<Long> values) {
            addCriterion("OLD_OID in", values, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidNotIn(List<Long> values) {
            addCriterion("OLD_OID not in", values, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidBetween(Long value1, Long value2) {
            addCriterion("OLD_OID between", value1, value2, "oldOid");
            return (Criteria) this;
        }

        public Criteria andOldOidNotBetween(Long value1, Long value2) {
            addCriterion("OLD_OID not between", value1, value2, "oldOid");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("TAGS is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("TAGS is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("TAGS =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("TAGS <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("TAGS >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("TAGS >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("TAGS <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("TAGS <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("TAGS like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("TAGS not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("TAGS in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("TAGS not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("TAGS between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("TAGS not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNull() {
            addCriterion("PAGE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPageTypeIsNotNull() {
            addCriterion("PAGE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPageTypeEqualTo(String value) {
            addCriterion("PAGE_TYPE =", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotEqualTo(String value) {
            addCriterion("PAGE_TYPE <>", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThan(String value) {
            addCriterion("PAGE_TYPE >", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_TYPE >=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThan(String value) {
            addCriterion("PAGE_TYPE <", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLessThanOrEqualTo(String value) {
            addCriterion("PAGE_TYPE <=", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeLike(String value) {
            addCriterion("PAGE_TYPE like", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotLike(String value) {
            addCriterion("PAGE_TYPE not like", value, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeIn(List<String> values) {
            addCriterion("PAGE_TYPE in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotIn(List<String> values) {
            addCriterion("PAGE_TYPE not in", values, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeBetween(String value1, String value2) {
            addCriterion("PAGE_TYPE between", value1, value2, "pageType");
            return (Criteria) this;
        }

        public Criteria andPageTypeNotBetween(String value1, String value2) {
            addCriterion("PAGE_TYPE not between", value1, value2, "pageType");
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

        public Criteria andRestoreOidIsNull() {
            addCriterion("RESTORE_OID is null");
            return (Criteria) this;
        }

        public Criteria andRestoreOidIsNotNull() {
            addCriterion("RESTORE_OID is not null");
            return (Criteria) this;
        }

        public Criteria andRestoreOidEqualTo(Long value) {
            addCriterion("RESTORE_OID =", value, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidNotEqualTo(Long value) {
            addCriterion("RESTORE_OID <>", value, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidGreaterThan(Long value) {
            addCriterion("RESTORE_OID >", value, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidGreaterThanOrEqualTo(Long value) {
            addCriterion("RESTORE_OID >=", value, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidLessThan(Long value) {
            addCriterion("RESTORE_OID <", value, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidLessThanOrEqualTo(Long value) {
            addCriterion("RESTORE_OID <=", value, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidIn(List<Long> values) {
            addCriterion("RESTORE_OID in", values, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidNotIn(List<Long> values) {
            addCriterion("RESTORE_OID not in", values, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidBetween(Long value1, Long value2) {
            addCriterion("RESTORE_OID between", value1, value2, "restoreOid");
            return (Criteria) this;
        }

        public Criteria andRestoreOidNotBetween(Long value1, Long value2) {
            addCriterion("RESTORE_OID not between", value1, value2, "restoreOid");
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

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(TITLE) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andImgSrcLikeInsensitive(String value) {
            addCriterion("upper(IMG_SRC) like", value.toUpperCase(), "imgSrc");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLikeInsensitive(String value) {
            addCriterion("upper(VISIT_TYPE) like", value.toUpperCase(), "visitType");
            return (Criteria) this;
        }

        public Criteria andPasswordLikeInsensitive(String value) {
            addCriterion("upper(`PASSWORD`) like", value.toUpperCase(), "password");
            return (Criteria) this;
        }

        public Criteria andTagsLikeInsensitive(String value) {
            addCriterion("upper(TAGS) like", value.toUpperCase(), "tags");
            return (Criteria) this;
        }

        public Criteria andPageTypeLikeInsensitive(String value) {
            addCriterion("upper(PAGE_TYPE) like", value.toUpperCase(), "pageType");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(`STATUS`) like", value.toUpperCase(), "status");
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