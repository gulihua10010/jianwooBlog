package cn.jianwoo.blog.entity.query;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class CommentPageQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 7533112279053614145L;
    /**
     * 文章oid
     */
    private Long artOid;
    /**
     * 评论父oid
     */
    private Long parentOid;
    /**
     * 回复评论根oid
     */
    private Long replyRootOid;
    /**
     * 当前ip
     */
    private String currentIp;

    /**
     * 排序方式(10:热度排序,20:最新排序)
     */
    private String orderWay;

    public String getOrderWay() {
        return this.orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    public Long getArtOid() {
        return this.artOid;
    }

    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }

    public Long getParentOid() {
        return this.parentOid;
    }

    public void setParentOid(Long parentOid) {
        this.parentOid = parentOid;
    }

    public Long getReplyRootOid() {
        return this.replyRootOid;
    }

    public void setReplyRootOid(Long replyRootOid) {
        this.replyRootOid = replyRootOid;
    }

    public String getCurrentIp() {
        return this.currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }
}
