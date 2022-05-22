package cn.jianwoo.blog.service.bo;

import cn.jianwoo.blog.enums.ArticleAccessEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class TempArticleBO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键 article.oid [ARTICLE.OID]
     */
    private Long oid;

    /**
     * 作者 article.author [ARTICLE.AUTHOR]
     */
    private String author;

    /**
     * 标题 article.title [ARTICLE.TITLE]
     */
    private String title;

    /**
     * 文章内容 article.content [ARTICLE.CONTENT]
     */
    private String content;

    /**
     * 类别id article.typeId [ARTICLE.MENU_ID]
     */
    private Integer menuOid;

    /**
     * 是否评论 article.isComment [ARTICLE.IS_COMMENT]
     */
    private Boolean isComment;

    /**
     * 图片 article.imgSrc [ARTICLE.IMG_SRC]
     */
    private String imgSrc;

    /**
     * 访问类型 article.accessType {@link ArticleAccessEnum} [ARTICLE.ACCESS_TYPE]
     */
    private String accessType;

    /**
     * 状态article.status {@link cn.jianwoo.blog.enums.ArticleStatusEnum} [ARTICLE.STATUS]
     * 当为临时文章时, 状态tempArticle.status {@link cn.jianwoo.blog.enums.TempArticleStatusEnum} [TEMP_ARTICLE.STATUS]
     */
    private String status;

    /**
     * 文章密码 article.password [ARTICLE.PASSWORD]
     */
    private String password;

    /**
     * 文标签 tags [ARTICLE_TAGS]
     */
    private List<TagsBO> artTagsList;

    /**
     * 临时文章的OLD_ARTICLE_OID
     */
    private Long oldArticleOid;

    /**
     * temp article page
     */
    private String pageType;

    /**
     * 恢复的文章的oid
     */
    private Long restoreArticleOid;

}