package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.AdminBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.biz.EmailBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.service.bo.UserBO;
import cn.jianwoo.blog.task.AsyncTask;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
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
    @Autowired
    private AsyncTask asyncTask;
    @Autowired
    private EmailBizService emailBizService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String name, String password, String ip) throws JwBlogException {
        log.info("========>> Start admin register,name={}", name);
        Admin existAdmin = adminTransDao.queryAdminByName(name);
        if (existAdmin != null) {
            throw AdminBizException.HAS_EXIST_EXCEPTION_CN.format(name).print();
        }
        Date date = DateUtil.getNow();

        Admin admin = new Admin();
        admin.setUsername(name);
        admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        admin.setRegisterIp(ip);
        admin.setCreateTime(date);
        admin.setUpdateTime(date);
        try {
            adminTransDao.doInsertSelective(admin);
        } catch (DaoException e) {
            log.error("AdminBizServiceImpl.register exec failed, e:\n", e);
            throw AdminBizException.CREATE_FAILED_EXCEPTION.format(name).print();

        }

        //执行异步任务
        asyncTask.execAdminUserRegIpAreaTask(admin.getOid());
        log.info("========>> Admin [username={}] register successfully!!", name);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
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
        Admin updAdmin = new Admin();
        updAdmin.setOid(admin.getOid());
        updAdmin.setUpdateTime(new Date());
        updAdmin.setLastLoginIp(ip);
        updAdmin.setLastLoginTime(new Date());
        try {
            adminTransDao.doUpdateByPrimaryKeySelective(updAdmin);
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
        String accessCacheKey = MessageFormat.format(CacaheKeyConstants.TOKEN_ACCESS_CACHE, user.getId());
        jwCacheStore.put(accessCacheKey, authToken.getAccessToken(), accessTokenExpiredSeconds, TimeUnit.SECONDS);
        String loginIdCacheKey = MessageFormat.format(CacaheKeyConstants.LOGIN_USER_STATUS, user.getId());
        jwCacheStore.put(loginIdCacheKey, true);

        String loginIDNameKey = MessageFormat.format(CacaheKeyConstants.ADMIN_OID_NAME_KEY, user.getId());
        jwCacheStore.put(loginIDNameKey, admin.getUsername());
        //执行异步任务
        asyncTask.execAdminUserLoginIpAreaTask(admin.getOid());
        log.info("========>> Admin [username={}, ip={}] login successfully!!!", name, ip);

        return authToken;

    }

    @Override
    public AdminBO queryAdminByName(String name) throws JwBlogException {
        String cacheKey = MessageFormat.format(CacaheKeyConstants.ADMIN_NAME_KEY, name);

        if (jwCacheStore.hasKey(cacheKey)) {
            Optional<Object> adminBO = jwCacheStore.get(cacheKey);
            if (adminBO.isPresent()) {
                return (AdminBO) adminBO.get();
            } else {
                throw AdminBizException.NOT_EXIST_EXCEPTION.format(name).print();
            }
        } else {
            Admin admin = adminTransDao.queryAdminByName(name);
            if (admin == null) {
                throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(name).print();
            }
            AdminBO adminBO = new AdminBO();
            BeanUtils.copyProperties(admin, adminBO);
            jwCacheStore.put(cacheKey, adminBO);
            return adminBO;
        }

    }

    @Override
    public AdminBO queryAdminByOid(Long oid) throws JwBlogException {
        String cacheKey = MessageFormat.format(CacaheKeyConstants.ADMIN_OID_KEY, oid);
        if (jwCacheStore.hasKey(cacheKey)) {
            Optional<Object> adminBO = jwCacheStore.get(cacheKey);
            if (adminBO.isPresent()) {
                return (AdminBO) adminBO.get();
            } else {
                throw AdminBizException.NOT_EXIST_EXCEPTION.format(oid).print();
            }
        } else {
            try {
                Admin admin = adminTransDao.queryAdminByPrimaryKey(oid);
                AdminBO adminBO = JwBuilder.of(AdminBO::new)
                        .with(AdminBO::setOid, admin.getOid())
                        .with(AdminBO::setUsername, admin.getUsername()).build();
                jwCacheStore.put(cacheKey, adminBO);
                return adminBO;
            } catch (DaoException e) {
                throw AdminBizException.NOT_EXIST_EXCEPTION.format(oid).print();
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doChangePassword(String loginID, String oldPassword, String newPassword) throws JwBlogException {
        log.info("========>> Start to change password , loginID= {}.", loginID);

        Admin admin = adminTransDao.queryAdminByName(loginID);
        if (admin == null) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(loginID).print();
        }
        String pwd = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!pwd.equals(admin.getPassword())) {
            log.warn("Change password failed, the old password[{}] is incorrect.", oldPassword);
            throw AdminBizException.OLD_PASSWORD_INCORRECT.print();
        }
        if (oldPassword.equals(newPassword)) {
            log.warn("Change password failed, the old password[{}] cannot be same as new password.", oldPassword);
            throw AdminBizException.OLD_PASSWORD_NOT_SAME_AS_NEW.print();
        }
        Admin newAdmin = new Admin();
        newAdmin.setOid(admin.getOid());
        newAdmin.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        newAdmin.setUpdateTime(new Date());
        try {
            adminTransDao.doUpdateByPrimaryKeySelective(newAdmin);
        } catch (DaoException e) {
            log.warn("Change password failed. e:\r\n", e);
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.print();
        }
        log.info("========>> Change password End, loginID= {}", loginID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doChangePassword4Forget(String loginID, String newPassword, String verifyCode) throws JwBlogException {
        log.info("========>> Start to change password , loginID= {}", loginID);

        Admin admin = adminTransDao.queryAdminByName(loginID);
        if (admin == null) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(loginID).print();
        }
        String loginIDNameKey = MessageFormat.format(CacaheKeyConstants.VERIFY_CODE_LOGIN_ID, loginID);
        Optional verifyConfirmCodeOpt = jwCacheStore.get(loginIDNameKey);
        if (!verifyConfirmCodeOpt.isPresent()) {
            throw AdminBizException.VERIFY_CODE_INCORRECT.print();
        }
        String verifyConfirmCode = (String) verifyConfirmCodeOpt.get();
        if (null == verifyCode || !Objects.equals(verifyCode, verifyConfirmCode)) {
            throw AdminBizException.VERIFY_CODE_INCORRECT.print();
        }
        String newPwd = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        if (admin.getPassword().equals(newPwd)) {
            log.warn("Change password failed, the old password[{}] cannot be same as new password.", newPassword);
            throw AdminBizException.OLD_PASSWORD_NOT_SAME_AS_NEW.print();
        }
        Admin newAdmin = new Admin();
        newAdmin.setOid(admin.getOid());
        newAdmin.setPassword(newPwd);
        newAdmin.setUpdateTime(new Date());
        try {
            adminTransDao.doUpdateByPrimaryKeySelective(newAdmin);
        } catch (DaoException e) {
            log.warn("Change password failed. e:\r\n", e);
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.print();
        }
        log.info("========>> Change password End, loginID= {}", loginID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doChangePassword4ForgetCheck(String loginID, String email) throws JwBlogException {
        log.info("========>> Start to change password Check, loginID= {}", loginID);
        Admin admin = adminTransDao.queryAdminByName(loginID);
        if (admin == null) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(loginID).print();
        }
        if (email.equals(admin.getUserEmail())) {
            throw AdminBizException.EMAIL_INCORRECT.print();
        }
        emailBizService.sendEmail4ForgetPwd(loginID, email);
        log.info("========>> End to change password Check, loginID= {}", loginID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSaveEditInfo(AdminBO adminBO) throws JwBlogException {
        log.info("========>> Start to Save user edit info, loginID= {}", adminBO.getUsername());
        Admin admin = adminTransDao.queryAdminByName(adminBO.getUsername());
        if (admin == null) {
            throw AdminBizException.NOT_EXIST_EXCEPTION_CN.format(adminBO.getUsername()).print();
        }
        Admin newAdmin = JwBuilder.of(Admin::new)
                .with(Admin::setOid, admin.getOid())
                .with(Admin::setUserNick, adminBO.getUserNick())
                .with(Admin::setUserEmail, adminBO.getUserEmail())
                .with(Admin::setUserPhone, adminBO.getUserPhone())
                .with(Admin::setUserSex, adminBO.getUserSex())
                .with(Admin::setUpdateTime, new Date())
                .build();

        try {
            adminTransDao.doUpdateByPrimaryKeySelective(newAdmin);
        } catch (DaoException e) {
            log.warn("Change password failed. e:\r\n", e);
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.print();
        }
        String nameCacheKey = MessageFormat.format(CacaheKeyConstants.ADMIN_NAME_KEY, admin.getUsername());
        String oidCacheKey = MessageFormat.format(CacaheKeyConstants.ADMIN_OID_KEY, admin.getOid());

        jwCacheStore.delete(nameCacheKey);
        jwCacheStore.delete(oidCacheKey);
        log.info("========>> End to Save user edit info, loginID= {}", adminBO.getUsername());
    }
}