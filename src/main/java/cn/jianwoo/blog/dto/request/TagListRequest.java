package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class TagListRequest extends BaseRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    private List<String> tagList;

    public List<String> getTagList() {
        return this.tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
