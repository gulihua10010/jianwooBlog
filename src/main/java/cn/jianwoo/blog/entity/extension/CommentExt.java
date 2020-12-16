package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Comment;

import java.util.List;

/**
 * @author gulihua
 */
public class CommentExt extends Comment {

    private static final long serialVersionUID = 3237287365279587845L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 回复给用户名
     */
    private String parentUserName;

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


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
