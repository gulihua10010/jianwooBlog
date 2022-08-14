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
public class CommentMainPageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;

    /**
     * 文章oid
     */
    private String artOid;
    /**
     * 评论父oid
     */
    private Long parentOid;
    /**
     * 回复评论根oid
     */
    private Long replyRootOid;

    /**
     * 评论oid, 用于消息跳转上下文
     */
    private Long refOid;
    /**
     * 当前ip
     */
    private String currentIp;
    /**
     * 排序方式(10:热度排序,20:最新排序)
     */
    private String orderWay;


}
