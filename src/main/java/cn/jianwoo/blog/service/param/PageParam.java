package cn.jianwoo.blog.service.param;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-03 10:33
 */
public class PageParam implements Serializable {
    private static final long serialVersionUID = -7420890785386799408L;
    private static final Integer DEFAULT_PAGE_NO = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer pageNo = DEFAULT_PAGE_NO;
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    public Integer getPageNo() {
        return pageNo;
    }


    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public Integer getPageSize() {
        return pageSize;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
