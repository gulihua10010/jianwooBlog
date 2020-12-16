package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.WebconfTransDao;
import cn.jianwoo.blog.dao.base.mapper.WebconfMapper;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("webconfTransDao")
public class WebconfTransDaoImpl extends WebconfQueryDaoImpl implements WebconfTransDao {
    @Autowired
    WebconfMapper webconfMapper;

    @Override
    public void doInsert(Webconf record) throws DaoException {
        int intRlt = webconfMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Webconf record) throws DaoException {
        int intRlt = webconfMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Webconf record) throws DaoException {
        int updRlt = webconfMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Webconf record) throws DaoException {
        int updRlt = webconfMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = webconfMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}