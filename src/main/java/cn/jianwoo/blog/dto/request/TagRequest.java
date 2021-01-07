package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class TagRequest extends BaseRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    private String tagText;
    private Long oid;

    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getTagText() {
        return tagText;
    }


    public void setTagText(String tagText) {
        this.tagText = tagText;
    }
}
