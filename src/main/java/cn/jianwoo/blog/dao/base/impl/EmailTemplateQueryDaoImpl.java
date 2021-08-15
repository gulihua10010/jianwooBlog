package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.EmailTemplateQueryDao;
import cn.jianwoo.blog.dao.base.mapper.EmailTemplateMapper;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.entity.example.EmailTemplateExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
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
    public List<EmailTemplate> queryAllEmailTplList() {
        return emailTemplateMapper.selectByExample(new EmailTemplateExample());
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