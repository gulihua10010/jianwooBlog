package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AccessIpCtrl;
import cn.jianwoo.blog.exception.DaoException;

public interface AccessIpCtrlTransDao extends AccessIpCtrlQueryDao {
    void doInsert(AccessIpCtrl record) throws DaoException;

    void doInsertSelective(AccessIpCtrl record) throws DaoException;

    void doUpdateByPrimaryKey(AccessIpCtrl record) throws DaoException;

    void doUpdateByPrimaryKeySelective(AccessIpCtrl record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}