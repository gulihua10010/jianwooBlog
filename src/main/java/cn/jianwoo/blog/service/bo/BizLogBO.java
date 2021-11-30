package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-31 16:08
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class BizLogBO {

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
     * 处理状态
     */
    private String processStatus;

    /**
     * 失败原因
     */
    private String failedReason;

}
