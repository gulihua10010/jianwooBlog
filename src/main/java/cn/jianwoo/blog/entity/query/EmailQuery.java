package cn.jianwoo.blog.entity.query;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class EmailQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 2478443514263410456L;

    /**
     * 邮件模板编号
     */
    private String code;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 是否是异常邮件
     */
    private String exception;
    /**
     * 是否发送失败
     */
    private String failed;

    public String getCode() {
        if (StringUtils.isBlank(this.code)) {
            return null;
        }
        return String.format("%%%s%%", this.code);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        if (StringUtils.isBlank(this.subject)) {
            return null;
        }
        return String.format("%%%s%%", this.subject);
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getException() {
        return this.exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getFailed() {
        return this.failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }
}
