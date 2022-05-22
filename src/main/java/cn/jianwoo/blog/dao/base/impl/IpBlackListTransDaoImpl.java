package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.IpBlackListTransDao;
import cn.jianwoo.blog.dao.base.mapper.IpBlackListMapper;
import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ipBlackListTransDao")
public class IpBlackListTransDaoImpl extends IpBlackListQueryDaoImpl implements IpBlackListTransDao {
    @Autowired
    IpBlackListMapper ipBlackListMapper;

    @Override
    public void doInsert(IpBlackList record) throws DaoException {
        int intRlt = ipBlackListMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(IpBlackList record) throws DaoException {
        int intRlt = ipBlackListMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(IpBlackList record) throws DaoException {
        int updRlt = ipBlackListMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(IpBlackList record) throws DaoException {
        int updRlt = ipBlackListMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = ipBlackListMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}