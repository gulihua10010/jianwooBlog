package cn.jianwoo.blog.entity.query;

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

    public String getIsRead() {
        return this.isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
