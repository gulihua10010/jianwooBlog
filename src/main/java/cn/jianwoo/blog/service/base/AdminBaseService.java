package cn.jianwoo.blog.service.base;

import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.JwBlogException;

/**
 * @author gulihua
 * @Description
 * @date 2022-02-28 12:01
 */
public interface AdminBaseService {

    /**
     * 通过主键Oid查询<br>
     * 结果为null时抛出<br>
     *
     * @param oid 主键Oid
     * @return
     * @author gulihua
     */
    Admin queryAdminByOid(Long oid) throws JwBlogException;


    /**
     * 通过LoginId(名字:username)查询<br>
     * 结果为null时抛出<br>
     *
     * @param loginID 名字
     * @return
     * @author gulihua
     */
    Admin queryAdminByLoginId(String loginID) throws JwBlogException;


    /**
     * 通过username查询
     * 结果为null时不抛出<br>
     *
     * @param username 名字
     * @return
     * @author gulihua
     */
    Admin queryAdminByUsername(String username);


    /**
     * 通过邮箱查询<br>
     * 结果为null时抛出<br>
     *
     * @param email 邮箱
     * @return
     * @author gulihua
     */
    Admin queryAdminByEmail(String email) throws JwBlogException;
}
