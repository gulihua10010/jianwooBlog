package cn.jianwoo.blog.entity.extension;

import cn.jianwoo.blog.entity.Comment;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-29 15:11
 */
public class ReplyCommentsExt extends Comment {
    private static final long serialVersionUID = -4959554133227060512L;

    /**
     * 回复给用户名
     */
    private String parentUserName;

    public String getParentUserName() {
        return parentUserName;
    }


    public void setParentUserName(String parentUserName) {
        this.parentUserName = parentUserName;
    }
}
