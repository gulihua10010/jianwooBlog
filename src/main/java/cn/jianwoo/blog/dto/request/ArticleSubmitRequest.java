package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-26 9:28
 */
public class ArticleSubmitRequest extends BaseRequestDto {
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
     * 文章标签
     */
    private Integer[] tags;

    /**
     * 文章类别
     */
    private Integer type;

    /**
     * 文章缩略图
     */
    private String imgSrc;

    /**
     * 访问类型, -1:密码, 0:私密, 1:公共, 2:置顶
     */
    private Integer visitType;

    /**
     * 密码 访问类型为-1时有效
     */
    private String password;

    /**
     * 文章是否可以评论,1:可以, 0:不可以
     */
    private Integer isComment;

    /**
     * 文章oid主键
     */
    private Long artOid;


    public Long getArtOid() {
        return artOid;
    }


    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getArticleContent() {
        return articleContent;
    }


    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }


    public Integer[] getTags() {
        return tags;
    }


    public void setTags(Integer[] tags) {
        this.tags = tags;
    }


    public Integer getType() {
        return type;
    }


    public void setType(Integer type) {
        this.type = type;
    }


    public String getImgSrc() {
        return imgSrc;
    }


    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }


    public Integer getVisitType() {
        return visitType;
    }


    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getIsComment() {
        return isComment;
    }


    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }
}
