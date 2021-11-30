package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.LogSummaryVO;
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
public class LogSummaryResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<LogSummaryVO> data;
    /**
     * 查询总数量
     */
    private Long count;

    public static LogSummaryResponse getInstance() {
        return new LogSummaryResponse();
    }


}
