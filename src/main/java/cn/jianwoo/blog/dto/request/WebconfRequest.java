package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
import cn.jianwoo.blog.base.BaseRequestDto;
import cn.jianwoo.blog.dto.response.vo.WebconfVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 16:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebconfRequest extends BaseRequestDto {
    private static final long serialVersionUID = -8260719513799536531L;
    private List<WebconfVO> list;

}