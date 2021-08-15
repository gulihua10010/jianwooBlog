package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String text;
    /**
     * 文章状态(00:草稿, 90:已发布, 91:回收站 )
     */
    private String status;


}
