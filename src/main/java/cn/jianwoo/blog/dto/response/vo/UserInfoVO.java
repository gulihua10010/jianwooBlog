package cn.jianwoo.blog.dto.response.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-29 18:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 7882315214967519501L;
    /**
     * 头像路径
     */
    private String avatarSrc;
    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 用户IP所在区域
     */
    private String userRegion;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户ID
     */
    private String userId;

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
     * 评论用户的邮箱
     */
    private String contactEmail;
}
