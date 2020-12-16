package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Module;
import cn.jianwoo.blog.exception.DaoException;

public interface ModuleTransDao extends ModuleQueryDao {
    void doInsert(Module record) throws DaoException;


    void doInsertSelective(Module record) throws DaoException;


    void doUpdateByPrimaryKey(Module record) throws DaoException;


    void doUpdateByPrimaryKeySelective(Module record) throws DaoException;


    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}