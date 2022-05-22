package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.AttachmentBO;
import cn.jianwoo.blog.service.bo.EmailBO;
import cn.jianwoo.blog.service.param.EmailParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-18 10:35
 */
public interface EmailBizService {

    /**
     * 发送邮件
     *
     * @param emailTos       收件人(支持多个)
     * @param emailTplCode   邮件模板编号
     * @param param          组装的参数
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件列表
     * @author gulihua
     */
    void doSendEmail(List<String> emailTos, String emailTplCode, String param, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException;

    /**
     * 发送邮件
     *
     * @param emailTos     收件人(支持多个)
     * @param emailTplCode 邮件模板编号
     * @param param        组装的参数
     * @param subject      主题
     * @param content      内容
     * @author gulihua
     */
    void doSendEmail(List<String> emailTos, String emailTplCode, String param, String subject, String content) throws JwBlogException;

    /**
     * 发送邮件
     *
     * @param emailTo        收件人
     * @param emailTplCode   邮件模板编号
     * @param param          组装的参数
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件列表
     * @author gulihua
     */
    void doSendEmail(String emailTo, String emailTplCode, String param, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException;

    /**
     * 发送邮件
     *
     * @param emailTo        收件人
     * @param emailTplCode   邮件模板编号
     * @param param          组装的参数
     * @param subject        主题
     * @param content        内容
     * @author gulihua
     */
    void doSendEmail(String emailTo, String emailTplCode, String param, String subject, String content) throws JwBlogException;

    /**
     * 获取所有邮件
     *
     * @param param 参数
     * @return PageInfo<EmailBO>
     * @author gulihua
     */
    PageInfo<EmailBO> queryAllEmailListPage(EmailParam param);

    /**
     * 获取邮件详情
     *
     * @param oid 主键
     * @return EmailBO
     * @author gulihua
     */
    EmailBO queryEmailDetail(String oid) throws JwBlogException;

    /**
     * 重发邮件
     *
     * @param oid 主键
     * @author gulihua
     */
    void doReSend(Long oid) throws JwBlogException;

    /**
     * 重发多封邮件
     *
     * @param oidList 主键列表
     * @author gulihua
     */
    void doReSendList(List<Long> oidList) throws JwBlogException;
}
