package cn.jianwoo.blog.service.bo;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:53
 */
public class WebconfBO implements Serializable {
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


    public String getKeywords() {
        return keywords;
    }


    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getRecord() {
        return record;
    }


    public void setRecord(String record) {
        this.record = record;
    }


    public String getDomain() {
        return domain;
    }


    public void setDomain(String domain) {
        this.domain = domain;
    }


    public String getFootHtml() {
        return footHtml;
    }


    public void setFootHtml(String footHtml) {
        this.footHtml = footHtml;
    }


    public String getLogoImg() {
        return logoImg;
    }


    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }


    public String getHomeImg() {
        return homeImg;
    }


    public void setHomeImg(String homeImg) {
        this.homeImg = homeImg;
    }


    public Integer getNumPerPage() {
        return numPerPage;
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
