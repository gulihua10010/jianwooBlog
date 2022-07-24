package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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
     * 关键词
     */
    private String keywords;
    /**
     * 文章状态(00:草稿, 90:已发布, 91:回收站 )
     */
    private String status;
    /**
     * 发布时间(yyyy-MM-dd)
     */
    private String publishDate;

    /**
     * 标签集合
     */
    private List<Integer> tags;

    /**
     * 标签
     */
    private Integer tag;

    /**
     * 类型1
     */
    private Integer category1;
    /**
     * 类型2
     */
    private Integer category2;

    /**
     * 发布时间的开始时间
     */
    private String publishDateStart;
    /**
     * 发布时间的结束时间
     */
    private String publishDateEnd;


}
