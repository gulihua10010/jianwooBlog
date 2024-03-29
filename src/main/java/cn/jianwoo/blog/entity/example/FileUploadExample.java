package cn.jianwoo.blog.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUploadExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer start;

    protected Integer rows;

    public FileUploadExample() {
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

        public Criteria andFileUuidIsNull() {
            addCriterion("FILE_UUID is null");
            return (Criteria) this;
        }

        public Criteria andFileUuidIsNotNull() {
            addCriterion("FILE_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andFileUuidEqualTo(String value) {
            addCriterion("FILE_UUID =", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidNotEqualTo(String value) {
            addCriterion("FILE_UUID <>", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidGreaterThan(String value) {
            addCriterion("FILE_UUID >", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_UUID >=", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidLessThan(String value) {
            addCriterion("FILE_UUID <", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidLessThanOrEqualTo(String value) {
            addCriterion("FILE_UUID <=", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidLike(String value) {
            addCriterion("FILE_UUID like", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidNotLike(String value) {
            addCriterion("FILE_UUID not like", value, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidIn(List<String> values) {
            addCriterion("FILE_UUID in", values, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidNotIn(List<String> values) {
            addCriterion("FILE_UUID not in", values, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidBetween(String value1, String value2) {
            addCriterion("FILE_UUID between", value1, value2, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileUuidNotBetween(String value1, String value2) {
            addCriterion("FILE_UUID not between", value1, value2, "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameIsNull() {
            addCriterion("OLD_FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOldFileNameIsNotNull() {
            addCriterion("OLD_FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOldFileNameEqualTo(String value) {
            addCriterion("OLD_FILE_NAME =", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameNotEqualTo(String value) {
            addCriterion("OLD_FILE_NAME <>", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameGreaterThan(String value) {
            addCriterion("OLD_FILE_NAME >", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("OLD_FILE_NAME >=", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameLessThan(String value) {
            addCriterion("OLD_FILE_NAME <", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameLessThanOrEqualTo(String value) {
            addCriterion("OLD_FILE_NAME <=", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameLike(String value) {
            addCriterion("OLD_FILE_NAME like", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameNotLike(String value) {
            addCriterion("OLD_FILE_NAME not like", value, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameIn(List<String> values) {
            addCriterion("OLD_FILE_NAME in", values, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameNotIn(List<String> values) {
            addCriterion("OLD_FILE_NAME not in", values, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameBetween(String value1, String value2) {
            addCriterion("OLD_FILE_NAME between", value1, value2, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameNotBetween(String value1, String value2) {
            addCriterion("OLD_FILE_NAME not between", value1, value2, "oldFileName");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("`SIZE` is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("`SIZE` is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Long value) {
            addCriterion("`SIZE` =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Long value) {
            addCriterion("`SIZE` <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Long value) {
            addCriterion("`SIZE` >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("`SIZE` >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Long value) {
            addCriterion("`SIZE` <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Long value) {
            addCriterion("`SIZE` <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Long> values) {
            addCriterion("`SIZE` in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Long> values) {
            addCriterion("`SIZE` not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Long value1, Long value2) {
            addCriterion("`SIZE` between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Long value1, Long value2) {
            addCriterion("`SIZE` not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`TYPE` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`TYPE` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`TYPE` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`TYPE` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`TYPE` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`TYPE` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`TYPE` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`TYPE` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`TYPE` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`TYPE` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`TYPE` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`TYPE` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`TYPE` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`TYPE` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andMediaInfoIsNull() {
            addCriterion("MEDIA_INFO is null");
            return (Criteria) this;
        }

        public Criteria andMediaInfoIsNotNull() {
            addCriterion("MEDIA_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andMediaInfoEqualTo(String value) {
            addCriterion("MEDIA_INFO =", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoNotEqualTo(String value) {
            addCriterion("MEDIA_INFO <>", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoGreaterThan(String value) {
            addCriterion("MEDIA_INFO >", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoGreaterThanOrEqualTo(String value) {
            addCriterion("MEDIA_INFO >=", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoLessThan(String value) {
            addCriterion("MEDIA_INFO <", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoLessThanOrEqualTo(String value) {
            addCriterion("MEDIA_INFO <=", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoLike(String value) {
            addCriterion("MEDIA_INFO like", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoNotLike(String value) {
            addCriterion("MEDIA_INFO not like", value, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoIn(List<String> values) {
            addCriterion("MEDIA_INFO in", values, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoNotIn(List<String> values) {
            addCriterion("MEDIA_INFO not in", values, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoBetween(String value1, String value2) {
            addCriterion("MEDIA_INFO between", value1, value2, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andMediaInfoNotBetween(String value1, String value2) {
            addCriterion("MEDIA_INFO not between", value1, value2, "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andPathIsNull() {
            addCriterion("`PATH` is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("`PATH` is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("`PATH` =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("`PATH` <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("`PATH` >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("`PATH` >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("`PATH` <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("`PATH` <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("`PATH` like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("`PATH` not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("`PATH` in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("`PATH` not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("`PATH` between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("`PATH` not between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andCdnUrlIsNull() {
            addCriterion("CDN_URL is null");
            return (Criteria) this;
        }

        public Criteria andCdnUrlIsNotNull() {
            addCriterion("CDN_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCdnUrlEqualTo(String value) {
            addCriterion("CDN_URL =", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlNotEqualTo(String value) {
            addCriterion("CDN_URL <>", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlGreaterThan(String value) {
            addCriterion("CDN_URL >", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CDN_URL >=", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlLessThan(String value) {
            addCriterion("CDN_URL <", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlLessThanOrEqualTo(String value) {
            addCriterion("CDN_URL <=", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlLike(String value) {
            addCriterion("CDN_URL like", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlNotLike(String value) {
            addCriterion("CDN_URL not like", value, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlIn(List<String> values) {
            addCriterion("CDN_URL in", values, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlNotIn(List<String> values) {
            addCriterion("CDN_URL not in", values, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlBetween(String value1, String value2) {
            addCriterion("CDN_URL between", value1, value2, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andCdnUrlNotBetween(String value1, String value2) {
            addCriterion("CDN_URL not between", value1, value2, "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andFileMd5IsNull() {
            addCriterion("FILE_MD5 is null");
            return (Criteria) this;
        }

        public Criteria andFileMd5IsNotNull() {
            addCriterion("FILE_MD5 is not null");
            return (Criteria) this;
        }

        public Criteria andFileMd5EqualTo(String value) {
            addCriterion("FILE_MD5 =", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotEqualTo(String value) {
            addCriterion("FILE_MD5 <>", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5GreaterThan(String value) {
            addCriterion("FILE_MD5 >", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5GreaterThanOrEqualTo(String value) {
            addCriterion("FILE_MD5 >=", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5LessThan(String value) {
            addCriterion("FILE_MD5 <", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5LessThanOrEqualTo(String value) {
            addCriterion("FILE_MD5 <=", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5Like(String value) {
            addCriterion("FILE_MD5 like", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotLike(String value) {
            addCriterion("FILE_MD5 not like", value, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5In(List<String> values) {
            addCriterion("FILE_MD5 in", values, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotIn(List<String> values) {
            addCriterion("FILE_MD5 not in", values, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5Between(String value1, String value2) {
            addCriterion("FILE_MD5 between", value1, value2, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andFileMd5NotBetween(String value1, String value2) {
            addCriterion("FILE_MD5 not between", value1, value2, "fileMd5");
            return (Criteria) this;
        }

        public Criteria andIsChunkIsNull() {
            addCriterion("IS_CHUNK is null");
            return (Criteria) this;
        }

        public Criteria andIsChunkIsNotNull() {
            addCriterion("IS_CHUNK is not null");
            return (Criteria) this;
        }

        public Criteria andIsChunkEqualTo(Boolean value) {
            addCriterion("IS_CHUNK =", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkNotEqualTo(Boolean value) {
            addCriterion("IS_CHUNK <>", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkGreaterThan(Boolean value) {
            addCriterion("IS_CHUNK >", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_CHUNK >=", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkLessThan(Boolean value) {
            addCriterion("IS_CHUNK <", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_CHUNK <=", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkLike(Boolean value) {
            addCriterion("IS_CHUNK like", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkNotLike(Boolean value) {
            addCriterion("IS_CHUNK not like", value, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkIn(List<Boolean> values) {
            addCriterion("IS_CHUNK in", values, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkNotIn(List<Boolean> values) {
            addCriterion("IS_CHUNK not in", values, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_CHUNK between", value1, value2, "isChunk");
            return (Criteria) this;
        }

        public Criteria andIsChunkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_CHUNK not between", value1, value2, "isChunk");
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

        public Criteria andUploadTimeIsNull() {
            addCriterion("UPLOAD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("UPLOAD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("UPLOAD_TIME =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("UPLOAD_TIME <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("UPLOAD_TIME >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPLOAD_TIME >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("UPLOAD_TIME <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPLOAD_TIME <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("UPLOAD_TIME in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("UPLOAD_TIME not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("UPLOAD_TIME between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPLOAD_TIME not between", value1, value2, "uploadTime");
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

        public Criteria andFileUuidLikeInsensitive(String value) {
            addCriterion("upper(FILE_UUID) like", value.toUpperCase(), "fileUuid");
            return (Criteria) this;
        }

        public Criteria andFileNameLikeInsensitive(String value) {
            addCriterion("upper(FILE_NAME) like", value.toUpperCase(), "fileName");
            return (Criteria) this;
        }

        public Criteria andOldFileNameLikeInsensitive(String value) {
            addCriterion("upper(OLD_FILE_NAME) like", value.toUpperCase(), "oldFileName");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(`TYPE`) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }

        public Criteria andMediaInfoLikeInsensitive(String value) {
            addCriterion("upper(MEDIA_INFO) like", value.toUpperCase(), "mediaInfo");
            return (Criteria) this;
        }

        public Criteria andPathLikeInsensitive(String value) {
            addCriterion("upper(`PATH`) like", value.toUpperCase(), "path");
            return (Criteria) this;
        }

        public Criteria andUrlLikeInsensitive(String value) {
            addCriterion("upper(URL) like", value.toUpperCase(), "url");
            return (Criteria) this;
        }

        public Criteria andCdnUrlLikeInsensitive(String value) {
            addCriterion("upper(CDN_URL) like", value.toUpperCase(), "cdnUrl");
            return (Criteria) this;
        }

        public Criteria andFileMd5LikeInsensitive(String value) {
            addCriterion("upper(FILE_MD5) like", value.toUpperCase(), "fileMd5");
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