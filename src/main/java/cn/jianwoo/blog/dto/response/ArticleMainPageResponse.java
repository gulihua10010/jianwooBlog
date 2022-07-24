package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.ArticleMainPageVO;
import cn.jianwoo.blog.dto.response.vo.ConditionVO;
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
public class ArticleMainPageResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    private List<ArticleMainPageVO> data;
    /**
     * 总计数量
     */
    private Long count;

    /**
     * 搜索条件1
     */
    private ConditionVO condition1;
    /**
     * 搜索条件2
     */
    private ConditionVO condition2;
    /**
     * 搜索条件3
     */
    private ConditionVO condition3;
    /**
     * 搜索条件集合
     */
    private List<ConditionVO> conditions;

    public static ArticleMainPageResponse getInstance() {
        return new ArticleMainPageResponse();
    }


}
