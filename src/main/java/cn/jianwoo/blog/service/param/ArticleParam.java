package cn.jianwoo.blog.service.param;

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
     * 标签
     */
    private List<Integer> tags;

    /**
     * 类型
     */
    private Integer category;

    public List<Integer> getTags() {
        return this.tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public Integer getCategory() {
        return this.category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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
