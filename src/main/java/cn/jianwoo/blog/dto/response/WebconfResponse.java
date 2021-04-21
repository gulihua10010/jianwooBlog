package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.WebconfVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebconfResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800810L;
    private List<WebconfVO> data;

    public static WebconfResponse getInstance() {
        return new WebconfResponse();
    }
}