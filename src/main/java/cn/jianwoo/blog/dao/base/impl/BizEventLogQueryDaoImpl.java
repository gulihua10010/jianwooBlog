package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.BizEventLogQueryDao;
import cn.jianwoo.blog.dao.base.mapper.BizEventLogMapper;
import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.entity.example.BizEventLogExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bizEventLogQueryDao")
public class BizEventLogQueryDaoImpl implements BizEventLogQueryDao {
    @Autowired
    BizEventLogMapper bizEventLogMapper;

    @Override
    public BizEventLog queryBizEventLogByPrimaryKey(Long oid) throws DaoException {
        BizEventLog record = bizEventLogMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<BizEventLog> queryAllList() {
        BizEventLogExample example = new BizEventLogExample();
        example.setOrderByClause("UPDATE_TIME DESC");
        return bizEventLogMapper.selectByExample(example);
    }
}