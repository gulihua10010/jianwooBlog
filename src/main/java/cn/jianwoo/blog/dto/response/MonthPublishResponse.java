package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.MonthPublishVO;
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
public class MonthPublishResponse extends BaseResponseDto {
    private static final long serialVersionUID = 1970975993765800809L;
    private List<MonthPublishVO> data;

    public static MonthPublishResponse getInstance() {
        return new MonthPublishResponse();
    }

}