package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Module;
import cn.jianwoo.blog.exception.DaoException;

public interface ModuleQueryDao {
    Module queryModuleByPrimaryKey(Long oid) throws DaoException;
}