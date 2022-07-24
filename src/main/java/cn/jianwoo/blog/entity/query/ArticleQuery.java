package cn.jianwoo.blog.entity.query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class ArticleQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 7533112279053614145L;
    private List<String> statusParams;
    private String title;
    private String text;

    /**
     * 关键词
     */
    private String keywords;


    /**
     * 发布的范围起始时间
     */
    private Date publishDateStart;
    /**
     * 发布的范围结束时间
     */
    private Date publishDateEnd;

    /**
     * 标签
     */
    private List<Integer> tags;

    /**
     * 类型1
     */
    private Integer category1;
    /**
     * 类型2
     */
    private Integer category2;

    /**
     * 类型集合
     */
    private List<Integer> categorys;

    public Date getPublishDateStart() {
        return this.publishDateStart;
    }

    public void setPublishDateStart(Date publishDateStart) {
        this.publishDateStart = publishDateStart;
    }

    public Date getPublishDateEnd() {
        return this.publishDateEnd;
    }

    public void setPublishDateEnd(Date publishDateEnd) {
        this.publishDateEnd = publishDateEnd;
    }

    public List<Integer> getCategorys() {
        return this.categorys;
    }

    public void setCategorys(List<Integer> categorys) {
        this.categorys = categorys;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Integer> getTags() {
        return this.tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public Integer getCategory1() {
        return this.category1;
    }

    public void setCategory1(Integer category1) {
        this.category1 = category1;
    }

    public Integer getCategory2() {
        return this.category2;
    }

    public void setCategory2(Integer category2) {
        this.category2 = category2;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public List<String> getStatusParams() {
        return statusParams;
    }


    public void setStatusParams(List<String> statusParams) {
        this.statusParams = statusParams;
    }
}
