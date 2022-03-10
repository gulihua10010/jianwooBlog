package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.LoginFailedEventTransDao;
import cn.jianwoo.blog.dao.base.mapper.LoginFailedEventMapper;
import cn.jianwoo.blog.entity.LoginFailedEvent;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginFailedEventTransDao")
public class LoginFailedEventTransDaoImpl extends LoginFailedEventQueryDaoImpl implements LoginFailedEventTransDao {
    @Autowired
    LoginFailedEventMapper loginFailedEventMapper;

    @Override
    public void doInsert(LoginFailedEvent record) throws DaoException {
        int intRlt = loginFailedEventMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(LoginFailedEvent record) throws DaoException {
        int intRlt = loginFailedEventMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(LoginFailedEvent record) throws DaoException {
        int updRlt = loginFailedEventMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(LoginFailedEvent record) throws DaoException {
        int updRlt = loginFailedEventMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = loginFailedEventMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}