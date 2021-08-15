package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.EmailTplVO;
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
public class EmailTplSummaryResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<EmailTplVO> data;
    /**
     * 查询总数量
     */
    private Long count;

    public static EmailTplSummaryResponse getInstance() {
        return new EmailTplSummaryResponse();
    }


}
