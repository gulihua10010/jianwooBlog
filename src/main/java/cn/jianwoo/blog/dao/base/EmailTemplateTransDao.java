package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.EmailTemplate;
import cn.jianwoo.blog.exception.DaoException;

public interface EmailTemplateTransDao extends EmailTemplateQueryDao {
    void doInsert(EmailTemplate record) throws DaoException;

    void doInsertSelective(EmailTemplate record) throws DaoException;

    void doUpdateByPrimaryKey(EmailTemplate record) throws DaoException;

    void doUpdateByPrimaryKeySelective(EmailTemplate record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;
}