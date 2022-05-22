package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AsyncProcTaskTypeCfgQueryDao;
import cn.jianwoo.blog.dao.base.mapper.AsyncProcTaskTypeCfgMapper;
import cn.jianwoo.blog.entity.AsyncProcTaskTypeCfg;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("asyncProcTaskTypeCfgQueryDao")
public class AsyncProcTaskTypeCfgQueryDaoImpl implements AsyncProcTaskTypeCfgQueryDao {
    @Autowired
    AsyncProcTaskTypeCfgMapper asyncProcTaskTypeCfgMapper;

    @Override
    public AsyncProcTaskTypeCfg queryAsyncProcTaskTypeCfgByPrimaryKey(String taskType) throws DaoException {
        AsyncProcTaskTypeCfg record = asyncProcTaskTypeCfgMapper.selectByPrimaryKey(taskType);
        if(null == record){
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}