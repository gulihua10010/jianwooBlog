package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.MessageTemplate;
import cn.jianwoo.blog.exception.DaoException;

public interface MessageTemplateTransDao extends MessageTemplateQueryDao {
    void doInsert(MessageTemplate record) throws DaoException;

    void doInsertSelective(MessageTemplate record) throws DaoException;

    void doUpdateByPrimaryKey(MessageTemplate record) throws DaoException;

    void doUpdateByPrimaryKeySelective(MessageTemplate record) throws DaoException;

    void doDeleteByPrimaryKey(String busiSceneCode) throws DaoException;
}