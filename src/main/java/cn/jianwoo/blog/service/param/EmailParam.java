package cn.jianwoo.blog.service.param;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:41
 */
public class EmailParam extends PageParam {
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
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return this.subject;
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
