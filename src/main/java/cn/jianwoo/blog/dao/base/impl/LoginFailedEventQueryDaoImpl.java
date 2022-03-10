package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.LoginFailedEventQueryDao;
import cn.jianwoo.blog.dao.base.mapper.LoginFailedEventMapper;
import cn.jianwoo.blog.entity.LoginFailedEvent;
import cn.jianwoo.blog.entity.example.LoginFailedEventExample;
import cn.jianwoo.blog.enums.LoginFailedEventStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginFailedEventQueryDao")
public class LoginFailedEventQueryDaoImpl implements LoginFailedEventQueryDao {
    @Autowired
    LoginFailedEventMapper loginFailedEventMapper;

    @Override
    public LoginFailedEvent queryLoginFailedEventByPrimaryKey(Long oid) throws DaoException {
        LoginFailedEvent record = loginFailedEventMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public LoginFailedEvent queryEffectiveFailed(String loginId, String ip) {
        LoginFailedEventExample eventExample = new LoginFailedEventExample();
        eventExample.createCriteria().andLoginIdEqualTo(loginId).andLoginIpEqualTo(ip)
                .andStatusEqualTo(LoginFailedEventStatusEnum.VALID.getValue());
        List<LoginFailedEvent> list = loginFailedEventMapper.selectByExample(eventExample);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}