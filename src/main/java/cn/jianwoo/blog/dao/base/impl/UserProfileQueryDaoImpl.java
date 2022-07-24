package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.UserProfileQueryDao;
import cn.jianwoo.blog.dao.base.mapper.UserProfileMapper;
import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.entity.example.UserProfileExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userProfileQueryDao")
public class UserProfileQueryDaoImpl implements UserProfileQueryDao {
    @Autowired
    UserProfileMapper userProfileMapper;

    @Override
    public UserProfile queryUserProfileByPrimaryKey(Long oid) throws DaoException {
        UserProfile record = userProfileMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public UserProfile queryUserProfileByUsername(String name) {
        UserProfileExample example = new UserProfileExample();
        example.createCriteria().andUsernameEqualTo(name);
        List<UserProfile> list = userProfileMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public UserProfile queryOneUserProfileByIp(String ip) {
        UserProfileExample example = new UserProfileExample();
        example.createCriteria().andRegisterIpEqualTo(ip);
        List<UserProfile> list = userProfileMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public UserProfile queryUserProfileByUserId(String userId) {
        UserProfileExample example = new UserProfileExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UserProfile> list = userProfileMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}