package cn.jianwoo.blog.service.param;

import cn.jianwoo.blog.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class ArticleParam extends PageParam {
    private static final long serialVersionUID = 7533112279053614145L;

    /**
     * 状态参数
     */
    private List<String> statusParams;
    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String text;

    /**
     * 关键词
     */
    private String keywords;


    /**
     * 发布时间(yyyy-MM-dd)
     */
    private Date publishDate;

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
     * 发布时间的开始时间
     */
    private Date publishDateStart;
    /**
     * 发布时间的结束时间
     */
    private Date publishDateEnd;

    public Date getPublishDateStart() {
        return this.publishDateStart;
    }

    public void setPublishDateStart(Date publishDateStart) {
        this.publishDateStart = publishDateStart;
    }

    public void setPublishDateStart(String publishDateStartStr) {
        this.publishDateStart = DateUtil.parseDate(publishDateStartStr);
    }

    public Date getPublishDateEnd() {
        return this.publishDateEnd;
    }

    public void setPublishDateEnd(Date publishDateEnd) {
        this.publishDateEnd = publishDateEnd;
    }

    public void setPublishDateEnd(String publishDateEndStr) {
        this.publishDateEnd = DateUtil.parseDate(publishDateEndStr);
    }


    public Date getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishDate(String publishDateStr) {
        this.publishDate = DateUtil.parseDate(publishDateStr);
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


    public void processSortField(String field, String order) {
        this.setSortOrder(order);
        if ("title".equals(field)) {
            this.setSortField("A.TITLE");
        } else if ("publishTimeDesc".equals(field)) {
            this.setSortField("A.PUSH_TIME");
        }
    }
}
