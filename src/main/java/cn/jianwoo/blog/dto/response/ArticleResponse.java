package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.dto.response.vo.ArticleVO;
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
public class ArticleResponse extends LayuiBaseResponse {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<ArticleVO> data;
    /**
     * 总计数量
     */
    private Long count;

    public static ArticleResponse getInstance() {
        return new ArticleResponse();
    }


}
