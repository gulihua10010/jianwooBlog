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
public class MessageBoardRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    /**
     * 留言主键
     */
    private Long oid;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 留言用户的qq号码
     */
    private String contactQq;

    /**
     * 留言用户的微信
     */
    private String contactWechat;

    /**
     * 留言用户的微博
     */
    private String contactWeibo;


    /**
     * 留言用户的联系手机号
     */
    private String contactTel;

    /**
     * 留言用户的邮箱
     */
    private String contactEmail;
    /**
     * 留言父id
     */
    private Long msgParentId;
    /**
     * 头像url
     */
    private String avatarSrc;

}
