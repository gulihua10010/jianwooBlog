package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.WebconfFacadeTransDao;
import cn.jianwoo.blog.dao.base.mapper.WebconfFacadeMapper;
import cn.jianwoo.blog.entity.WebconfFacade;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("webconfFacadeTransDao")
public class WebconfFacadeTransDaoImpl extends WebconfFacadeQueryDaoImpl implements WebconfFacadeTransDao {
    @Autowired
    WebconfFacadeMapper webconfFacadeMapper;

    @Override
    public void doInsert(WebconfFacade record) throws DaoException {
        int intRlt = webconfFacadeMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(WebconfFacade record) throws DaoException {
        int intRlt = webconfFacadeMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(WebconfFacade record) throws DaoException {
        int updRlt = webconfFacadeMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(WebconfFacade record) throws DaoException {
        int updRlt = webconfFacadeMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(String key) throws DaoException {
        int delRlt = webconfFacadeMapper.deleteByPrimaryKey(key);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}