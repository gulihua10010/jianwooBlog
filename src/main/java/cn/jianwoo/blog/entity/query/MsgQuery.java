package cn.jianwoo.blog.entity.query;

import cn.jianwoo.blog.enums.ReceiverTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class MsgQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 2478443514263410456L;

    /**
     * 是否已读
     */
    private String isRead;

    /**
     * 接收人
     */
    private String receiverId;
    /**
     * 接收类型({@link ReceiverTypeEnum})
     */

    private String receiverType;

    public String getReceiverType() {
        return this.receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getIsRead() {
        return this.isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
