package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AnnouncementMsg;
import cn.jianwoo.blog.exception.DaoException;

public interface AnnouncementMsgTransDao extends AnnouncementMsgQueryDao {
    void doInsert(AnnouncementMsg record) throws DaoException;

    void doInsertSelective(AnnouncementMsg record) throws DaoException;

    void doUpdateByPrimaryKey(AnnouncementMsg record) throws DaoException;

    void doUpdateByPrimaryKeySelective(AnnouncementMsg record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}