package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AsyncProcAutoTask;
import cn.jianwoo.blog.exception.DaoException;

public interface AsyncProcAutoTaskTransDao extends AsyncProcAutoTaskQueryDao {
    void doInsert(AsyncProcAutoTask record) throws DaoException;

    void doInsertSelective(AsyncProcAutoTask record) throws DaoException;

    void doUpdateByPrimaryKey(AsyncProcAutoTask record) throws DaoException;

    void doUpdateByPrimaryKeySelective(AsyncProcAutoTask record) throws DaoException;

    void doDeleteByPrimaryKey(Long taskId) throws DaoException;
}