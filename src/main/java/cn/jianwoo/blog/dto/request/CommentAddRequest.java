package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:00
 */
public class CommentAddRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    private String commentText;
    private String username;
    private String qq;
    private Long artId;
    private Long commentParentId;
    private String headImgUrl;

    public String getCommentText() {
        return commentText;
    }


    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getQq() {
        return qq;
    }


    public void setQq(String qq) {
        this.qq = qq;
    }


    public Long getArtId() {
        return artId;
    }


    public void setArtId(Long artId) {
        this.artId = artId;
    }


    public Long getCommentParentId() {
        return commentParentId;
    }


    public void setCommentParentId(Long commentParentId) {
        this.commentParentId = commentParentId;
    }


    public String getHeadImgUrl() {
        return headImgUrl;
    }


    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
