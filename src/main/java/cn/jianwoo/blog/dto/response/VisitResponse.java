package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.VisitVO;
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
public class VisitResponse extends LayuiBaseResponse {
    private static final long serialVersionUID = 1970975993765800809L;
    private List<VisitVO> data;
    /**
     * 查询总数量
     */
    private Long count;

    public static VisitResponse getInstance() {
        return new VisitResponse();
    }

}