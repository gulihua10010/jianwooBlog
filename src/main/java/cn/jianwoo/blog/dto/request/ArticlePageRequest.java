package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class ArticlePageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String text;
    /**
     * 文章状态(0:草稿, 1:已发布, -1:回收站 )
     */
    private Integer status;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
