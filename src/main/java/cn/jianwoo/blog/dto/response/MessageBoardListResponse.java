package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.MessageBoardListVO;
import cn.jianwoo.blog.dto.response.vo.UserInfoVO;
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
public class MessageBoardListResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    /**
     * 当前IP用户信息
     */
    private UserInfoVO userInfo;
    /**
     * 留言集合
     */
    private List<MessageBoardListVO> messageList;
    /**
     * 总记录数
     */
    private Long count;
    /**
     * 根留言记录数
     */
    private Long rootMsgCount;

    public static MessageBoardListResponse getInstance() {
        return new MessageBoardListResponse();
    }


}
