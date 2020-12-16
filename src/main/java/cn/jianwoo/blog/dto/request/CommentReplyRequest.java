package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class CommentReplyRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    private String content;
    private Long parentOid;
    private Long artOid;
    private String headImgUrl;
    private String qq;

    public String getQq() {
        return qq;
    }


    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getHeadImgUrl() {
        return headImgUrl;
    }


    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }


    public Long getArtOid() {
        return artOid;
    }


    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }


    public Long getParentOid() {
        return parentOid;
    }


    public void setParentOid(Long parentOid) {
        this.parentOid = parentOid;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
