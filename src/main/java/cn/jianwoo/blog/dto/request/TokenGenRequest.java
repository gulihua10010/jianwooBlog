package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:15
 */
public class TokenGenRequest extends BaseRequestDto {
    private static final long serialVersionUID = -7682284534958110646L;
    /**
     * 页面ID {@link cn.jianwoo.blog.enums.PageIdEnum}
     */
    private String pageId;

    public String getPageId() {
        return this.pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
