package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AsyncProcAutoTaskQueryDao;
import cn.jianwoo.blog.dao.base.mapper.AsyncProcAutoTaskMapper;
import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("asyncProcAutoTaskQueryDao")
public class AsyncProcAutoTaskQueryDaoImpl implements AsyncProcAutoTaskQueryDao {
    @Autowired
    AsyncProcAutoTaskMapper asyncProcAutoTaskMapper;

    @Override
    public AsyncProcAutoTask queryAsyncProcAutoTaskByPrimaryKey(Long taskId) throws DaoException {
        AsyncProcAutoTask record = asyncProcAutoTaskMapper.selectByPrimaryKey(taskId);
        if(null == record){
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }
}