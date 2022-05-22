package cn.jianwoo.blog.service.notify.impl;

import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.EmailBizService;
import cn.jianwoo.blog.service.biz.EmailTplBizService;
import cn.jianwoo.blog.service.notify.NotifyMsgService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GuLihua
 * @Description
 * @date 2021-09-10 9:23
 */
@Service("emailNotifyService")
@Slf4j
public class EmailNotifyServiceImpl implements NotifyMsgService {


    @Autowired
    private EmailBizService emailBizService;
    @Autowired
    private EmailTemplateTransDao emailTemplateTransDao;
    @Autowired
    private EmailTplBizService emailTplBizService;

    @Override
    public void doSend(String emailTplCode, JSONObject param, String recipient) throws JwBlogException {
        EmailTemplate emailTemplate = emailTemplateTransDao.queryEmailTplByCode(emailTplCode);
        if (null == emailTemplate) {
            log.warn("Email Template is not set for code {}", emailTplCode);
            return;
        }
        String content = emailTplBizService.doRenderEmailTpl(emailTemplate.getContent(), param.toJSONString());

        emailBizService.doSendEmail(recipient, emailTplCode, param.toJSONString(), emailTemplate.getSubject(), content);
    }
}
