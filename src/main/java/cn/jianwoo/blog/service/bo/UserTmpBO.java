package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 21:51
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class UserTmpBO implements Serializable {



    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户所属地区
     */
    private String userRegion;

    /**
     * 用户头像
     */
    private String avatarSrc;

    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * QQ
     */
    private String qq;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 微博号
     */
    private String weibo;
    /**
     * 电话
     */
    private String tel;
    /**
     * 电子邮箱
     */
    private String email;


}
