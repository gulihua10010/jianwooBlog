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
public class MessageBoardVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130154L;

    /**
     * seq
     */
    private Integer seq;


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
     * 留言日期
     */
    private Date pushTime;
    /**
     * 留言日期 格式:yyyy-MM-dd HH:mm:ss
     */
    private String pushTimeStr;
    /**
     * 回复给的用户
     */
    private String replyTo;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 要回复留言的oid
     */
    private Long replyOid;
    /**
     * 留言oid
     */
    private Long oid;
    /**
     * IP地址
     */
    private String clientIp;

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
     * 头像
     */
    private String avatarSrc;
    /**
     * ip所属地域
     */
    private String userRegion;
    /**
     * 是否以读
     */
    private String readStatus;


}