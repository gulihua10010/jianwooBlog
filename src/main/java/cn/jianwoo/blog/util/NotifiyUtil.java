package cn.jianwoo.blog.util;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.enums.CfgTypeEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.AttachmentBO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 邮件发送工具类
 *
 * @author GuLihua
 * @Description
 * @date 2021-07-13 10:42
 */
@Slf4j
@Component
public class NotifiyUtil {

    @Autowired
    private CacheStore<String, MailAccount> cacheStore;
    @Autowired
    private WebconfBizService webconfBizService;


    private void sendEmail(List<String> emailTo, String subject, String content, boolean isHtml, List<AttachmentBO> attachmentList) throws JwBlogException {
        log.info(">>Start Send Email::emailTo = [{}], subject = [{}], content = [{}]", JSON.toJSONString(emailTo), subject, content);
        try {

            MailAccount account = fetchMailAcct();
            log.info("<<Fetch Email account::[{}]", JSON.toJSONString(account));
            List<File> files = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                files = attachmentList.stream().map(o -> new File(o.getFilePath())).collect(Collectors.toList());
            }

            MailUtil.send(account, emailTo, subject, content, isHtml, files.toArray(new File[0]));

        } catch (Exception e) {
            log.error(">>Start Send Email failed, e\r\n", e);
            throw new JwBlogException(e);

        }
    }

    private MailAccount fetchMailAcct() throws JwBlogException {
        MailAccount account = null;

        if (cacheStore.hasKey(CacaheKeyConstants.EMAIL_CFG)) {
            log.info(">>Fetch MailAcct from cache success.");
            return cacheStore.get(CacaheKeyConstants.EMAIL_CFG).get();
        }
        Map<String, String> cfgMap = webconfBizService.queryWebconfByType(CfgTypeEnum.EMAIL.getValue());
        String host = cfgMap.get(WebConfDataConfig.EMAIL_HOST);
        String port = cfgMap.get(WebConfDataConfig.EMAIL_PORT);
        String sender = cfgMap.get(WebConfDataConfig.EMAIL_SENDER);
        String user = cfgMap.get(WebConfDataConfig.EMAIL_USER);
        String senderNick = cfgMap.get(WebConfDataConfig.EMAIL_SENDER_NICK);
        String pwd = cfgMap.get(WebConfDataConfig.EMAIL_PWD);
        String ttlsEnable = cfgMap.get(WebConfDataConfig.EMAIL_STARTTLS);
        String sslEnable = cfgMap.get(WebConfDataConfig.EMAIL_SSL);
        String socketFactoryClass = cfgMap.get(WebConfDataConfig.EMAIL_SOCKET_FACTORY_CLASS);
        String socketFactoryFallback = cfgMap.get(WebConfDataConfig.EMAIL_SOCKET_FACTORY_FALLBACK);
        String socketFactoryPort = cfgMap.get(WebConfDataConfig.EMAIL_SOCKET_FACTORY_PORT);
        String timeout = cfgMap.get(WebConfDataConfig.EMAIL_STMT_TIMEOUT);
        String connectionTimeout = cfgMap.get(WebConfDataConfig.EMAIL_CONNECTION_TIMEOUT);

        account = new MailAccount();
        account.setHost(host);
        account.setPort(new BigDecimal(port).intValue());
        account.setAuth(true);
//        account.setFrom(sender);
        account.setFrom(String.format("%s <%s>", senderNick, sender));
        account.setUser(user);
        account.setPass(pwd);
        if (StringUtils.isNotBlank(sslEnable)) {
            account.setSslEnable(Constants.TRUE.equals(sslEnable));
        }
        if (StringUtils.isNotBlank(ttlsEnable)) {
            account.setStarttlsEnable(Constants.TRUE.equals(ttlsEnable));
        }
        if (StringUtils.isNotBlank(socketFactoryClass)) {
            account.setSocketFactoryClass(socketFactoryClass);
        }
        if (StringUtils.isNotBlank(socketFactoryFallback)) {
            account.setSocketFactoryFallback(Constants.TRUE.equals(socketFactoryFallback));
        }
        if (StringUtils.isNotBlank(socketFactoryPort)) {
            account.setSocketFactoryPort(new BigDecimal(socketFactoryPort).intValue());
        }
        if (StringUtils.isNotBlank(timeout)) {
            account.setTimeout(new BigDecimal(timeout).longValue());
        }
        if (StringUtils.isNotBlank(connectionTimeout)) {
            account.setConnectionTimeout(new BigDecimal(connectionTimeout).longValue());
        }
        cacheStore.put(CacaheKeyConstants.EMAIL_CFG, account);
        return account;

    }

    /**
     * 发送邮件(纯文本)
     *
     * @param emailTos       收件人(支持多个)
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件列表
     * @author gulihua
     */
    public void sendEmailText(List<String> emailTos, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(emailTos, subject, content, false, attachmentList);
    }

    /**
     * 发送邮件(纯文本)
     *
     * @param emailTos 收件人(支持多个)
     * @param subject  主题
     * @param content  内容
     * @author gulihua
     */
    public void sendEmailText(List<String> emailTos, String subject, String content) throws JwBlogException {
        sendEmail(emailTos, subject, content, false, null);
    }

    /**
     * 发送邮件(支持HTML)
     *
     * @param emailTos       收件人(支持多个)
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件列表
     * @author gulihua
     */
    public void sendEmail(List<String> emailTos, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(emailTos, subject, content, true, attachmentList);
    }

    /**
     * 发送邮件(支持HTML)
     *
     * @param emailTos 收件人(支持多个)
     * @param subject  主题
     * @param content  内容
     * @author gulihua
     */
    public void sendEmail(List<String> emailTos, String subject, String content) throws JwBlogException {
        sendEmail(emailTos, subject, content, true, null);
    }

    /**
     * 发送邮件(纯文本)
     *
     * @param emailTo        收件人
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件列表
     * @author gulihua
     */
    public void sendEmailText(String emailTo, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, false, attachmentList);
    }

    /**
     * 发送邮件(纯文本)
     *
     * @param emailTo 收件人
     * @param subject 主题
     * @param content 内容
     * @author gulihua
     */
    public void sendEmailText(String emailTo, String subject, String content) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, false, null);
    }

    /**
     * 发送邮件(支持HTML)
     *
     * @param emailTo        收件人
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件列表
     * @author gulihua
     */
    public void sendEmail(String emailTo, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, true, attachmentList);
    }

    /**
     * 发送邮件(支持HTML)
     *
     * @param emailTo 收件人
     * @param subject 主题
     * @param content 内容
     * @author gulihua
     */
    public void sendEmail(String emailTo, String subject, String content) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, true, null);
    }

}
