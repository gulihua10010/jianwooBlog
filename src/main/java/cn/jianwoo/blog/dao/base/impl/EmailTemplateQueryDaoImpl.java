package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.EmailTemplateQueryDao;
import cn.jianwoo.blog.dao.base.mapper.EmailTemplateMapper;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.example.EmailTemplateExample;
import cn.jianwoo.blog.entity.query.EmailTplQuery;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emailTemplateQueryDao")
public class EmailTemplateQueryDaoImpl implements EmailTemplateQueryDao {
    @Autowired
    EmailTemplateMapper emailTemplateMapper;

    @Override
    public EmailTemplate queryEmailTemplateByPrimaryKey(Long oid) throws DaoException {
        EmailTemplate record = emailTemplateMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<EmailTemplate> queryAllEmailTplList(EmailTplQuery query) {
        EmailTemplateExample example = new EmailTemplateExample();
        EmailTemplateExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(query.getCode())) {
            criteria.andEmailTplCodeLike(query.getCode());
        }
        if (StringUtils.isNotBlank(query.getDesc())) {
            criteria.andDescLike(query.getDesc());
        }

        return emailTemplateMapper.selectByExample(example);
    }

    @Override
    public EmailTemplate queryEmailTplByCode(String code) {
        EmailTemplateExample example = new EmailTemplateExample();
        example.createCriteria().andEmailTplCodeEqualTo(code);
        List<EmailTemplate> list = emailTemplateMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}