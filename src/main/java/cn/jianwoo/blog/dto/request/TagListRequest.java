package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TagListRequest extends BaseRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 标签列表集合
     */
    private List<String> tagList;

}
