package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.EmailBizService;
import cn.jianwoo.blog.service.biz.EmailTplBizService;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.NotifiyUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author GuLihua
 * @Description
 * @date 2021-09-10 9:23
 */
@Service
@Slf4j
public class EmailBizServiceImpl implements EmailBizService {
    @Value("${jw.emailtpl.code.forgetPassword}")
    private String forgetPwdCode;
    @Autowired
    private NotifiyUtil notifiyUtil;
    @Autowired
    private EmailTemplateTransDao emailTemplateTransDao;
    @Autowired
    private CacheStore<String, Object> jwCacheStore;
    @Autowired
    private EmailTplBizService emailTplBizService;

    @Override
    public void sendEmail4ForgetPwd(String loginID, String emailTo) throws JwBlogException {
        EmailTemplate emailTemplate = emailTemplateTransDao.queryEmailTplByCode(forgetPwdCode);
        if (null == emailTemplate) {
            log.warn("Email Template is not set for code {}", forgetPwdCode);
            return;
        }
        String verifyCode = JwUtil.generateVerifyCode(6);
        String loginIDNameKey = MessageFormat.format(CacaheKeyConstants.VERIFY_CODE_LOGIN_ID, loginID);
        jwCacheStore.put(loginIDNameKey, verifyCode);
        JSONObject map = new JSONObject();
        map.put("username", loginID);
        map.put("verifyCode", verifyCode);
        String content = emailTplBizService.doRenderEmailTpl(emailTemplate.getEmailTplCode(), map.toJSONString());

        notifiyUtil.sendEmail(emailTo, emailTemplate.getSubject(), content);

    }
}
