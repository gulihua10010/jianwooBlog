package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.enums.LoginEventTypeEnum;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.event.LoginLogEvent;
import cn.jianwoo.blog.exception.AdminBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.base.AdminBaseService;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.service.bo.ForgetPwdResBO;
import cn.jianwoo.blog.service.bo.UserBO;
import cn.jianwoo.blog.service.notify.NotifyMsgService;
import cn.jianwoo.blog.task.bo.TaskDataD0020BO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
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
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;
    @Autowired
    private NotifyMsgService emailNotifyService;
    @Autowired
    private AdminBaseService adminBaseService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    protected HttpServletRequest request;
    @Value("${jw.emailtpl.code.forgetPassword}")
    private String forgetPwdCode;
    @Autowired
    private TransactionUtils transactionUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(String username, String password, String ip) throws JwBlogException {
        log.info("========>> Start admin register,name={}", username);
        Admin existAdmin = adminBaseService.queryAdminByUsername(username);
        if (existAdmin != null) {
            throw AdminBizException.HAS_EXIST_EXCEPTION_CN.format(username).print();
        }
        Date date = DateUtil.getNow();

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        admin.setRegisterIp(ip);
        admin.setCreateTime(date);
        admin.setUpdateTime(date);
        try {
            adminTransDao.doInsertSelective(admin);
        } catch (DaoException e) {
            log.error("AdminBizServiceImpl.register exec failed, e:\n", e);
            throw AdminBizException.CREATE_FAILED_EXCEPTION.format(username).print();

        }


        //执行异步任务
        TaskDataD0020BO taskDataD0020BO = new TaskDataD0020BO();
        taskDataD0020BO.setOid(admin.getOid());
        taskDataD0020BO.setIp(admin.getRegisterIp());
        taskDataD0020BO.setAsyncIpType(AsyncIpEnum.ADMIN_REG.name());
        Long taskId = null;
        try {

            taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0020.getValue(), JSONObject.toJSONString(taskDataD0020BO));
        } catch (JwBlogException e) {
            log.error("\r\n>>AdminBizServiceImpl.register exec failed, e\r\n", e);
        }
        if (taskId != null) {
            transactionUtils.doTriggerTaskAfterCommit(taskId);
        }
        log.info("========>> Admin [username={}] register successfully!!", username);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuthToken authLogin(String loginID, String ip) throws JwBlogException {

        log.info("========>> Start admin login,name= {},ip= {}", loginID, ip);

        Admin admin = null;
        if (JwUtil.isEmail(loginID)) {
            admin = adminBaseService.queryAdminByEmail(loginID);
        } else {
            admin = adminBaseService.queryAdminByLoginId(loginID);
        }
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
//        updAdmin.setUpdateTime(new Date());
        updAdmin.setLastLoginIp(ip);
        updAdmin.setLastLoginTime(new Date());
        try {
            adminTransDao.doUpdateByPrimaryKeySelective(updAdmin);
        } catch (DaoException e) {
            log.error("AdminBizServiceImpl.authLogin exec failed, e:\n", e);
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.format(loginID).print();
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
        TaskDataD0020BO taskDataD0020BO = new TaskDataD0020BO();
        taskDataD0020BO.setOid(updAdmin.getOid());
        taskDataD0020BO.setIp(updAdmin.getLastLoginIp());
        taskDataD0020BO.setAsyncIpType(AsyncIpEnum.ADMIN_LOGIN.name());
        Long taskId = null;
        try {

            taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0020.getValue(), JSONObject.toJSONString(taskDataD0020BO));
        } catch (JwBlogException e) {
            log.error("\r\n>>AdminBizServiceImpl.authLogin exec failed, e\r\n", e);
        }
        if (taskId != null) {
            transactionUtils.doTriggerTaskAfterCommit(taskId);
        }
        log.info("========>> Admin [username={}, ip={}] login successfully!!!", loginID, ip);

        return authToken;

    }

    @Override
    public AdminBO queryAdminInfoByLoginId(String loginID) throws JwBlogException {
        String cacheKey = MessageFormat.format(CacaheKeyConstants.ADMIN_NAME_KEY, loginID);

        if (jwCacheStore.hasKey(cacheKey)) {
            Optional<Object> adminBO = jwCacheStore.get(cacheKey);
            if (adminBO.isPresent()) {
                return (AdminBO) adminBO.get();
            } else {
                throw AdminBizException.NOT_EXIST_EXCEPTION.format(loginID).print();
            }
        } else {
            Admin admin = adminBaseService.queryAdminByLoginId(loginID);
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
            Admin admin = adminBaseService.queryAdminByOid(oid);
            AdminBO adminBO = JwBuilder.of(AdminBO::new)
                    .with(AdminBO::setOid, admin.getOid())
                    .with(AdminBO::setUsername, admin.getUsername()).build();
            jwCacheStore.put(cacheKey, adminBO);
            return adminBO;

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doChangePassword(String loginID, String oldPassword, String newPassword) throws JwBlogException {
        log.info("========>> Start to change password , loginID= {}.", loginID);

        Admin admin = adminBaseService.queryAdminByLoginId(loginID);
        String pwd = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!pwd.equals(admin.getPassword())) {
            log.warn("Change password failed, the old password[{}] is incorrect.", oldPassword);
            registerLog(admin.getUsername(), AdminBizException.OLD_PASSWORD_INCORRECT.getMsg(), LoginEventTypeEnum.CHANGE_PASSWORD);
            throw AdminBizException.OLD_PASSWORD_INCORRECT.print();
        }
        if (oldPassword.equals(newPassword)) {
            log.warn("Change password failed, the old password[{}] cannot be same as new password.", oldPassword);
            registerLog(admin.getUsername(), AdminBizException.OLD_PASSWORD_NOT_SAME_AS_NEW.getMsg(), LoginEventTypeEnum.CHANGE_PASSWORD);
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
    public ForgetPwdResBO doForgetCaptchaAuth(String loginID, String email, String captchaCode) throws JwBlogException {
        ForgetPwdResBO resBO = ForgetPwdResBO.getInstance();
        Admin admin = adminBaseService.queryAdminByLoginId(loginID);
        if (!email.equals(admin.getUserEmail())) {
            registerLog(admin.getUsername(), AdminBizException.EMAIL_INCORRECT.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
            throw AdminBizException.EMAIL_INCORRECT.print();
        }
        resBO.setLoginIdEncrypt(JwUtil.encrypt(loginID));
        resBO.setCaptchaCodeEncrypt(JwUtil.encrypt(captchaCode));

        String loginIDNameKey = MessageFormat.format(CacaheKeyConstants.VERIFY_CODE_LOGIN_ID, loginID);
        Optional verifyConfirmCodeOpt = jwCacheStore.get(loginIDNameKey);
        if (!verifyConfirmCodeOpt.isPresent()) {
            resBO.setReason(AdminBizException.VERIFY_CODE_INCORRECT.getMsg());
            resBO.setCode(AdminBizException.VERIFY_CODE_INCORRECT.getCode());
            registerLog(admin.getUsername(), AdminBizException.VERIFY_CODE_INCORRECT.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
            return resBO;
        }
        String captchaConfirmCode = (String) verifyConfirmCodeOpt.get();
        if (null == captchaCode || !Objects.equals(captchaCode, captchaConfirmCode)) {
            registerLog(admin.getUsername(), AdminBizException.VERIFY_CODE_INCORRECT.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
            resBO.setReason(AdminBizException.VERIFY_CODE_INCORRECT.getMsg());
        }
        return resBO;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doChangePassword4Forget(String loginIdEncrypt, String newPasswordEncrypt, String captchaCodeEncrypt) throws JwBlogException {
        log.info("========>> Start to change password , loginID= {}", loginIdEncrypt);
        String loginID = JwUtil.decrypt(loginIdEncrypt);
        String captchaCode = JwUtil.decrypt(captchaCodeEncrypt);
        String newPassword = JwUtil.decrypt(newPasswordEncrypt);

        Admin admin = adminBaseService.queryAdminByLoginId(loginID);

        String loginIDNameKey = MessageFormat.format(CacaheKeyConstants.VERIFY_CODE_LOGIN_ID, loginID);
        Optional verifyConfirmCodeOpt = jwCacheStore.get(loginIDNameKey);
        if (!verifyConfirmCodeOpt.isPresent()) {
            registerLog(admin.getUsername(), AdminBizException.PAGE_TIMEOUT.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
            throw AdminBizException.PAGE_TIMEOUT.print();
        }
        String verifyConfirmCode = (String) verifyConfirmCodeOpt.get();
        if (null == captchaCode || !Objects.equals(captchaCode, verifyConfirmCode)) {
            registerLog(admin.getUsername(), AdminBizException.VERIFY_CODE_INCORRECT.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
            throw AdminBizException.VERIFY_CODE_INCORRECT.print();
        }
        String newPwd = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        if (admin.getPassword().equals(newPwd)) {
            log.warn("Change password failed, the old password[{}] cannot be same as new password.", newPassword);
            registerLog(admin.getUsername(), AdminBizException.OLD_PASSWORD_NOT_SAME_AS_NEW.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
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
            registerLog(admin.getUsername(), AdminBizException.MODIFY_FAILED_EXCEPTION.getMsg(), LoginEventTypeEnum.FORGET_PASSWORD);
            throw AdminBizException.MODIFY_FAILED_EXCEPTION.print();
        }

        registerLog(admin.getUsername(), null, LoginEventTypeEnum.FORGET_PASSWORD);
        jwCacheStore.delete(loginIDNameKey);
        log.info("========>> Change password End, loginID= {}", loginID);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doSaveEditInfo(AdminBO adminBO) throws JwBlogException {
        log.info("========>> Start to Save user edit info, loginID= {}", adminBO.getUsername());
        Admin admin = adminBaseService.queryAdminByLoginId(adminBO.getUsername());
        Admin newAdmin = JwBuilder.of(Admin::new)
                .with(Admin::setOid, admin.getOid())
                .with(Admin::setUserNick, adminBO.getUserNick())
                .with(Admin::setUserEmail, adminBO.getUserEmail())
                .with(Admin::setUserPhone, adminBO.getUserPhone())
                .with(Admin::setUserSex, adminBO.getUserSex())
                .with(Admin::setAvatarSrc, adminBO.getAvatarSrc())
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
        registerLog(admin.getUsername(), null, LoginEventTypeEnum.EDIT_USER);

        jwCacheStore.delete(nameCacheKey);
        jwCacheStore.delete(oidCacheKey);
        log.info("========>> End to Save user edit info, loginID= {}", adminBO.getUsername());
    }

    @Override
    public void doSendCaptcha4Forget(String loginID, String email) throws JwBlogException {
        log.info("========>> Start to change password Check, loginID= {}", loginID);

        Admin admin = adminBaseService.queryAdminByLoginId(loginID);
        if (!email.equals(admin.getUserEmail())) {
            registerLog(admin.getUsername(), AdminBizException.EMAIL_INCORRECT.getMsg(),
                    LoginEventTypeEnum.FORGET_PASSWORD);
            throw AdminBizException.EMAIL_INCORRECT.print();
        }
        String captchaCode = JwUtil.generateVerifyCode(6);
        String loginIDNameKey = MessageFormat.format(CacaheKeyConstants.VERIFY_CODE_LOGIN_ID, loginID);
        jwCacheStore.put(loginIDNameKey, captchaCode, 1, TimeUnit.HOURS);
        JSONObject param = new JSONObject();
        param.put("username", loginID);
        param.put("verifyCode", captchaCode);
        //忘记密码发验证码
        emailNotifyService.doSend(forgetPwdCode, param, email);
        log.info("========>> End to change password Check, loginID= {}", loginID);
    }


    private void registerLog(String loginID, String reason, LoginEventTypeEnum eventType) {
        LoginLogEvent event = new LoginLogEvent(this, loginID, request);
        event.setEventTypeEnum(eventType);
        event.setIsSuccess(true);
        if (StringUtils.isNotBlank(reason)) {
            event.setReason(reason);
            event.setIsSuccess(false);
        }
        applicationContext.publishEvent(event);
    }


}