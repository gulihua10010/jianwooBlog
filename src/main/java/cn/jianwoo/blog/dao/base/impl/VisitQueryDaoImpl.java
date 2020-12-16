package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.VisitQueryDao;
import cn.jianwoo.blog.dao.base.mapper.VisitMapper;
import cn.jianwoo.blog.entity.Visit;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("visitQueryDao")
public class VisitQueryDaoImpl implements VisitQueryDao {
    @Autowired
    VisitMapper visitMapper;

    @Override
    public Visit queryVisitByPrimaryKey(Long oid) throws DaoException {
        Visit record = visitMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}