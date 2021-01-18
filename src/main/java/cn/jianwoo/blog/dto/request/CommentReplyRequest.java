package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 11:40
 */
public class CommentReplyRequest extends BasePageRequestDto {
    private static final long serialVersionUID = 5162232950590602831L;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论父oid
     */
    private Long parentOid;
    /**
     * 文章oid
     */
    private Long artOid;
    /**
     * 头像url
     */
    private String headImgUrl;
    /**
     * qq
     */
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
