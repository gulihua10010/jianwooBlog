package cn.jianwoo.blog.service.bo;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.enums.ArticleAccessEnum;
import cn.jianwoo.blog.enums.TopPlaceEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode()
@NoArgsConstructor
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
     * 文章类别id article.categoryId [ARTICLE.CATEGORY_ID]
     */
    private Integer categoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 上级文章类型
     */
    private String parentCategory;
    /**
     * 上级类别id
     */
    private Integer parentCategoryId;

    /**
     * 是否评论 article.isComment [ARTICLE.IS_COMMENT]
     */
    private Boolean isComment;

    /**
     * 是否原创 article.flagOriginal [ARTICLE.FLAG_ORIGINAL]
     */
    private Boolean flagOriginal;


    /**
     * 转载源链接 article.originalUrl [ARTICLE.ORIGINAL_URL]
     */
    private String originalUrl;

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
    private List<Integer> tagOidList;

    /**
     * 临时文章oid主键
     */
    private Long tempArtOid;

    /**
     * 发布时间
     */
    private Date pushTime;

    /**
     * 文章发布的IP地址
     */
    private String pushIp;

    /**
     * 文章发布的地区
     */
    private String pushRegion;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 阅读次数
     */
    private Long readCount;
    /**
     * 赞的数量
     */
    private Long praiseCount;
    /**
     * 文章内容纯文本
     */
    private String text;
    /**
     * 评论数量
     */
    private Long commentCount;
    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 文章标签列表
     */
    private List<TagsBO> artTagsList;
    /**
     * 标签列表
     */
    private List<TagsBO> allTagsList;

    /**
     * 文章类别列表
     */
    private List<ArticleCategoryBO> categoryList;
    /**
     * 菜单名字
     */
    private String menuName;

    /**
     * 临时文章数据
     */
    private TempArticleBO tempArticle;


    /**
     * 回收站删除时间
     */
    private Date removeRecycleTime;

    /**
     * 当前IP是否已经赞过
     */
    private Boolean isPraise;

    /**
     * 是否置顶
     */
    private Boolean flagTop;

    /**
     * 置顶状态,00:未置顶,50:置顶{@link cn.jianwoo.blog.enums.TopPlaceEnum}
     */
    private String topPlaceStatus;

    /**
     * 总楼数
     */
    private Long totalCommentFloors;

    /**
     * 文章是否是锁的状态(需要密码才能访问)
     */
    private Boolean isLock;

    public void setCategoryId(Integer categoryId) {
        if (!Constants.CATEGORY_NULL.equals(categoryId)) {
            this.categoryId = categoryId;
        }
    }

    public void setFlagTop(Boolean flagTop) {
        if (flagTop == null) {
            this.flagTop = false;
        } else {
            this.flagTop = flagTop;
        }
        this.topPlaceStatus = this.flagTop ? TopPlaceEnum.TOP.getValue() : TopPlaceEnum.NOT_TOP.getValue();
    }
}