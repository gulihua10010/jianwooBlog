package cn.jianwoo.blog.entity.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-09-03 15:10
 */
public class BaseQuery implements Serializable {
    private static final long serialVersionUID = -7388808955410999440L;
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页数量
     */
    private Integer limit = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方式(asc:升序, desc:降序)
     */
    private String sortOrder;

    /**
     * 排序规则
     */
    private String orderByClause;
    /**
     * 最大每页记录数(100).
     */
    public static final Integer MAX_PAGE_SIZE = 100;
    /**
     * 默认每页记录数(20).
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    private Integer start;
    private Integer rows;
    /**
     * 是否限制记录数, 默认限制
     */
    private Boolean isLimit = true;

    public BaseQuery() {
    }

    public String getOrderByClause() {
        if (StringUtils.isEmpty(orderByClause) && StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
            if (!"asc".equalsIgnoreCase(sortOrder) && !"desc".equalsIgnoreCase(sortOrder)) {
                sortOrder = "ASC";
            }
            orderByClause = sortField + " " + sortOrder;
        }
        // 暂时不设置默认值
        return StringUtils.isEmpty(orderByClause) ? null : orderByClause;
    }


    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getSortField() {
        return this.sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return (null != limit && limit > 0) ? limit > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : limit
                : DEFAULT_PAGE_SIZE;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        if (null == start || 0 == start) {
            // 如果 start 没有被传值 或者 值为0,则通过 页码计算
            return (this.getPage() - 1) * this.getLimit();
        } else {
            return start;
        }
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        if (null == rows || 0 == rows) {
            // 如果 rows 没有被传值或值为0,则取另一个变量
            return this.getLimit();
        } else {
            return rows;
        }
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Boolean getIsLimit() {
        return this.isLimit;
    }

    public void setIsLimit(Boolean limit) {
        this.isLimit = limit;
    }
}
