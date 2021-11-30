package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.BizEventLogTransDao;
import cn.jianwoo.blog.dao.base.mapper.BizEventLogMapper;
import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bizEventLogTransDao")
public class BizEventLogTransDaoImpl extends BizEventLogQueryDaoImpl implements BizEventLogTransDao {
    @Autowired
    BizEventLogMapper bizEventLogMapper;

    @Override
    public void doInsert(BizEventLog record) throws DaoException {
        int intRlt = bizEventLogMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(BizEventLog record) throws DaoException {
        int intRlt = bizEventLogMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(BizEventLog record) throws DaoException {
        int updRlt = bizEventLogMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(BizEventLog record) throws DaoException {
        int updRlt = bizEventLogMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = bizEventLogMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}