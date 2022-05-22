package cn.jianwoo.blog.entity;

import java.io.Serializable;

public class MessageProfileWithBLOBs extends MessageProfile implements Serializable {
    private byte[] msgContent;

    private byte[] bizParam;

    private static final long serialVersionUID = 1L;

    public byte[] getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }

    public byte[] getBizParam() {
        return bizParam;
    }

    public void setBizParam(byte[] bizParam) {
        this.bizParam = bizParam;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", msgContent=").append(msgContent);
        sb.append(", bizParam=").append(bizParam);
        sb.append("]");
        return sb.toString();
    }
}