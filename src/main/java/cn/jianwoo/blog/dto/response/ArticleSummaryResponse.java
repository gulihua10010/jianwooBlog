package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.ArticleSummaryVO;
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
public class ArticleSummaryResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<ArticleSummaryVO> data;
    /**
     * 总计数量
     */
    private Long count;

    public static ArticleSummaryResponse getInstance() {
        return new ArticleSummaryResponse();
    }


}
