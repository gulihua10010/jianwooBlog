package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.EmailQueryDao;
import cn.jianwoo.blog.dao.base.mapper.EmailMapper;
import cn.jianwoo.blog.entity.Email;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailQueryDao")
public class EmailQueryDaoImpl implements EmailQueryDao {
    @Autowired
    EmailMapper emailMapper;

    @Override
    public Email queryEmailByPrimaryKey(Long oid) throws DaoException {
        Email record = emailMapper.selectByPrimaryKey(oid);
        if(null == record){
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}