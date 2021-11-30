package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.LoginLogTransDao;
import cn.jianwoo.blog.dao.base.mapper.LoginLogMapper;
import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginLogTransDao")
public class LoginLogTransDaoImpl extends LoginLogQueryDaoImpl implements LoginLogTransDao {
    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public void doInsert(LoginLog record) throws DaoException {
        int intRlt = loginLogMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(LoginLog record) throws DaoException {
        int intRlt = loginLogMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(LoginLog record) throws DaoException {
        int updRlt = loginLogMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(LoginLog record) throws DaoException {
        int updRlt = loginLogMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = loginLogMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}