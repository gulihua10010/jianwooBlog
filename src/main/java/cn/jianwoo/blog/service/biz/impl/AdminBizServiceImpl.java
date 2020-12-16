package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.AdminBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.validation.BizValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class AdminBizServiceImpl implements AdminBizService {
    private final Logger logger = LoggerFactory.getLogger(AdminBizServiceImpl.class);
    @Autowired
    AdminTransDao adminTransDao;

    @Override
    public boolean register(String name, String password) throws JwBlogException {
        BizValidation.paramValidate(name, "name");
        BizValidation.paramValidate(password, "password");
        logger.info("========>> Start admin register,name={}", name);
        Admin existAdmin = adminTransDao.queryAdminByName(name);
        if (existAdmin != null) {
            throw AdminBizException.HAS_EXIST_EXCEPTION.format(name).print();
        }

        Admin admin = new Admin();
        admin.setUsername(name);
        admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        admin.setCreateDate(new Date());
        admin.setUpdateDate(new Date());
        try {
            adminTransDao.doInsertSelective(admin);
        } catch (DaoException e) {
            throw AdminBizException.CREATE_FAILED_EXCEPTION.format(name).print();

        }
        logger.info("========>> Admin [username={}] register successfully!!", name);
        return true;
    }


    @Override
    public Admin login(String name, String password, String ip) throws JwBlogException {
        BizValidation.paramValidate(name, "name");
        BizValidation.paramValidate(password, "password");

        logger.info("========>> Start admin login,name= {},ip= {}", name, ip);

        Admin admin = adminTransDao.queryAdminByName(name);
        if (admin == null) {
            throw AdminBizException.NOT_EXIST_EXCEPTION.format(name).print();
        }
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(admin.getPassword())) {
            logger.warn("Admin [name = {}, ip = {}] login failed", name, ip);
            return null;
        }

        admin.setUpdateDate(new Date());
        admin.setIp(ip);
        admin.setLastLoginIp(ip);
        admin.setLastLoginTime(new Date());
        try {
            adminTransDao.doUpdateByPrimaryKeySelective(admin);
        } catch (DaoException e) {
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.format(name).print();

        }
        logger.info("========>> Admin [username={}, ip={}] login successfully!!1", name, ip);

        return admin;
    }
}