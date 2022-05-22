package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AsyncProcTaskTypeCfgTransDao;
import cn.jianwoo.blog.dao.base.mapper.AsyncProcTaskTypeCfgMapper;
import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("asyncProcTaskTypeCfgTransDao")
public class AsyncProcTaskTypeCfgTransDaoImpl extends AsyncProcTaskTypeCfgQueryDaoImpl implements AsyncProcTaskTypeCfgTransDao {
    @Autowired
    AsyncProcTaskTypeCfgMapper asyncProcTaskTypeCfgMapper;

    @Override
    public void doInsert(AsyncProcTaskTypeCfg record) throws DaoException {
        int intRlt = asyncProcTaskTypeCfgMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(AsyncProcTaskTypeCfg record) throws DaoException {
        int intRlt = asyncProcTaskTypeCfgMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(AsyncProcTaskTypeCfg record) throws DaoException {
        int updRlt = asyncProcTaskTypeCfgMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(AsyncProcTaskTypeCfg record) throws DaoException {
        int updRlt = asyncProcTaskTypeCfgMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(String taskType) throws DaoException {
        int delRlt = asyncProcTaskTypeCfgMapper.deleteByPrimaryKey(taskType);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}