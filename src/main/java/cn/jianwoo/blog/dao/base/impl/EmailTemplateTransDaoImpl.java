package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.EmailTemplateTransDao;
import cn.jianwoo.blog.dao.base.mapper.EmailTemplateMapper;
import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailTemplateTransDao")
public class EmailTemplateTransDaoImpl extends EmailTemplateQueryDaoImpl implements EmailTemplateTransDao {
    @Autowired
    EmailTemplateMapper emailTemplateMapper;

    @Override
    public void doInsert(EmailTemplate record) throws DaoException {
        int intRlt = emailTemplateMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(EmailTemplate record) throws DaoException {
        int intRlt = emailTemplateMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(EmailTemplate record) throws DaoException {
        int updRlt = emailTemplateMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(EmailTemplate record) throws DaoException {
        int updRlt = emailTemplateMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = emailTemplateMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}