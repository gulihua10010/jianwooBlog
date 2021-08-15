package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:00
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentAddRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    /**
     * 评论内容
     */
    private String commentText;
    /**
     * 用户名
     */
    private String username;

    /**
     * 评论用户的qq号码
     */
    private String contactQq;

    /**
     * 评论用户的微信
     */
    private String contactWechat;

    /**
     * 评论用户的微博
     */
    private String contactWeibo;

    /**
     * 评论用户的联系手机号
     */
    private String contactTel;

    /**
     * 文章oid
     */
    private Long artId;
    /**
     * 评论父id
     */
    private Long commentParentId;
    /**
     * 头像url
     */
    private String headImgUrl;

}
