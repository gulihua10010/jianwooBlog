package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class MsgSummaryVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130157L;

    /**
     * 主键
     */
    private Long oid;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息链接
     */
    private String link;
    /**
     * 是否已读
     */
    private String isRead;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 发送时间
     */
    private String sendTimeStr;

    public String getSendTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getSendTime());
    }
}