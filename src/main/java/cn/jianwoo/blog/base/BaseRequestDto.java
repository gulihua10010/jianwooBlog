package cn.jianwoo.blog.base;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-25 17:01
 */
public class BaseRequestDto implements Serializable {
    private static final long serialVersionUID = 2854392503356329692L;

    private String requestId;
    private String actor;
    private String clientIp;
    private String clientName;
    private String sessionId;
    private String token;
    private Date requestDate = new Date();


    public BaseRequestDto() {
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestId() {
        return requestId;
    }


    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getActor() {
        return actor;
    }


    public void setActor(String actor) {
        this.actor = actor;
    }


    public String getClientIp() {
        return clientIp;
    }


    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }


    public String getClientName() {
        return clientName;
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public String getSessionId() {
        return sessionId;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public Date getRequestDate() {
        return requestDate;
    }


    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        try {
            return BeanUtils.describe(this).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
