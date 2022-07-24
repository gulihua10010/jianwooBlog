package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.ArticleMainPageVO;
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
public class ArticleMainRecommendResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    /**
     * 最新文章
     */
    private List<ArticleMainPageVO> newestList;
    /**
     * 随机推荐文章
     */
    private List<ArticleMainPageVO> randomList;
    /**
     * 最热门的文章
     */
    private List<ArticleMainPageVO> hotList;

    public static ArticleMainRecommendResponse getInstance() {
        return new ArticleMainRecommendResponse();
    }


}
