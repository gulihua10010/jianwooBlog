package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.VisitTransDao;
import cn.jianwoo.blog.dao.base.mapper.VisitMapper;
import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("visitTransDao")
public class VisitTransDaoImpl extends VisitQueryDaoImpl implements VisitTransDao {
    @Autowired
    VisitMapper visitMapper;

    @Override
    public void doInsert(Visit record) throws DaoException {
        int intRlt = visitMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Visit record) throws DaoException {
        int intRlt = visitMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Visit record) throws DaoException {
        int updRlt = visitMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Visit record) throws DaoException {
        int updRlt = visitMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = visitMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}