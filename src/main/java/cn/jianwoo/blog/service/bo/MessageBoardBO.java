package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 16:39
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class MessageBoardBO implements Serializable {
    private static final long serialVersionUID = 2157217103012138770L;


    /**
     * 主键OID
     */
    private Long oid;

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
     * IP地址
     */
    private String clientIp;

    /**
     * 地域
     */
    private String userRegion;

    /**
     * 留言时间
     */
    private Date pushTime;

    /**
     * 父oid
     */
    private Long parentOid;

    /**
     * 赞数量
     */
    private Long praiseCount;

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
     * 头像路径
     */
    private String avatarSrc;

    /**
     * 已读状态
     */
    private String readStatus;
    /**
     * 留言内容
     */
    private String content;

    /**
     * 回复给用户名
     */
    private String parentUserName;

    /**
     * 回复list
     */
    private List<MessageBoardBO> replyMessages;

    /**
     * 是否已经赞过
     */
    private Boolean isPraise;

    /**
     * 回复记录数
     */
    private Long replyCount;


    /**
     * 回复的父留言的用户ID
     */
    private String replyToUserId;

    /**
     * 回复的父留言的用户名
     */
    private String replyToUserName;

    /**
     * 回复的父留言的用户昵称
     */
    private String replyToUserNick;

    /**
     * 是否可以编辑
     */
    private Boolean flagEdit;

    /**
     * 留言是否删除
     */
    private Boolean isDelete;


}
