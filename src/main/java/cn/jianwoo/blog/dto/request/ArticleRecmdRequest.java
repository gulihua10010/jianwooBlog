package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import cn.jianwoo.blog.enums.ArtRecmdTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-25 15:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ArticleRecmdRequest extends BaseRequestDto {

    private static final long serialVersionUID = -9159381492520980551L;

    /**
     * 查询类型<br>
     * 10:最热文章(默认)<br>
     * 20:最新文章<br>
     * 30:随机文章<br>
     * {@link ArtRecmdTypeEnum}<br>
     */
    private String type;

}
