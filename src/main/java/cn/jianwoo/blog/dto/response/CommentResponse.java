package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.CommentVO;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
public class CommentResponse extends LayuiBaseResponse {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<CommentVO> data;
    private Long count;

    public static CommentResponse getInstance() {
        return new CommentResponse();
    }


    public Long getCount() {
        return count;
    }


    public void setCount(Long count) {
        this.count = count;
    }


    public List<CommentVO> getData() {
        return data;
    }


    public void setData(List<CommentVO> data) {
        this.data = data;
    }
}
