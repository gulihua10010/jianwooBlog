package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface LoginLogQueryDao {
    LoginLog queryLoginLogByPrimaryKey(Long oid) throws DaoException;

    /**
     * 查询所有的记录
     *
     * @return
     * @author gulihua
     */
    List<LoginLog> queryAllList();
}