package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ModuleTransDao;
import cn.jianwoo.blog.dao.base.mapper.ModuleMapper;
import cn.jianwoo.blog.entity.Module;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("moduleTransDao")
public class ModuleTransDaoImpl extends ModuleQueryDaoImpl implements ModuleTransDao {
    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public void doInsert(Module record) throws DaoException {
        int intRlt = moduleMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Module record) throws DaoException {
        int intRlt = moduleMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Module record) throws DaoException {
        int updRlt = moduleMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Module record) throws DaoException {
        int updRlt = moduleMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = moduleMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}