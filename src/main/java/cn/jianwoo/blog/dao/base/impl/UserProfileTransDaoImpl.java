package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.UserProfileTransDao;
import cn.jianwoo.blog.dao.base.mapper.UserProfileMapper;
import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userProfileTransDao")
public class UserProfileTransDaoImpl extends UserProfileQueryDaoImpl implements UserProfileTransDao {
    @Autowired
    UserProfileMapper userProfileMapper;

    @Override
    public void doInsert(UserProfile record) throws DaoException {
        int intRlt = userProfileMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(UserProfile record) throws DaoException {
        int intRlt = userProfileMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(UserProfile record) throws DaoException {
        int updRlt = userProfileMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(UserProfile record) throws DaoException {
        int updRlt = userProfileMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = userProfileMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}