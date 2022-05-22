package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
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
public class CommentReplyRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论父oid
     */
    private Long parentOid;
    /**
     * 文章oid
     */
    private Long artOid;
    /**
     * 头像url
     */
    private String avatarSrc;
    /**
     * qq
     */
    private String contactQq;

    /**
     * 用户名
     */
    private String userName;

}
