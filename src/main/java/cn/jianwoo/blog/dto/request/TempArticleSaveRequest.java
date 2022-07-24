package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import cn.jianwoo.blog.dto.request.vo.TagsVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-26 9:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TempArticleSaveRequest extends BaseRequestDto {
    private static final long serialVersionUID = 4249657870459225102L;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章内容
     */
    private String articleContent;


    /**
     * 类别id
     */
    private Integer categoryId;

    /**
     * 文章缩略图
     */
    private String imgSrc;

    /**
     * 访问类型, 11:密码, 10:私密, 20:公共, 21:置顶
     */
    private String accessType;

    /**
     * 密码 访问类型为-1时有效
     */
    private String password;

    /**
     * 文章是否可以评论,1:可以, 0:不可以
     */
    private Boolean isComment;

    /**
     * 文章oid主键
     */
    private Long oid;

    /**
     * temp article oldArticleOid
     */
    private Long oldArticleOid;

    /**
     * temp article page
     */
    private String pageType;

    /**
     * temp article tags信息
     */
    private List<TagsVO> tags;


    /**
     * 是否置顶
     */
    private Boolean topPlaceFlag;

    /**
     * 是否原创
     */
    private Boolean flagOriginal;

    /**
     * 转载源链接
     */
    private String originalUrl;
}
