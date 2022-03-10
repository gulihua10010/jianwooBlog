package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.AdminBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.AdminBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gulihua
 * @Description
 * @date 2022-02-28 12:01
 */
@Service
public class AdminBaseServiceImpl implements AdminBaseService {
    @Autowired
    private AdminTransDao adminTransDao;

    @Override
    public Admin queryAdminByOid(Long oid) throws JwBlogException {
        try {
            return adminTransDao.queryAdminByPrimaryKey(oid);
        } catch (DaoException e) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format("Oid=" + oid).print();
        }
    }

    @Override
    public Admin queryAdminByLoginId(String loginID) throws JwBlogException {
        try {
            return adminTransDao.queryAdminByLoginId(loginID);
        } catch (DaoException e) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(loginID).print();
        }
    }

    @Override
    public Admin queryAdminByUsername(String username) {
        return adminTransDao.queryAdminByUsername(username);
    }
}
