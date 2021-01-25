package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagRequest extends BaseRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 标签文本
     */
    private String tagText;
    /**
     * 标签oid
     */
    private Long oid;

}
