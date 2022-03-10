package cn.jianwoo.blog.util;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.EmailTransDao;
import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.enums.CfgTypeEnum;
import cn.jianwoo.blog.enums.EmailSendStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.AttachmentBO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    @Autowired
    private EmailTransDao emailTransDao;


    private void sendEmail(List<String> emailTo, String subject, String content, boolean isHtml, List<AttachmentBO> attachmentList) throws JwBlogException {
        log.info(">>Start Send Email::emailTo = [{}], subject = [{}], content = [{}]", JSON.toJSONString(emailTo), subject, content);
        Email email = new Email();
        Email updEmail = new Email();
        try {
            Date now = DateUtil.getNow();
            email.setToEmail(JSON.toJSONString(emailTo));
            email.setSubject(subject);
            email.setContent(content);
            email.setFiles(CollectionUtils.isNotEmpty(attachmentList) ? JSON.toJSONString(attachmentList) : null);
            email.setProcStatus(EmailSendStatusEnum.INIT.getValue());
            email.setProcTime(now);
            email.setCreateTime(now);
            email.setUpdateTime(now);
            emailTransDao.doInsert(email);
            MailAccount account = fetchMailAcct();
            log.info("<<Fetch Email account::[{}]", JSON.toJSONString(account));
            List<File> files = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                files = attachmentList.stream().map(o -> new File(o.getFilePath())).collect(Collectors.toList());
            }
            updEmail.setOid(email.getOid());
            updEmail.setProcStatus(EmailSendStatusEnum.SENDING.getValue());
            emailTransDao.doUpdateByPrimaryKeySelective(updEmail);

            MailUtil.send(account, emailTo, subject, content, isHtml, files.toArray(new File[0]));

            updEmail.setProcStatus(EmailSendStatusEnum.SUCCESS.getValue());
            updEmail.setProcDesc(Constants.SUCCESS);
            emailTransDao.doUpdateByPrimaryKeySelective(updEmail);
        } catch (Exception e) {
            log.error(">>Start Send Email failed, e\r\n", e);
            updEmail.setProcStatus(EmailSendStatusEnum.FAILED.getValue());
            updEmail.setProcDesc(e.getMessage());
            try {
                emailTransDao.doUpdateByPrimaryKeySelective(updEmail);
            } catch (DaoException daoException) {
                log.error(">> Insert into Email failed, e\r\n", daoException);
            }
            throw new JwBlogException(ExceptionConstants.EMAIL_SEND_FAILED, ExceptionConstants.EMAIL_SEND_FAILED_DESC);

        }
    }

    private MailAccount fetchMailAcct() throws JwBlogException {
        MailAccount account = null;

        if (cacheStore.hasKey(CacaheKeyConstants.EMAIL_CFG)) {
            log.info(">>Fetch MailAcct from cache success.");
            return cacheStore.get(CacaheKeyConstants.EMAIL_CFG).get();
        }
        Map<String,String> cfgMap = webconfBizService.queryWebconfByType(CfgTypeEnum.EMAIL.getValue());
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

    public void sendEmailText(List<String> emailTo, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(emailTo, subject, content, false, attachmentList);
    }

    public void sendEmailText(List<String> emailTo, String subject, String content) throws JwBlogException {
        sendEmail(emailTo, subject, content, false, null);
    }

    public void sendEmail(List<String> emailTo, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(emailTo, subject, content, true, attachmentList);
    }

    public void sendEmail(List<String> emailTo, String subject, String content) throws JwBlogException {
        sendEmail(emailTo, subject, content, true, null);
    }

    //
    public void sendEmailText(String emailTo, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, false, attachmentList);
    }

    public void sendEmailText(String emailTo, String subject, String content) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, false, null);
    }

    public void sendEmail(String emailTo, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, true, attachmentList);
    }

    public void sendEmail(String emailTo, String subject, String content) throws JwBlogException {
        sendEmail(Collections.singletonList(emailTo), subject, content, true, null);
    }

}
