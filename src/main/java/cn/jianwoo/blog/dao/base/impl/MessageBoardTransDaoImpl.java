package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.MessageBoardTransDao;
import cn.jianwoo.blog.dao.base.mapper.MessageBoardMapper;
import cn.jianwoo.blog.entity.Comment;
import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageBoardTransDao")
public class MessageBoardTransDaoImpl extends MessageBoardQueryDaoImpl implements MessageBoardTransDao {
    @Autowired
    MessageBoardMapper messageBoardMapper;

    @Override
    public void doInsert(MessageBoard record) throws DaoException {
        int intRlt = messageBoardMapper.insert(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doInsertSelective(MessageBoard record) throws DaoException {
        int intRlt = messageBoardMapper.insertSelective(record);
        if (1 != intRlt) {
            throw DaoException.DAO_INSERT_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKey(MessageBoard record) throws DaoException {
        int updRlt = messageBoardMapper.updateByPrimaryKey(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateByPrimaryKeySelective(MessageBoard record) throws DaoException {
        int updRlt = messageBoardMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doDeleteByPrimaryKey(Long oid) throws DaoException {
//        int delRlt = messageBoardMapper.deleteByPrimaryKey(oid);
//        if (1 != delRlt) {
//            throw DaoException.DAO_DELETE_RESULT_0.print();
//        }

        MessageBoard record = JwBuilder.of(MessageBoard::new)
                .with(MessageBoard::setOid, oid)
                .with(MessageBoard::setIsDelete, true)
                .build();
        int updRlt = messageBoardMapper.updateByPrimaryKeySelective(record);
        if (1 != updRlt) {
            throw DaoException.DAO_DELETE_RESULT_0.print();
        }
    }
}