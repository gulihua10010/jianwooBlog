package cn.jianwoo.blog.base;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-23 10:06
 */
public class BasePageRequestDto extends BaseRequestDto {
    private static final long serialVersionUID = -2636842269647723969L;

    private Integer page = 1;

    private Integer limit = 10;

    public Integer getPage() {
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
    }


    public Integer getLimit() {
        return limit;
    }


    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
