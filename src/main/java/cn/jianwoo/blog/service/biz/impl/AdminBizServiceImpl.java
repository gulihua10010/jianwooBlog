package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.AdminBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.security.util.SecurityUtils;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.bo.UserBO;
import cn.jianwoo.blog.util.JwUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AdminBizServiceImpl implements AdminBizService {
    @Autowired
    private AdminTransDao adminTransDao;
    @Autowired
    private CacheStore<String, String> jwCacheStore;
    @Value("${access.token.expired.seconds}")
    private int accessTokenExpiredSeconds;
    @Value("${access.token.expired.days}")
    private int accessTokenExpiredDays;

    @Override
    public void register(String name, String password) throws JwBlogException {
        log.info("========>> Start admin register,name={}", name);
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
        log.info("========>> Admin [username={}] register successfully!!", name);
    }


    @Override
    public AuthToken authLogin(String name, String password, String ip) throws JwBlogException {

        log.info("========>> Start admin login,name= {},ip= {}", name, ip);

        Admin admin = adminTransDao.queryAdminByName(name);
        if (admin == null) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(name).print();
        }
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!pwd.equals(admin.getPassword())) {
            log.warn("Admin [name = {}, ip = {}] login failed", name, ip);
            throw AdminBizException.USERNAME_OR_PASSWORD_INCORRECT.print();

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

        AuthToken authToken = JwBuilder.of(AuthToken::new)
                .with(AuthToken::setAccessToken, JwUtil.randomUUIDWithoutDash())
                .with(AuthToken::setRefreshToken, JwUtil.randomUUIDWithoutDash())
                .with(AuthToken::setExpiredIn, accessTokenExpiredSeconds).build();
        UserBO user = JwBuilder.of(UserBO::new)
                .with(UserBO::setId, admin.getOid())
                .with(UserBO::setName, admin.getUsername()).build();
        jwCacheStore.put("user", user.getId().toString());
        jwCacheStore.put(SecurityUtils.buildAccessTokenKey(user), authToken.getAccessToken(), accessTokenExpiredSeconds, TimeUnit.SECONDS);

        log.info("========>> Admin [username={}, ip={}] login successfully!!1", name, ip);

        return authToken;

    }
}