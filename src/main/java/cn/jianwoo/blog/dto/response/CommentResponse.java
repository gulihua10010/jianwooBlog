package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.CommentVO;
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
public class CommentResponse extends LayuiBaseResponse {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<CommentVO> data;
    /**
     * 查询总数量
     */
    private Long count;

    public static CommentResponse getInstance() {
        return new CommentResponse();
    }


}
