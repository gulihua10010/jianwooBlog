package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 16:05
 */
public class WebconfRequest extends BasePageRequestDto {
    private static final long serialVersionUID = -8260719513799536531L;
    private String title;
    private String author;
    private String keywords;
    private String description;
    private String record;
    private String domain;
    private String footHtml;
    private String logoImg;
    private String homeImg;
    private Integer numPerPage;
    private Boolean isComment;

    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return this.author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getKeywords() {
        return this.keywords;
    }


    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }


    public String getDescription() {
        return this.description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getRecord() {
        return this.record;
    }


    public void setRecord(String record) {
        this.record = record;
    }


    public String getDomain() {
        return this.domain;
    }


    public void setDomain(String domain) {
        this.domain = domain;
    }


    public String getFootHtml() {
        return this.footHtml;
    }


    public void setFootHtml(String footHtml) {
        this.footHtml = footHtml;
    }


    public String getLogoImg() {
        return this.logoImg;
    }


    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }


    public String getHomeImg() {
        return this.homeImg;
    }


    public void setHomeImg(String homeImg) {
        this.homeImg = homeImg;
    }


    public Integer getNumPerPage() {
        return this.numPerPage;
    }


    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }


    public Boolean getIsComment() {
        return this.isComment;
    }


    public void setIsComment(Boolean comment) {
        this.isComment = comment;
    }
}
