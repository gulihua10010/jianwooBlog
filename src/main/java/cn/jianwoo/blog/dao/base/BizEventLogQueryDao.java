package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.BizEventLog;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface BizEventLogQueryDao {
    BizEventLog queryBizEventLogByPrimaryKey(Long oid) throws DaoException;


    /**
     * 查询所有的记录
     *
     * @return
     * @author gulihua
     */
    List<BizEventLog> queryAllList();
}