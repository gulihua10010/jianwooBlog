package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AccessIpCtrl;
import cn.jianwoo.blog.exception.DaoException;

public interface AccessIpCtrlQueryDao {
    AccessIpCtrl queryAccessIpCtrlByPrimaryKey(Long oid) throws DaoException;
}