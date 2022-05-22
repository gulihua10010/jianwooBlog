package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.EmailQueryDao;
import cn.jianwoo.blog.dao.base.mapper.EmailMapper;
import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.entity.example.EmailExample;
import cn.jianwoo.blog.entity.query.EmailQuery;
import cn.jianwoo.blog.enums.EmailSendStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emailQueryDao")
public class EmailQueryDaoImpl implements EmailQueryDao {
    @Autowired
    EmailMapper emailMapper;

    @Override
    public Email queryEmailByPrimaryKey(Long oid) throws DaoException {
        Email record = emailMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<Email> queryEmailPageList(EmailQuery query) {
        EmailExample example = new EmailExample();
        EmailExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(query.getCode())) {
            criteria.andEmailTplCodeLike(query.getCode());
        }
        if (StringUtils.isNotBlank(query.getSubject())) {
            criteria.andSubjectLike(query.getSubject());
        }
        if (Constants.TRUE_1.equals(query.getFailed())) {
            criteria.andProcStatusEqualTo(EmailSendStatusEnum.FAILED.getValue());

        }
        if (Constants.TRUE_1.equals(query.getException())) {
            criteria.andEmailTplCodeEqualTo(Constants.EMAIL_TPL_CODE_EXCEPTION);

        }
        example.setOrderByClause("CREATE_TIME DESC");

        return emailMapper.selectByExample(example);
    }
}