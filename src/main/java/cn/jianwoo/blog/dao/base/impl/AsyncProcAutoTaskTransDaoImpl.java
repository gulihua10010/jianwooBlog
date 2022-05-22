package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AsyncProcAutoTaskTransDao;
import cn.jianwoo.blog.dao.base.mapper.AsyncProcAutoTaskMapper;
import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("asyncProcAutoTaskTransDao")
public class AsyncProcAutoTaskTransDaoImpl extends AsyncProcAutoTaskQueryDaoImpl implements AsyncProcAutoTaskTransDao {
    @Autowired
    AsyncProcAutoTaskMapper asyncProcAutoTaskMapper;

    @Override
    public void doInsert(AsyncProcAutoTask record) throws DaoException {
        int intRlt = asyncProcAutoTaskMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(AsyncProcAutoTask record) throws DaoException {
        int intRlt = asyncProcAutoTaskMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(AsyncProcAutoTask record) throws DaoException {
        int updRlt = asyncProcAutoTaskMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(AsyncProcAutoTask record) throws DaoException {
        int updRlt = asyncProcAutoTaskMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long taskId) throws DaoException {
        int delRlt = asyncProcAutoTaskMapper.deleteByPrimaryKey(taskId);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}