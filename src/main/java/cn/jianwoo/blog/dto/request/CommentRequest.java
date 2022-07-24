package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
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
public class CommentRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    /**
     * 评论主键
     */
    private Long oid;
    /**
     * 评论内容
     */
    private String commentText;
    /**
     * 用户昵称
     */
    private String userNick;

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
     * 评论用户的邮箱
     */
    private String contactEmail;

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
    private String avatarSrc;

}
