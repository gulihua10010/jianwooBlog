package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.MessageProfileTransDao;
import cn.jianwoo.blog.dao.base.mapper.MessageProfileMapper;
import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.entity.example.MessageProfileExample;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageProfileTransDao")
public class MessageProfileTransDaoImpl extends MessageProfileQueryDaoImpl implements MessageProfileTransDao {
    @Autowired
    MessageProfileMapper messageProfileMapper;

    @Override
    public void doInsert(MessageProfileWithBLOBs record) throws DaoException {
        int intRlt = messageProfileMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(MessageProfileWithBLOBs record) throws DaoException {
        int intRlt = messageProfileMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(MessageProfile record) throws DaoException {
        int updRlt = messageProfileMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeyWithBLOBs(MessageProfileWithBLOBs record) throws DaoException {
        int updRlt = messageProfileMapper.updateByPrimaryKeyWithBLOBs(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(MessageProfileWithBLOBs record) throws DaoException {
        int updRlt = messageProfileMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = messageProfileMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateFlagPopupByOidList(List<Long> oidList) {
        MessageProfileExample example = new MessageProfileExample();
        MessageProfileExample.Criteria criteria = example.createCriteria();
        criteria.andOidIn(oidList);
        MessageProfileWithBLOBs newMessage = new MessageProfileWithBLOBs();
        newMessage.setFlagPopup(true);
        newMessage.setUpdateTime(DateUtil.getNow());
        messageProfileMapper.updateByExampleSelective(newMessage, example);

    }

    @Override
    public void doUpdateAllMsgRead() {
        MessageProfileExample example = new MessageProfileExample();
        MessageProfileExample.Criteria criteria = example.createCriteria();
        criteria.andFlagReadEqualTo(false);
        MessageProfileWithBLOBs newMessage = new MessageProfileWithBLOBs();
        newMessage.setFlagRead(true);
        newMessage.setReadTime(DateUtil.getNow());
        newMessage.setUpdateTime(DateUtil.getNow());
        messageProfileMapper.updateByExampleSelective(newMessage, example);
    }
}