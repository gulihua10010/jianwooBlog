package cn.jianwoo.blog.service.biz.impl;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.query.EmailTplQuery;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.EmailTplBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.EmailTplBizService;
import cn.jianwoo.blog.service.bo.EmailTplBO;
import cn.jianwoo.blog.service.param.EmailTplParam;
import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    public PageInfo<EmailTplBO> queryAllEmailTplPage(EmailTplParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        EmailTplQuery query = new EmailTplQuery();
        if (StringUtils.isNotBlank(param.getCode())) {
            query.setCode(Constants.SYMBOL_PERCENT.concat(param.getCode()).concat(Constants.SYMBOL_PERCENT));
        }
        if (StringUtils.isNotBlank(param.getDesc())) {
            query.setDesc(Constants.SYMBOL_PERCENT.concat(param.getDesc()).concat(Constants.SYMBOL_PERCENT));
        }
        List<EmailTemplate> emailTemplateList = emailTemplateTransDao.queryAllEmailTplList(query);
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
        EmailTemplate tpl = JwBuilder.of(EmailTemplate::new)
                .with(EmailTemplate::setEmailTplCode, param.getEmailTplCode())
                .with(EmailTemplate::setSubject, param.getSubject())
                .with(EmailTemplate::setContent, param.getContent())
                .with(EmailTemplate::setDesc, param.getDesc())
                .with(EmailTemplate::setTestJsonData, param.getTestJsonData())
                .with(EmailTemplate::setCreateTime, DateUtil.getNow())
                .with(EmailTemplate::setUpdateTime, DateUtil.getNow()).build();
        try {
            emailTemplateTransDao.doInsert(tpl);
        } catch (DaoException e) {
            log.error(">>doCreateEmailTpl exec failed, e\r\n", e);
            throw EmailTplBizException.CREATE_FAILED_EXCEPTION.format(param.getEmailTplCode()).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateEmailTpl(EmailTplBO param) throws JwBlogException {
        EmailTemplate tpl = JwBuilder.of(EmailTemplate::new)
                .with(EmailTemplate::setOid, param.getOid())
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
            throw EmailTplBizException.MODIFY_FAILED_EXCEPTION.format(param.getOid()).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveEmailTpl(Long oid) throws JwBlogException {
        try {
            emailTemplateTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error(">>doRemoveEmailTpl exec failed, e\r\n", e);
            throw EmailTplBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
    }

    @Override
    public EmailTplBO queryEmailTplByOid(String oid) throws JwBlogException {
        try {
            EmailTemplate tpl = emailTemplateTransDao.queryEmailTemplateByPrimaryKey(Long.parseLong(oid));
            return JwBuilder.of(EmailTplBO::new)
                    .with(EmailTplBO::setEmailTplCode, tpl.getEmailTplCode())
                    .with(EmailTplBO::setOid, tpl.getOid())
                    .with(EmailTplBO::setSubject, tpl.getSubject())
                    .with(EmailTplBO::setContent, tpl.getContent())
                    .with(EmailTplBO::setDesc, tpl.getDesc())
                    .with(EmailTplBO::setCreateTime, tpl.getCreateTime())
                    .with(EmailTplBO::setTestJsonData, tpl.getTestJsonData()).build();
        } catch (Exception e) {
            log.error(">>queryEmailTplByOid exec failed, e\r\n", e);
            throw EmailTplBizException.NOT_EXIST_EXCEPTION_CN.format(oid).print();
        }
    }

    @Override
    public String doRenderEmailTpl(EmailTplBO param) throws JwBlogException {
        try {
            //自动根据用户引入的模板引擎库的jar来自动选择使用的引擎
            //TemplateConfig为模板引擎的选项，可选内容有字符编码、模板路径、模板加载方式等，默认通过模板字符串渲染
            TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());

            //假设我们引入的是Beetl引擎，则：
            Template template = engine.getTemplate(param.getContent());
            return template.render(JSON.parseObject(param.getTestJsonData()));
        } catch (Exception e) {
            log.error(">>Render Email Template Failed, e:\r\n", e);
            throw EmailTplBizException.TPL_RENDER_FAILED_CN.format(param.getEmailTplCode()).print();
        }
    }
}
