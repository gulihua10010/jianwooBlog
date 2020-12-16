package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.exception.DaoException;

public interface VisitQueryDao {
    Visit queryVisitByPrimaryKey(Long oid) throws DaoException;
}