package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.exception.DaoException;

public interface EmailQueryDao {
    Email queryEmailByPrimaryKey(Long oid) throws DaoException;
}