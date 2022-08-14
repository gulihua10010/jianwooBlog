package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.MessageBoardVO;
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
public class MessageBoardInfoResponse extends BaseResponseDto {
    private static final long serialVersionUID = -1241770965013392991L;
    private MessageBoardVO data;

    public MessageBoardVO getData() {
        return this.data;
    }

    public void setData(MessageBoardVO data) {
        this.data = data;
    }

    public static MessageBoardInfoResponse getInstance() {
        return new MessageBoardInfoResponse();
    }

}
