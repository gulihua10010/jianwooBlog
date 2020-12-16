package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.dao.base.mapper.AdminMapper;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminTransDao")
public class AdminTransDaoImpl extends AdminQueryDaoImpl implements AdminTransDao {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public void doInsert(Admin record) throws DaoException {
        int intRlt = adminMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Admin record) throws DaoException {
        int intRlt = adminMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Admin record) throws DaoException {
        int updRlt = adminMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Admin record) throws DaoException {
        int updRlt = adminMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = adminMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}