package cn.jianwoo.blog.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-23 10:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BasePageRequestDto extends BaseRequestDto {
    private static final long serialVersionUID = -2636842269647723969L;

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


}
