package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class ArticleBO implements Serializable {
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
     * 类别id article.typeId [ARTICLE.TYPE_ID]
     */
    private Integer typeId;

    /**
     * 是否评论 article.isComment [ARTICLE.IS_COMMENT]
     */
    private Integer isComment;

    /**
     * 图片 article.imgSrc [ARTICLE.IMG_SRC]
     */
    private String imgSrc;

    /**
     * 访问类型 article.visitType {@link cn.jianwoo.blog.enums.ArticleVisitEnum} [ARTICLE.VISIT_TYPE]
     */
    private Integer visitType;

    /**
     * 状态article.status {@link cn.jianwoo.blog.enums.ArticleStatusEnum} [ARTICLE.STATUS]
     * 当为临时文章时, 状态tempArticle.status {@link cn.jianwoo.blog.enums.TempArticleStatusEnum} [TEMP_ARTICLE.STATUS]
     */
    private Integer status;

    /**
     * 文章密码 article.password [ARTICLE.PASSWORD]
     */
    private String password;

    /**
     * 文标签 tags [ARTICLE_TAGS]
     */
    private Integer[] tags;

    /**
     *  临时文章的OLD_OID
     */
    private Long oldOid;

    /**
     * temp article page
     */
    private Integer page;

}