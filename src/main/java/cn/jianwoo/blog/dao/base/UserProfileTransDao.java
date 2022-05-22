package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.UserProfile;
import cn.jianwoo.blog.exception.DaoException;

public interface UserProfileTransDao extends UserProfileQueryDao {
    void doInsert(UserProfile record) throws DaoException;

    void doInsertSelective(UserProfile record) throws DaoException;

    void doUpdateByPrimaryKey(UserProfile record) throws DaoException;

    void doUpdateByPrimaryKeySelective(UserProfile record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}