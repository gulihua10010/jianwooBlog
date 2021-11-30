package cn.jianwoo.blog.event;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.filter.JwAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description 登录事件
 * @date 2021-08-24 10:58
 */
@Slf4j
public class BizEventLogEvent extends ApplicationEvent {
    /**
     * 用户名
     */
    private String username;
    /**
     * entity oid
     */
    private Long oid;
    /**
     * request
     */
    private HttpServletRequest request;

    /**
     * 描述
     */
    private String desc;
    /**
     * 登录失败原因
     */
    private String reason;
    /**
     * 是否成功
     */
    private Boolean isSuccess = true;

    /**
     * 事件类型
     */
    private BizEventTypeEnum bizEventTypeEnum;
    /**
     * 事件操作类型
     */
    private BizEventOptTypeEnum bizEventOptTypeEnum;

    private SecurityContext context;

    private String ip;


    public BizEventLogEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public BizEventLogEvent(Object source, String username, HttpServletRequest request) {
        super(source);
        this.username = username;
        this.request = request;
    }


    public BizEventLogEvent(Object source, SecurityContext context) {
        super(source);
        this.context = context;
    }


    public Long getOid() {
        return this.oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public SecurityContext getContext() {
        return this.context;
    }

    public void setContext(SecurityContext context) {
        this.context = context;
    }

    public static Logger getLog() {
        return BizEventLogEvent.log;
    }

    public String getIp() {
        if (StringUtils.isNotBlank(this.ip)) {
            return this.ip;
        }
        if (null != this.context) {
            Authentication authentication = this.context.getAuthentication();
            JwAuthenticationToken jwAuthenticationToken = (JwAuthenticationToken) authentication;
            if (null != jwAuthenticationToken.getParam()) {
                return (String) jwAuthenticationToken.getParam().get(Constants.LOGIN_IP);
            }
        }
        return null;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        if (StringUtils.isNotBlank(this.username)) {
            return this.username;
        }
        if (null != this.context) {
            Authentication authentication = this.context.getAuthentication();
            return (String) authentication.getPrincipal();
        }
        return Constants.ADMIN;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getDesc() {
        if (StringUtils.isNotBlank(desc)) {
            if (desc.length() > 50) {
                return desc.substring(0, 50) + Constants.ELLIPSIS;
            }
            return this.desc;
        }
        return String.valueOf(this.oid);

    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getIsSuccess() {
        return this.isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        this.isSuccess = success;
    }

    public BizEventTypeEnum getBizEventTypeEnum() {
        return this.bizEventTypeEnum;
    }

    public void setBizEventTypeEnum(BizEventTypeEnum bizEventTypeEnum) {
        this.bizEventTypeEnum = bizEventTypeEnum;
    }

    public BizEventOptTypeEnum getBizEventOptTypeEnum() {
        return this.bizEventOptTypeEnum;
    }

    public void setBizEventOptTypeEnum(BizEventOptTypeEnum bizEventOptTypeEnum) {
        this.bizEventOptTypeEnum = bizEventOptTypeEnum;
    }
}
