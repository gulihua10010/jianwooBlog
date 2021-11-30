package cn.jianwoo.blog.event;

import cn.jianwoo.blog.enums.LoginEventTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description 登录事件
 * @date 2021-08-24 10:58
 */
@Slf4j
public class LoginLogEvent extends ApplicationEvent {
    /**
     * 用户名
     */
    private String username;
    /**
     * request
     */
    private HttpServletRequest request;

    /**
     * 登录描述
     */
    private String loginDesc;
    /**
     * 登录失败原因
     */
    private String reason;
    /**
     * 是否成功
     */
    private Boolean isSuccess;

    /**
     * 登录类型
     */
    private LoginEventTypeEnum eventTypeEnum;

    public LoginLogEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public LoginLogEvent(Object source, String username, HttpServletRequest request) {
        super(source);
        this.username = username;
        this.request = request;
//        log.info(">>LoginLogEvent.username = [{}]", username);
    }

    public static Logger getLog() {
        return LoginLogEvent.log;
    }

    public String getLoginDesc() {
        return this.loginDesc;
    }

    public void setLoginDesc(String loginDesc) {
        this.loginDesc = loginDesc;
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

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginEventTypeEnum getEventTypeEnum() {
        return this.eventTypeEnum;
    }

    public void setEventTypeEnum(LoginEventTypeEnum eventTypeEnum) {
        this.eventTypeEnum = eventTypeEnum;
    }
}
