package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AccessIpCtrlTransDao;
import cn.jianwoo.blog.dao.base.mapper.AccessIpCtrlMapper;
import cn.jianwoo.blog.entity.AccessIpCtrl;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accessIpCtrlTransDao")
public class AccessIpCtrlTransDaoImpl extends AccessIpCtrlQueryDaoImpl implements AccessIpCtrlTransDao {
    @Autowired
    AccessIpCtrlMapper accessIpCtrlMapper;

    @Override
    public void doInsert(AccessIpCtrl record) throws DaoException {
        int intRlt = accessIpCtrlMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(AccessIpCtrl record) throws DaoException {
        int intRlt = accessIpCtrlMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(AccessIpCtrl record) throws DaoException {
        int updRlt = accessIpCtrlMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(AccessIpCtrl record) throws DaoException {
        int updRlt = accessIpCtrlMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = accessIpCtrlMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}