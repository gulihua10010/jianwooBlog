package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.DaoException;

public interface AdminQueryDao {
    Admin queryAdminByPrimaryKey(Long oid) throws DaoException;


    /**
     * 通过名字查询
     *
     * @param name 名字
     * @return
     * @author gulihua
     */
    Admin queryAdminByName(String name);
}