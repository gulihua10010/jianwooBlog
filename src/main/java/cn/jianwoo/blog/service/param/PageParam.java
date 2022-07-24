package cn.jianwoo.blog.service.param;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-03 10:33
 */
public class PageParam implements Serializable {
    private static final long serialVersionUID = -7420890785386799408L;
    public static final Integer DEFAULT_PAGE_NO = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 20;
    /**
     * 最大每页记录数(100).
     */
    public static final Integer MAX_PAGE_SIZE = 100;

    /**
     * 页码
     */
    private Integer pageNo = DEFAULT_PAGE_NO;

    /**
     * 每页数量
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方式(asc:升序, desc:降序)
     */
    private String sortOrder;

    public PageParam() {
    }

    public PageParam(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public Integer getPageNo() {
        return pageNo;
    }


    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public Integer getPageSize() {
        return (null != pageSize && pageSize > 0) ? pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize
                : DEFAULT_PAGE_SIZE;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
