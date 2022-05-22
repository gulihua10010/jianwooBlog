package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.dao.base.UserProfileTransDao;
import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.UserBaseService;
import cn.jianwoo.blog.service.biz.UserBizService;
import cn.jianwoo.blog.service.bo.UserTmpBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-17 13:37
 */
@Slf4j
@Service
public class UserBizServiceImpl implements UserBizService {
    @Autowired
    private UserBaseService userBaseService;
    @Autowired
    private UserProfileTransDao userProfileTransDao;

    @Override
    public void doCreateOrUpdateUser(String ip, UserTmpBO userTmpBO) throws JwBlogException {
        UserProfile userProfile = userProfileTransDao.queryOneUserProfileByIp(ip);
        if (userProfile == null) {
            userBaseService.doCreateUser(ip, userTmpBO);
        } else {
            userBaseService.doUpdateUser(ip, userTmpBO);

        }

    }

    @Override
    public void doCreateOrUpdateUser(String ip) throws JwBlogException {
        UserProfile userProfile = userProfileTransDao.queryOneUserProfileByIp(ip);
        if (userProfile == null) {
            userBaseService.doCreateUser(ip);
        }
    }
}
