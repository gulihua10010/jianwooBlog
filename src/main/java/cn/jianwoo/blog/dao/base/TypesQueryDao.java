package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Types;
import cn.jianwoo.blog.exception.DaoException;

public interface TypesQueryDao {
    Types queryTypesByPrimaryKey(Long oid) throws DaoException;
}