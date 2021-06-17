package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.Constants;
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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AdminBizServiceImpl implements AdminBizService {
    @Autowired
    private AdminTransDao adminTransDao;
    @Autowired
    private CacheStore<String, Object> jwCacheStore;
    @Value("${access.token.expired.seconds}")
    private Long accessTokenExpiredSeconds;

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
            log.error("AdminBizServiceImpl.register exec failed, e:\n", e);
            throw AdminBizException.CREATE_FAILED_EXCEPTION.format(name).print();

        }
        log.info("========>> Admin [username={}] register successfully!!", name);
    }


    @Override
    public AuthToken authLogin(String name, String ip) throws JwBlogException {

        log.info("========>> Start admin login,name= {},ip= {}", name, ip);

        Admin admin = adminTransDao.queryAdminByName(name);
        //security已经做过校验，这里不再做
//        if (admin == null) {
//            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(name).print();
//        }
//        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
//        if (!pwd.equals(admin.getPassword())) {
//            log.warn("Admin [name = {}, ip = {}] login failed", name, ip);
//            throw AdminBizException.USERNAME_OR_PASSWORD_INCORRECT.print();
//
//        }

        admin.setUpdateDate(new Date());
        admin.setIp(ip);
        admin.setLastLoginIp(ip);
        admin.setLastLoginTime(new Date());
        try {
            adminTransDao.doUpdateByPrimaryKeySelective(admin);
        } catch (DaoException e) {
            log.error("AdminBizServiceImpl.authLogin exec failed, e:\n", e);
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.format(name).print();
        }

        //生成jwt token对象
        AuthToken authToken = JwBuilder.of(AuthToken::new)
                .with(AuthToken::setUid, admin.getOid())
                .with(AuthToken::setAccessToken, JwUtil.randomUUIDWithoutDash())
                .with(AuthToken::setRefreshToken, JwUtil.randomUUIDWithoutDash())
                .with(AuthToken::setExpiredIn, accessTokenExpiredSeconds).build();
        //生成user对象(移除密码等敏感信息)
        UserBO user = JwBuilder.of(UserBO::new)
                .with(UserBO::setId, admin.getOid())
                .with(UserBO::setName, admin.getUsername()).build();
        jwCacheStore.put(SecurityUtils.buildAccessTokenKey(user), authToken.getAccessToken(), accessTokenExpiredSeconds, TimeUnit.SECONDS);
        Map<Long, Object> userLogin = (Map<Long, Object>) jwCacheStore.get(Constants.LOGIN_USER_STATUS).orElse(new HashMap<>());
        userLogin.put(admin.getOid(), true);
        jwCacheStore.put(Constants.LOGIN_USER_STATUS, userLogin);

        log.info("========>> Admin [username={}, ip={}] login successfully!!!", name, ip);

        return authToken;

    }
}