package cn.jianwoo.blog.service.param;

import cn.jianwoo.blog.enums.OrderWayEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 18:39
 */
public class MessageBoardMainParam extends PageParam {
    private static final long serialVersionUID = 7533112279053614145L;

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    /**
     * 评论父oid
     */
    private Long parentOid;
    /**
     * 回复评论根oid
     */
    private Long replyRootOid;
    /**
     * 评论oid, 用于消息跳转上下文
     */
    private Long refOid;
    /**
     * 当前ip
     */
    private String currentIp;


    /**
     * 排序方式(10:热度排序,20:最新排序)
     */
    private String orderWay;

    /**
     * 页码
     */
    private Integer replyPageNo = DEFAULT_PAGE_NO;

    /**
     * 每页数量
     */
    private Integer replyPageSize = DEFAULT_PAGE_SIZE;

    public String getOrderWay() {
        return this.orderWay;
    }

    public void setOrderWay(String orderWay) {
        if (StringUtils.isNotBlank(orderWay)) {
            this.orderWay = orderWay;
        } else {
            this.orderWay = OrderWayEnum.HOT.getValue();

        }
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

    public Long getRefOid() {
        return this.refOid;
    }

    public void setRefOid(Long refOid) {
        this.refOid = refOid;
    }

    public String getCurrentIp() {
        return this.currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }

    public Integer getReplyPageNo() {
        return this.replyPageNo;
    }

    public void setReplyPageNo(Integer replyPageNo) {
        this.replyPageNo = replyPageNo;
    }

    public Integer getReplyPageSize() {
        return (null != replyPageSize && replyPageSize > 0) ? replyPageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : replyPageSize
                : DEFAULT_PAGE_SIZE;
    }

    public void setReplyPageSize(Integer replyPageSize) {
        this.replyPageSize = replyPageSize;
    }
}