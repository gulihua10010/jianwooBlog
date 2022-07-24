package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.CommentVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2021-03-12 10:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CommentInfoResponse extends BaseResponseDto {
    private static final long serialVersionUID = -1241770965013392991L;
    private CommentVO data;

    public CommentVO getData() {
        return this.data;
    }

    public void setData(CommentVO data) {
        this.data = data;
    }

    public static CommentInfoResponse getInstance() {
        return new CommentInfoResponse();
    }

}
