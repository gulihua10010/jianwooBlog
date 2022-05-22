package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AccessIpCtrlQueryDao;
import cn.jianwoo.blog.dao.base.mapper.AccessIpCtrlMapper;
import cn.jianwoo.blog.entity.AccessIpCtrl;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accessIpCtrlQueryDao")
public class AccessIpCtrlQueryDaoImpl implements AccessIpCtrlQueryDao {
    @Autowired
    AccessIpCtrlMapper accessIpCtrlMapper;

    @Override
    public AccessIpCtrl queryAccessIpCtrlByPrimaryKey(Long oid) throws DaoException {
        AccessIpCtrl record = accessIpCtrlMapper.selectByPrimaryKey(oid);
        if(null == record){
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}