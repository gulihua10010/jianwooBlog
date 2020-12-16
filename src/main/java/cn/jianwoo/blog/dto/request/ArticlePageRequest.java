package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class ArticlePageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    private String title;
    private String text;

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
