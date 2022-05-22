package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.query.EmailTplQuery;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.EmailTplBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.EmailTplBizService;
import cn.jianwoo.blog.service.bo.EmailTplBO;
import cn.jianwoo.blog.service.param.EmailTplParam;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:59
 */
@Service
@Slf4j
public class EmailTplBizServiceImpl implements EmailTplBizService {
    @Autowired
    private EmailTemplateTransDao emailTemplateTransDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageInfo<EmailTplBO> queryAllEmailTplPage(EmailTplParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        EmailTplQuery query = new EmailTplQuery();
        if (StringUtils.isNotBlank(param.getCode())) {
            query.setCode(param.getCode());
        }
        if (StringUtils.isNotBlank(param.getDesc())) {
            query.setDesc(param.getDesc());
        }
        List<EmailTemplate> emailTemplateList = emailTemplateTransDao.queryAllEffectiveEmailTplList(query);
        List<EmailTplBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(emailTemplateList)) {
            emailTemplateList.forEach(o -> {
                EmailTplBO bo = new EmailTplBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<EmailTplBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateEmailTpl(EmailTplBO param) throws JwBlogException {

        EmailTemplate oldTpl = emailTemplateTransDao.queryEmailTplByCode(param.getEmailTplCode());
        if (null != oldTpl) {
            throw EmailTplBizException.HAS_EXIST_EXCEPTION_CN.format(param.getEmailTplCode()).print();
        }
        Date now = DateUtil.getNow();
        EmailTemplate tpl = JwBuilder.of(EmailTemplate::new)
                .with(EmailTemplate::setEmailTplCode, param.getEmailTplCode())
                .with(EmailTemplate::setSubject, param.getSubject())
                .with(EmailTemplate::setContent, param.getContent())
                .with(EmailTemplate::setDesc, param.getDesc())
                .with(EmailTemplate::setTestJsonData, param.getTestJsonData())
                .with(EmailTemplate::setCreateTime, now)
                .with(EmailTemplate::setUpdateTime, now).build();
        try {
            emailTemplateTransDao.doInsertSelective(tpl);
        } catch (DaoException e) {
            log.error(">>doCreateEmailTpl exec failed, e\r\n", e);
            throw EmailTplBizException.CREATE_FAILED_EXCEPTION.format(param.getEmailTplCode()).print();
        }
        registerBizEvent(tpl.getEmailTplCode(), tpl.getEmailTplCode(), BizEventOptTypeEnum.CREATE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateEmailTpl(EmailTplBO param) throws JwBlogException {
        EmailTemplate tpl = JwBuilder.of(EmailTemplate::new)
                .with(EmailTemplate::setEmailTplCode, param.getEmailTplCode())
                .with(EmailTemplate::setSubject, param.getSubject())
                .with(EmailTemplate::setContent, param.getContent())
                .with(EmailTemplate::setDesc, param.getDesc())
                .with(EmailTemplate::setTestJsonData, param.getTestJsonData())
                .with(EmailTemplate::setUpdateTime, DateUtil.getNow()).build();
        try {
            emailTemplateTransDao.doUpdateByPrimaryKeySelective(tpl);
        } catch (DaoException e) {
            log.error(">>doUpdateEmailTpl exec failed, e\r\n", e);
            throw EmailTplBizException.MODIFY_FAILED_EXCEPTION.format(param.getEmailTplCode()).print();
        }
        registerBizEvent(tpl.getEmailTplCode(), tpl.getEmailTplCode(), BizEventOptTypeEnum.UPDATE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveEmailTpl(String code) throws JwBlogException {
        try {
            emailTemplateTransDao.doDeleteByPrimaryKey(code);
        } catch (DaoException e) {
            log.error(">>doRemoveEmailTpl exec failed, e\r\n", e);
            throw EmailTplBizException.DELETE_FAILED_EXCEPTION.format(code).print();
        }
        registerBizEvent(code, null, BizEventOptTypeEnum.DELETE);
    }

    @Override
    public EmailTplBO queryEmailTplByCode(String code) throws JwBlogException {
        try {
            EmailTemplate tpl = emailTemplateTransDao.queryEmailTemplateByPrimaryKey(code);
            if (!tpl.getStatusUsed()) {
                throw EmailTplBizException.NOT_USED_EXCEPTION_CN.format(code).print();
            }
            return JwBuilder.of(EmailTplBO::new)
                    .with(EmailTplBO::setEmailTplCode, tpl.getEmailTplCode())
                    .with(EmailTplBO::setSubject, tpl.getSubject())
                    .with(EmailTplBO::setContent, tpl.getContent())
                    .with(EmailTplBO::setDesc, tpl.getDesc())
                    .with(EmailTplBO::setCreateTime, tpl.getCreateTime())
                    .with(EmailTplBO::setTestJsonData, tpl.getTestJsonData()).build();
        } catch (DaoException e) {
            log.error(">>queryEmailTplByCode exec failed, e\r\n", e);
            throw EmailTplBizException.NOT_EXIST_EXCEPTION_CN.format(code).print();
        }
    }

    @Override
    public String doRenderEmailTpl(String content, String param) throws JwBlogException {
        return JwUtil.doRenderTpl(content, param);
    }

    private void registerBizEvent(String code, String desc, BizEventOptTypeEnum optTypeEnum) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.EMAIL_TPL);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOptEntityId(code);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }

}
