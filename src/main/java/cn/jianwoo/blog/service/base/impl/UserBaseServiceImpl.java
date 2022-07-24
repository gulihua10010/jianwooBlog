package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.UserProfileTransDao;
import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.enums.AsyncIpEnum;
import cn.jianwoo.blog.enums.TaskTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.UserProfileBizException;
import cn.jianwoo.blog.service.base.AsyncAutoTaskBaseService;
import cn.jianwoo.blog.service.base.UserBaseService;
import cn.jianwoo.blog.service.bo.UserTmpBO;
import cn.jianwoo.blog.task.bo.TaskDataD0020BO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.util.TransactionUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-13 21:31
 */
@Service
@Slf4j
public class UserBaseServiceImpl implements UserBaseService {
    @Autowired
    private UserProfileTransDao userProfileTransDao;

    @Autowired
    private AsyncAutoTaskBaseService asyncAutoTaskBaseService;

    @Autowired
    private TransactionUtils transactionUtils;

    @Override
    public UserProfile queryUserByUsername(String username) {
        return userProfileTransDao.queryUserProfileByUsername(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateUser(UserProfile userProfile) throws JwBlogException {
        Date now = DateUtil.getNow();
        userProfile.setCreateTime(now);
        userProfile.setUpdateTime(now);

        try {
            userProfileTransDao.doInsertSelective(userProfile);
        } catch (DaoException e) {
            throw UserProfileBizException.CREATE_FAILED_EXCEPTION.format(userProfile.getUsername()).print();
        }

        if (StringUtils.isNotBlank(userProfile.getRegisterIp())) {

            //执行异步任务
            TaskDataD0020BO taskDataD0020BO = new TaskDataD0020BO();
            taskDataD0020BO.setOid(userProfile.getOid());
            taskDataD0020BO.setIp(userProfile.getRegisterIp());
            taskDataD0020BO.setAsyncIpType(AsyncIpEnum.USER_REG.name());

            Long taskId = null;
            try {

                taskId = asyncAutoTaskBaseService.doCreateTask(TaskTypeEnum.D0020.getValue(), JSONObject.toJSONString(taskDataD0020BO));
            } catch (JwBlogException e) {
                log.error("\r\n>>UserBaseServiceImpl.doCreateUser exec failed, e\r\n", e);
            }
            if (taskId != null) {
                transactionUtils.doTriggerTaskAfterCommit(taskId);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateUser(UserProfile userProfile) throws JwBlogException {
        Date now = DateUtil.getNow();
//        userProfile.setCreateTime(now);
        userProfile.setUpdateTime(now);
        try {
            userProfileTransDao.doUpdateByPrimaryKeySelective(userProfile);
        } catch (DaoException e) {
            throw UserProfileBizException.MODIFY_FAILED_EXCEPTION.format(userProfile.getUsername()).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateUser(String ip) throws JwBlogException {
        String userId = JwUtil.generateUserId();
        String username = JwUtil.generateUsername(ip);
        String avatar = JwUtil.fetchUserAvatar();
        UserProfile userProfile = JwBuilder.of(UserProfile::new)
                .with(UserProfile::setUserId, userId)
                .with(UserProfile::setUsername, username)
                .with(UserProfile::setAvatarSrc, avatar)
                .with(UserProfile::setRegisterIp, ip)
                .build();
        doCreateUser(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateUser(String ip, UserTmpBO userTmpBO) throws JwBlogException {
        String userId = JwUtil.generateUserId();
        String username = JwUtil.generateUsername(ip);
        String avatar = JwUtil.fetchUserAvatar();
        userTmpBO.setUserId(userId);
        userTmpBO.setUsername(username);
        userTmpBO.setAvatarSrc(avatar);
        UserProfile userProfile = JwBuilder.of(UserProfile::new)
                .with(UserProfile::setUserId, userId)
                .with(UserProfile::setUsername, username)
                .with(UserProfile::setAvatarSrc, avatar)
                .with(UserProfile::setRegisterIp, ip)
                .with(UserProfile::setUserNick, userTmpBO.getNickname())
                .with(UserProfile::setUserTel, userTmpBO.getTel())
                .with(UserProfile::setUserQq, userTmpBO.getQq())
                .with(UserProfile::setUserWechat, userTmpBO.getWechat())
                .with(UserProfile::setUserWeibo, userTmpBO.getWeibo())
                .with(UserProfile::setUserEmail, userTmpBO.getEmail())
                .build();
        doCreateUser(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateUser(String ip, UserTmpBO userTmpBO) throws JwBlogException {
        UserProfile userProfile = userProfileTransDao.queryOneUserProfileByIp(ip);
        if (userProfile == null) {
            return;
        }

        userTmpBO.setUserId(userProfile.getUserId());
        userTmpBO.setUsername(userProfile.getUsername());
        userTmpBO.setAvatarSrc(userProfile.getAvatarSrc());
        UserProfile newUserProfile = JwBuilder.of(UserProfile::new)
                .with(UserProfile::setOid, userProfile.getOid())
                .with(UserProfile::setUserNick, userTmpBO.getNickname())
                .with(UserProfile::setUserTel, userTmpBO.getTel())
                .with(UserProfile::setUserQq, userTmpBO.getQq())
                .with(UserProfile::setUserWechat, userTmpBO.getWechat())
                .with(UserProfile::setUserWeibo, userTmpBO.getWeibo())
                .with(UserProfile::setUserEmail, userTmpBO.getEmail())
                .build();
        doUpdateUser(newUserProfile);
    }

    @Override
    public UserProfile queryUserByUserId(String userId) {
        return userProfileTransDao.queryUserProfileByUserId(userId);

    }
}
