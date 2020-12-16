package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.CommentListVO;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
public class CommentListResponse extends LayuiBaseResponse {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<CommentListVO> commentList;

    public static CommentListResponse getInstance() {
        return new CommentListResponse();
    }


    public List<CommentListVO> getCommentList() {
        return commentList;
    }


    public void setCommentList(List<CommentListVO> commentList) {
        this.commentList = commentList;
    }
}
