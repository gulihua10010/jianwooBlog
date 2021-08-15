package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.query.EmailTplParam;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.EmailTplBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.EmailTplBizService;
import cn.jianwoo.blog.service.bo.EmailTplBO;
import cn.jianwoo.blog.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public PageInfo<EmailTemplate> queryAllEmailTplPage(EmailTplParam param) {
        PageHelper.startPage(param.getPageNo(), param.getPageSize());
        List<EmailTemplate> list = emailTemplateTransDao.queryAllEmailTplList();
        PageInfo<EmailTemplate> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddEmailTpl(EmailTplBO param) throws JwBlogException {

        EmailTemplate oldTpl = emailTemplateTransDao.queryEmailTplByCode(param.getCode());
        if (null != oldTpl) {
            throw EmailTplBizException.HAS_EXIST_EXCEPTION_CN.format(param.getCode()).print();
        }
        EmailTemplate tpl = JwBuilder.of(EmailTemplate::new)
                .with(EmailTemplate::setEmailTplCode, param.getCode())
                .with(EmailTemplate::setSubject, param.getSubject())
                .with(EmailTemplate::setContent, param.getContent())
                .with(EmailTemplate::setDesc, param.getDesc())
                .with(EmailTemplate::setTestJsonData, param.getJsonData())
                .with(EmailTemplate::setCreateTime, DateUtil.getNow())
                .with(EmailTemplate::setUpdateTime, DateUtil.getNow()).build();
        try {
            emailTemplateTransDao.doInsert(tpl);
        } catch (DaoException e) {
            throw EmailTplBizException.CREATE_FAILED_EXCEPTION.format(param.getCode()).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateEmailTpl(EmailTplBO param) throws JwBlogException {
        EmailTemplate tpl = JwBuilder.of(EmailTemplate::new)
                .with(EmailTemplate::setOid, param.getOid())
                .with(EmailTemplate::setEmailTplCode, param.getCode())
                .with(EmailTemplate::setSubject, param.getSubject())
                .with(EmailTemplate::setContent, param.getContent())
                .with(EmailTemplate::setDesc, param.getDesc())
                .with(EmailTemplate::setTestJsonData, param.getJsonData())
                .with(EmailTemplate::setCreateTime, DateUtil.getNow())
                .with(EmailTemplate::setUpdateTime, DateUtil.getNow()).build();
        try {
            emailTemplateTransDao.doUpdateByPrimaryKeySelective(tpl);
        } catch (DaoException e) {
            throw EmailTplBizException.MODIFY_FAILED_EXCEPTION.format(param.getOid()).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveEmailTpl(Long oid) throws JwBlogException {
        try {
            emailTemplateTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            throw EmailTplBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
    }

    @Override
    public EmailTplBO queryEmailTplByOid(Long oid) throws JwBlogException {
        try {
            EmailTemplate tpl = emailTemplateTransDao.queryEmailTemplateByPrimaryKey(oid);
            return JwBuilder.of(EmailTplBO::new)
                    .with(EmailTplBO::setCode, tpl.getEmailTplCode())
                    .with(EmailTplBO::setOid, tpl.getOid())
                    .with(EmailTplBO::setSubject, tpl.getSubject())
                    .with(EmailTplBO::setContent, tpl.getContent())
                    .with(EmailTplBO::setDesc, tpl.getDesc())
                    .with(EmailTplBO::setCreateDate, tpl.getCreateTime())
                    .with(EmailTplBO::setJsonData, tpl.getTestJsonData()).build();
        } catch (DaoException e) {
            throw EmailTplBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
    }
}
