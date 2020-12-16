package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.ModuleQueryDao;
import cn.jianwoo.blog.dao.base.mapper.ModuleMapper;
import cn.jianwoo.blog.entity.Module;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("moduleQueryDao")
public class ModuleQueryDaoImpl implements ModuleQueryDao {
    @Autowired
    ModuleMapper moduleMapper;

    @Override
    public Module queryModuleByPrimaryKey(Long oid) throws DaoException {
        Module record = moduleMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}