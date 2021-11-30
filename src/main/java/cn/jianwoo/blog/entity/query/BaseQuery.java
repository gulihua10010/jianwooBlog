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

    public String getOrderByClause() {
        if (StringUtils.isEmpty(orderByClause) && StringUtils.isNotEmpty(sortField) && StringUtils.isNotEmpty(sortOrder)) {
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
}
