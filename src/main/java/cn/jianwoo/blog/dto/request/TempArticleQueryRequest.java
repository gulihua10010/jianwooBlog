package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TempArticleQueryRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7682284534958110646L;
    /**
     * 文章编辑时该文章的oid
     */
    private Long editArtOid;


    /**
     * 页面类型 10：文章发布 20：文章编辑
     */
    private String pageType;
}
