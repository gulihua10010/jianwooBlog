package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.BizPraiseTransDao;
import cn.jianwoo.blog.dao.base.mapper.BizPraiseMapper;
import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bizPraiseTransDao")
public class BizPraiseTransDaoImpl extends BizPraiseQueryDaoImpl implements BizPraiseTransDao {
    @Autowired
    BizPraiseMapper bizPraiseMapper;

    @Override
    public void doInsert(BizPraise record) throws DaoException {
        int intRlt = bizPraiseMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(BizPraise record) throws DaoException {
        int intRlt = bizPraiseMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(BizPraise record) throws DaoException {
        int updRlt = bizPraiseMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(BizPraise record) throws DaoException {
        int updRlt = bizPraiseMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = bizPraiseMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}