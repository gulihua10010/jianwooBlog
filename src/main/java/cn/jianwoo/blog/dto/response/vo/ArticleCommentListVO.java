package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-29 18:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class ArticleCommentListVO implements Serializable {
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
    /**
     * 评论时间
     */
    private String commentTimeStr;
    /**
     * 评论时间
     */
    private Date commentTime;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复的父评论的用户ID
     */
    private String replyToUserId;
    /**
     * 回复的父评论的用户名
     */
    private String replyToUserName;
    /**
     * 回复的父评论的用户昵称
     */
    private String replyToUserNick;
    /**
     * 主键
     */
    private Long oid;
    /**
     * 楼数
     */
    private Long floorNumber;
    /**
     * 是否已经赞过
     */
    private Boolean isPraise;
    /**
     * 是否可以编辑
     */
    private Boolean flagEdit;
    /**
     * 赞数量
     */
    private Long praiseCount;

    /**
     * 回复记录数
     */
    private Long replyCount;
    private List<ArticleCommentListVO> replyList;

    public String getCommentTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getCommentTime());
    }

    public String getUserNick() {
        return this.userNick == null ? Constants.BLANK : this.userNick;
    }
}
