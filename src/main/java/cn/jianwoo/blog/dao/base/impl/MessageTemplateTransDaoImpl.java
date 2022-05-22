package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.MessageTemplateTransDao;
import cn.jianwoo.blog.dao.base.mapper.MessageTemplateMapper;
import cn.jianwoo.blog.entity.MessageTemplate;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageTemplateTransDao")
public class MessageTemplateTransDaoImpl extends MessageTemplateQueryDaoImpl implements MessageTemplateTransDao {
    @Autowired
    MessageTemplateMapper messageTemplateMapper;

    @Override
    public void doInsert(MessageTemplate record) throws DaoException {
        int intRlt = messageTemplateMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(MessageTemplate record) throws DaoException {
        int intRlt = messageTemplateMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(MessageTemplate record) throws DaoException {
        int updRlt = messageTemplateMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(MessageTemplate record) throws DaoException {
        int updRlt = messageTemplateMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(String busiSceneCode) throws DaoException {
        int delRlt = messageTemplateMapper.deleteByPrimaryKey(busiSceneCode);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}