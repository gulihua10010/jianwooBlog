package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.MenuTransDao;
import cn.jianwoo.blog.dao.base.mapper.MenuMapper;
import cn.jianwoo.blog.entity.Menu;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuTransDao")
public class MenuTransDaoImpl extends MenuQueryDaoImpl implements MenuTransDao {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public void doInsert(Menu record) throws DaoException {
        int intRlt = menuMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doInsertSelective(Menu record) throws DaoException {
        int intRlt = menuMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKey(Menu record) throws DaoException {
        int updRlt = menuMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doUpdateByPrimaryKeySelective(Menu record) throws DaoException {
        int updRlt = menuMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }


    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = menuMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}