package cn.jianwoo.blog.dto.response.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130154L;

    /**
     * seq
     */
    private Integer seq;
    /**
     * 文章标题
     */
    private String artTitle;


    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 评论日期
     */
    private Date commentTime;
    /**
     * 评论日期 格式:yyyy-MM-dd HH:mm:ss
     */
    private String commentTimeStr;
    /**
     * 回复给的用户
     */
    private String replyTo;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 要回复评论的oid
     */
    private Long replyOid;
    /**
     * 评论oid
     */
    private Long oid;
    /**
     * 文章oid
     */
    private Long artOid;
    /**
     * IP地址
     */
    private String clientIp;

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
     * 头像
     */
    private String avatarSrc;
    /**
     * ip所属地域
     */
    private String userArea;
    /**
     * 是否以读
     */
    private String readStatus;


}