package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.AnnounceSummaryVO;
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
public class AnnounceSummaryResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<AnnounceSummaryVO> data;
    /**
     * 查询总数量
     */
    private Long count;

    public static AnnounceSummaryResponse getInstance() {
        return new AnnounceSummaryResponse();
    }


}
