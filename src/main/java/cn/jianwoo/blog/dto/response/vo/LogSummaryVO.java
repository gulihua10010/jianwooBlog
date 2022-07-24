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
 * @date 2020-11-02 17:58
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class LogSummaryVO implements Serializable {
    private static final long serialVersionUID = -8183712030536923737L;

    /**
     * 登录ID
     */
    private String loginId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 操作类型
     */
    private String optType;

    /**
     * 实体oid
     */
    private String optEntityOid;

    /**
     * 实体描述
     */
    private String optEntityDesc;

    /**
     * 触发时间
     */
    private Date triggerTime;

    /**
     * 触发ip
     */
    private String triggerIp;

    /**
     * 触发地区
     */
    private String triggerArea;

    /**
     * 处理状态(描述)
     */
    private String processStatusStr;
    /**
     * 处理状态(代码)
     */
    private String processStatus;

    /**
     * 失败原因
     */
    private String failedReason;

    public String getTriggerTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getTriggerTime());
    }

}
