package cn.jianwoo.blog.service.bo;

import cn.jianwoo.blog.enums.ArticleAccessEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 类别id article.typeId [ARTICLE.MENU_ID]
     */
    private Integer menuId;

    /**
     * 类别名称
     */
    private String typeName;

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
     * 菜单列表
     */
    private List<ArticleMenuBO> menuList;
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



}