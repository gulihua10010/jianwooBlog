package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.CommentListVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponse extends LayuiBaseResponse {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<CommentListVO> commentList;

    public static CommentListResponse getInstance() {
        return new CommentListResponse();
    }


}
