package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.EmailTransDao;
import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.entity.query.EmailQuery;
import cn.jianwoo.blog.enums.EmailSendStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.EmailBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.EmailBizService;
import cn.jianwoo.blog.service.bo.AttachmentBO;
import cn.jianwoo.blog.service.bo.EmailBO;
import cn.jianwoo.blog.service.param.EmailParam;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.NotifiyUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-18 10:43
 */
@Service
@Slf4j
public class EmailBizServiceImpl implements EmailBizService {
    @Autowired
    private NotifiyUtil notifiyUtil;
    @Autowired
    private EmailTransDao emailTransDao;

    @Override
    public void doSendEmail(List<String> emailTos, String emailTplCode, String param, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        Email email = new Email();
        Email updEmail = new Email();
        try {
            Date now = DateUtil.getNow();
            email.setToEmail(JSON.toJSONString(emailTos));
            email.setSubject(subject);
            email.setEmailTplCode(emailTplCode);
            email.setJsonData(param);
            email.setContent(content);
            email.setFiles(CollectionUtils.isNotEmpty(attachmentList) ? JSON.toJSONString(attachmentList) : null);
            email.setProcStatus(EmailSendStatusEnum.INIT.getValue());
            email.setProcTime(now);
            email.setCreateTime(now);
            email.setUpdateTime(now);
            emailTransDao.doInsert(email);

            updEmail.setOid(email.getOid());
            updEmail.setProcStatus(EmailSendStatusEnum.SENDING.getValue());
            emailTransDao.doUpdateByPrimaryKeySelective(updEmail);

            notifiyUtil.sendEmail(emailTos, subject, content, attachmentList);

            updEmail.setProcStatus(EmailSendStatusEnum.SUCCESS.getValue());
            updEmail.setProcDesc(Constants.SUCCESS);
            emailTransDao.doUpdateByPrimaryKeySelective(updEmail);
        } catch (Exception e) {
//            log.error(">>Start Send Email failed, e\r\n", e);
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

    @Override
    public void doSendEmail(List<String> emailTos, String emailTplCode, String param, String subject, String content) throws JwBlogException {
        this.doSendEmail(emailTos, emailTplCode, param, subject, content, null);
    }

    @Override
    public void doSendEmail(String emailTo, String emailTplCode, String param, String subject, String content, List<AttachmentBO> attachmentList) throws JwBlogException {
        this.doSendEmail(Collections.singletonList(emailTo), emailTplCode, param, subject, content, attachmentList);

    }

    @Override
    public void doSendEmail(String emailTo, String emailTplCode, String param, String subject, String content) throws JwBlogException {
        this.doSendEmail(Collections.singletonList(emailTo), emailTplCode, param, subject, content, null);

    }

    @Override
    public PageInfo<EmailBO> queryAllEmailListPage(EmailParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        EmailQuery query = new EmailQuery();
        if (StringUtils.isNotBlank(param.getCode())) {
            query.setCode(param.getCode());
        }
        if (StringUtils.isNotBlank(param.getSubject())) {
            query.setSubject(param.getSubject());
        }
        if (Constants.TRUE_1.equals(param.getFailed())) {
            query.setFailed(param.getFailed());
        }
        if (Constants.TRUE_1.equals(param.getException())) {
            query.setException(param.getException());
        }
        List<Email> emailList = emailTransDao.queryEmailPageList(query);
        List<EmailBO> list = new ArrayList<>();
        if (!org.springframework.util.CollectionUtils.isEmpty(emailList)) {
            emailList.forEach(o -> {
                EmailBO bo = new EmailBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<EmailBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public EmailBO queryEmailDetail(String oid) throws JwBlogException {
        try {
            Email email = emailTransDao.queryEmailByPrimaryKey(Long.valueOf(oid));
            EmailBO bo = new EmailBO();
            BeanUtils.copyProperties(email, bo);
            return bo;
        } catch (DaoException e) {
            throw EmailBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();
        }
    }

    @Override
    public void doReSend(Long oid) throws JwBlogException {
        try {
            Email email = emailTransDao.queryEmailByPrimaryKey(oid);
            this.doSendEmail(JSON.parseArray(email.getToEmail(), String.class), email.getEmailTplCode(),
                    email.getJsonData(), email.getSubject(), email.getContent(), JSON.parseArray(email.getFiles(), AttachmentBO.class));
        } catch (DaoException e) {
            throw EmailBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();
        }
    }

    @Override
    public void doReSendList(List<Long> oidList) throws JwBlogException {
        for (Long oidId : oidList) {
            this.doReSend(oidId);
        }
    }
}
