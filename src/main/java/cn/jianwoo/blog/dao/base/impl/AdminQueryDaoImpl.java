package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AdminQueryDao;
import cn.jianwoo.blog.dao.base.mapper.AdminMapper;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.entity.example.AdminExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminQueryDao")
public class AdminQueryDaoImpl implements AdminQueryDao {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin queryAdminByPrimaryKey(Long oid) throws DaoException {
        Admin record = adminMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }


    @Override
    public Admin queryAdminByLoginId(String loginID) throws DaoException {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(loginID);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(admins)) {
            throw DaoException.DAO_LIST_IS_NULL;
        }
        return admins.get(0);
    }

    @Override
    public Admin queryAdminByUsername(String username) {
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(admins)) {
            return admins.get(0);
        }
        return null;
    }

    @Override
    public Admin queryAdminByEmail(String email) throws DaoException {
        AdminExample example = new AdminExample();
        example.createCriteria().andUserEmailEqualTo(email);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(admins)) {
            throw DaoException.DAO_LIST_IS_NULL;
        }
        return admins.get(0);
    }
}