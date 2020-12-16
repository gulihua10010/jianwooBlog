package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class CommentPageRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    private String title;
    private Integer unread;

    public Integer getUnread() {
        return this.unread;
    }


    public void setUnread(Integer unread) {
        this.unread = unread;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
