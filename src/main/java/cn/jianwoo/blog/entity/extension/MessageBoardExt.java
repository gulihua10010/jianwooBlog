package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.MessageBoard;

import java.util.List;

/**
 * @author gulihua
 */
public class MessageBoardExt extends MessageBoard {

    private static final long serialVersionUID = 3237287365279587845L;

    /**
     * 回复给用户名
     */
    private String parentUserName;

    /**
     * 业务赞表的主键
     */
    private Long praiseOid;

    /**
     * 回复list
     */
    private List<ReplyCommentsExt> replyComments;

    public String getParentUserName() {
        return parentUserName;
    }


    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }


    public List<ReplyCommentsExt> getReplyComments() {
        return replyComments;
    }


    public void setReplyComments(List<ReplyCommentsExt> replyComments) {
        this.replyComments = replyComments;
    }



    public Long getPraiseOid() {
        return this.praiseOid;
    }

    public void setPraiseOid(Long praiseOid) {
        this.praiseOid = praiseOid;
    }
}
