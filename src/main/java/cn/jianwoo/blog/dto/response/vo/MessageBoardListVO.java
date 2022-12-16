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
public class MessageBoardListVO implements Serializable {
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
     * 留言用户的邮箱
     */
    private String contactEmail;
    /**
     * 留言时间
     */
    private String pushTimeStr;
    /**
     * 留言时间
     */
    private Date pushTime;
    /**
     * 留言内容
     */
    private String content;
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
     * 主键
     */
    private Long oid;
    /**
     * 是否已经赞过
     */
    private Boolean isPraise;
    /**
     * 是否可以编辑
     */
    private Boolean flagEdit;

    /**
     * 是否是管理员发布
     */
    private Boolean flagAdmin;
    /**
     * 是否已经删除
     */
    private Boolean flagDelete;
    /**
     * 赞数量
     */
    private Long praiseCount;

    /**
     * 回复记录数
     */
    private Long replyCount;
    private List<MessageBoardListVO> replyList;

    public String getPushTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getPushTime());
    }

    public String getUserNick() {
        return this.userNick == null ? Constants.BLANK : this.userNick;
    }
}
