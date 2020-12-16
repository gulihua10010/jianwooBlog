package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.DaoException;

public interface AdminQueryDao {
    Admin queryAdminByPrimaryKey(Long oid) throws DaoException;


    Admin queryAdminByName(String name);
}