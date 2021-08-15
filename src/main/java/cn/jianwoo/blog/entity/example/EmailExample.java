package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public EmailExample() {
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

        public Criteria andToEmailIsNull() {
            addCriterion("TO_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andToEmailIsNotNull() {
            addCriterion("TO_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andToEmailEqualTo(String value) {
            addCriterion("TO_EMAIL =", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotEqualTo(String value) {
            addCriterion("TO_EMAIL <>", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailGreaterThan(String value) {
            addCriterion("TO_EMAIL >", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailGreaterThanOrEqualTo(String value) {
            addCriterion("TO_EMAIL >=", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailLessThan(String value) {
            addCriterion("TO_EMAIL <", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailLessThanOrEqualTo(String value) {
            addCriterion("TO_EMAIL <=", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailLike(String value) {
            addCriterion("TO_EMAIL like", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotLike(String value) {
            addCriterion("TO_EMAIL not like", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailIn(List<String> values) {
            addCriterion("TO_EMAIL in", values, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotIn(List<String> values) {
            addCriterion("TO_EMAIL not in", values, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailBetween(String value1, String value2) {
            addCriterion("TO_EMAIL between", value1, value2, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotBetween(String value1, String value2) {
            addCriterion("TO_EMAIL not between", value1, value2, "toEmail");
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

        public Criteria andJsonDataIsNull() {
            addCriterion("JSON_DATA is null");
            return (Criteria) this;
        }

        public Criteria andJsonDataIsNotNull() {
            addCriterion("JSON_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andJsonDataEqualTo(String value) {
            addCriterion("JSON_DATA =", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataNotEqualTo(String value) {
            addCriterion("JSON_DATA <>", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataGreaterThan(String value) {
            addCriterion("JSON_DATA >", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataGreaterThanOrEqualTo(String value) {
            addCriterion("JSON_DATA >=", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataLessThan(String value) {
            addCriterion("JSON_DATA <", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataLessThanOrEqualTo(String value) {
            addCriterion("JSON_DATA <=", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataLike(String value) {
            addCriterion("JSON_DATA like", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataNotLike(String value) {
            addCriterion("JSON_DATA not like", value, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataIn(List<String> values) {
            addCriterion("JSON_DATA in", values, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataNotIn(List<String> values) {
            addCriterion("JSON_DATA not in", values, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataBetween(String value1, String value2) {
            addCriterion("JSON_DATA between", value1, value2, "jsonData");
            return (Criteria) this;
        }

        public Criteria andJsonDataNotBetween(String value1, String value2) {
            addCriterion("JSON_DATA not between", value1, value2, "jsonData");
            return (Criteria) this;
        }

        public Criteria andFilesIsNull() {
            addCriterion("FILES is null");
            return (Criteria) this;
        }

        public Criteria andFilesIsNotNull() {
            addCriterion("FILES is not null");
            return (Criteria) this;
        }

        public Criteria andFilesEqualTo(String value) {
            addCriterion("FILES =", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesNotEqualTo(String value) {
            addCriterion("FILES <>", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesGreaterThan(String value) {
            addCriterion("FILES >", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesGreaterThanOrEqualTo(String value) {
            addCriterion("FILES >=", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesLessThan(String value) {
            addCriterion("FILES <", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesLessThanOrEqualTo(String value) {
            addCriterion("FILES <=", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesLike(String value) {
            addCriterion("FILES like", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesNotLike(String value) {
            addCriterion("FILES not like", value, "files");
            return (Criteria) this;
        }

        public Criteria andFilesIn(List<String> values) {
            addCriterion("FILES in", values, "files");
            return (Criteria) this;
        }

        public Criteria andFilesNotIn(List<String> values) {
            addCriterion("FILES not in", values, "files");
            return (Criteria) this;
        }

        public Criteria andFilesBetween(String value1, String value2) {
            addCriterion("FILES between", value1, value2, "files");
            return (Criteria) this;
        }

        public Criteria andFilesNotBetween(String value1, String value2) {
            addCriterion("FILES not between", value1, value2, "files");
            return (Criteria) this;
        }

        public Criteria andProcStatusIsNull() {
            addCriterion("PROC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andProcStatusIsNotNull() {
            addCriterion("PROC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andProcStatusEqualTo(String value) {
            addCriterion("PROC_STATUS =", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusNotEqualTo(String value) {
            addCriterion("PROC_STATUS <>", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusGreaterThan(String value) {
            addCriterion("PROC_STATUS >", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_STATUS >=", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusLessThan(String value) {
            addCriterion("PROC_STATUS <", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusLessThanOrEqualTo(String value) {
            addCriterion("PROC_STATUS <=", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusLike(String value) {
            addCriterion("PROC_STATUS like", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusNotLike(String value) {
            addCriterion("PROC_STATUS not like", value, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusIn(List<String> values) {
            addCriterion("PROC_STATUS in", values, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusNotIn(List<String> values) {
            addCriterion("PROC_STATUS not in", values, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusBetween(String value1, String value2) {
            addCriterion("PROC_STATUS between", value1, value2, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcStatusNotBetween(String value1, String value2) {
            addCriterion("PROC_STATUS not between", value1, value2, "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcDescIsNull() {
            addCriterion("PROC_DESC is null");
            return (Criteria) this;
        }

        public Criteria andProcDescIsNotNull() {
            addCriterion("PROC_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andProcDescEqualTo(String value) {
            addCriterion("PROC_DESC =", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescNotEqualTo(String value) {
            addCriterion("PROC_DESC <>", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescGreaterThan(String value) {
            addCriterion("PROC_DESC >", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DESC >=", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescLessThan(String value) {
            addCriterion("PROC_DESC <", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescLessThanOrEqualTo(String value) {
            addCriterion("PROC_DESC <=", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescLike(String value) {
            addCriterion("PROC_DESC like", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescNotLike(String value) {
            addCriterion("PROC_DESC not like", value, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescIn(List<String> values) {
            addCriterion("PROC_DESC in", values, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescNotIn(List<String> values) {
            addCriterion("PROC_DESC not in", values, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescBetween(String value1, String value2) {
            addCriterion("PROC_DESC between", value1, value2, "procDesc");
            return (Criteria) this;
        }

        public Criteria andProcDescNotBetween(String value1, String value2) {
            addCriterion("PROC_DESC not between", value1, value2, "procDesc");
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

        public Criteria andToEmailLikeInsensitive(String value) {
            addCriterion("upper(TO_EMAIL) like", value.toUpperCase(), "toEmail");
            return (Criteria) this;
        }

        public Criteria andEmailTplCodeLikeInsensitive(String value) {
            addCriterion("upper(EMAIL_TPL_CODE) like", value.toUpperCase(), "emailTplCode");
            return (Criteria) this;
        }

        public Criteria andSubjectLikeInsensitive(String value) {
            addCriterion("upper(SUBJECT) like", value.toUpperCase(), "subject");
            return (Criteria) this;
        }

        public Criteria andJsonDataLikeInsensitive(String value) {
            addCriterion("upper(JSON_DATA) like", value.toUpperCase(), "jsonData");
            return (Criteria) this;
        }

        public Criteria andFilesLikeInsensitive(String value) {
            addCriterion("upper(FILES) like", value.toUpperCase(), "files");
            return (Criteria) this;
        }

        public Criteria andProcStatusLikeInsensitive(String value) {
            addCriterion("upper(PROC_STATUS) like", value.toUpperCase(), "procStatus");
            return (Criteria) this;
        }

        public Criteria andProcDescLikeInsensitive(String value) {
            addCriterion("upper(PROC_DESC) like", value.toUpperCase(), "procDesc");
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