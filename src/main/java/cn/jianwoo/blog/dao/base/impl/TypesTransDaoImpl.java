package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.TypesTransDao;
import cn.jianwoo.blog.dao.base.mapper.TypesMapper;
import cn.jianwoo.blog.entity.Types;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("typesTransDao")
public class TypesTransDaoImpl extends TypesQueryDaoImpl implements TypesTransDao {
    @Autowired
    TypesMapper typesMapper;

    @Override
    public void doInsert(Types record) throws DaoException {
        int intRlt = typesMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Types record) throws DaoException {
        int intRlt = typesMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Types record) throws DaoException {
        int updRlt = typesMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Types record) throws DaoException {
        int updRlt = typesMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = typesMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}