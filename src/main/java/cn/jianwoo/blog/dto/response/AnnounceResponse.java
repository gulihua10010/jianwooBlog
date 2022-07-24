package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.AnnounceVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AnnounceResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private AnnounceVO data;

    public static AnnounceResponse getInstance() {
        return new AnnounceResponse();
    }


}
