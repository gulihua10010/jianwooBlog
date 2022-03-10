package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.DaoException;

public interface AdminQueryDao {
    Admin queryAdminByPrimaryKey(Long oid) throws DaoException;


    /**
     * 通过LoginId(名字:username)查询<br>
     * 结果为null时抛出<br>
     *
     * @param loginID 名字
     * @return
     * @author gulihua
     */
    Admin queryAdminByLoginId(String loginID) throws DaoException;

    /**
     * 通过username查询
     * 结果为null时不抛出<br>
     *
     * @param username 名字
     * @return
     * @author gulihua
     */
    Admin queryAdminByUsername(String username);
}