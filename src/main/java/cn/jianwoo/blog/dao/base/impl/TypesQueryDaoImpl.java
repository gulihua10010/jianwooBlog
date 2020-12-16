package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.TypesQueryDao;
import cn.jianwoo.blog.dao.base.mapper.TypesMapper;
import cn.jianwoo.blog.entity.Types;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("typesQueryDao")
public class TypesQueryDaoImpl implements TypesQueryDao {
    @Autowired
    TypesMapper typesMapper;

    @Override
    public Types queryTypesByPrimaryKey(Long oid) throws DaoException {
        Types record = typesMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}