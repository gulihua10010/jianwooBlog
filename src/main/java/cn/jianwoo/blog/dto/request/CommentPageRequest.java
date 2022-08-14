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
public class CommentPageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 是否已读
     */
    private Integer unread;
    /**
     * 文章主键
     */
    private String  artOid;

    /**
     * 主键, 用于消息跳转
     */
    private Long oid;


}
