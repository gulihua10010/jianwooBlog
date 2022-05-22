package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.exception.DaoException;

public interface AsyncProcAutoTaskQueryDao {
    AsyncProcAutoTask queryAsyncProcAutoTaskByPrimaryKey(Long taskId) throws DaoException;
}