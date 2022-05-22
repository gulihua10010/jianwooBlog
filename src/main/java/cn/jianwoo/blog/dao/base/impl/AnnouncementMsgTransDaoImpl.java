package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AnnouncementMsgTransDao;
import cn.jianwoo.blog.dao.base.mapper.AnnouncementMsgMapper;
import cn.jianwoo.blog.entity.AnnouncementMsg;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("announcementMsgTransDao")
public class AnnouncementMsgTransDaoImpl extends AnnouncementMsgQueryDaoImpl implements AnnouncementMsgTransDao {
    @Autowired
    AnnouncementMsgMapper announcementMsgMapper;

    @Override
    public void doInsert(AnnouncementMsg record) throws DaoException {
        int intRlt = announcementMsgMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(AnnouncementMsg record) throws DaoException {
        int intRlt = announcementMsgMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(AnnouncementMsg record) throws DaoException {
        int updRlt = announcementMsgMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(AnnouncementMsg record) throws DaoException {
        int updRlt = announcementMsgMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
        int delRlt = announcementMsgMapper.deleteByPrimaryKey(oid);
        if (1 != delRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}