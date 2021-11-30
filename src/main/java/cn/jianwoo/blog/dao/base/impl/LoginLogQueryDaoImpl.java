package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.LoginLogQueryDao;
import cn.jianwoo.blog.dao.base.mapper.LoginLogMapper;
import cn.jianwoo.blog.entity.LoginLog;
import cn.jianwoo.blog.entity.example.LoginLogExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loginLogQueryDao")
public class LoginLogQueryDaoImpl implements LoginLogQueryDao {
    @Autowired
    LoginLogMapper loginLogMapper;

    @Override
    public LoginLog queryLoginLogByPrimaryKey(Long oid) throws DaoException {
        LoginLog record = loginLogMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<LoginLog> queryAllList() {
        LoginLogExample example = new LoginLogExample();
        example.setOrderByClause("UPDATE_TIME DESC");
        return loginLogMapper.selectByExample(example);
    }
}