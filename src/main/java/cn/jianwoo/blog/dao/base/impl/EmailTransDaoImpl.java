package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.EmailTransDao;
import cn.jianwoo.blog.dao.base.mapper.EmailMapper;
import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailTransDao")
public class EmailTransDaoImpl extends EmailQueryDaoImpl implements EmailTransDao {
    @Autowired
    EmailMapper emailMapper;

    @Override
    public void doInsert(Email record) throws DaoException {
        int intRlt = emailMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(Email record) throws DaoException {
        int intRlt = emailMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(Email record) throws DaoException {
        int updRlt = emailMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(Email record) throws DaoException {
        int updRlt = emailMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = emailMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}