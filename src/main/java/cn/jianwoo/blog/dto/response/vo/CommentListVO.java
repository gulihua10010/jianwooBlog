package cn.jianwoo.blog.dto.response.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-29 18:56
 */
public class CommentListVO implements Serializable {
    private static final long serialVersionUID = 7882315214967519501L;
    private String headImg;
    private String user;
    private String date;
    private String content;
    private Long oid;

    private List<CommentReplyListVO> replyList;


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public String getHeadImg() {
        return headImg;
    }


    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public List<CommentReplyListVO> getReplyList() {
        return replyList;
    }


    public void setReplyList(List<CommentReplyListVO> replyList) {
        this.replyList = replyList;
    }
}
