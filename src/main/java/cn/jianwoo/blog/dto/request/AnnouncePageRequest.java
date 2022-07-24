package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
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
public class AnnouncePageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 公告标题
     */
    private String title;

    /**
     * 状态: 00:有效,91:无效
     */
    private String status;


}
