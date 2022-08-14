package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.MessageBoardQueryDao;
import cn.jianwoo.blog.dao.base.mapper.MessageBoardMapper;
import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.entity.example.MessageBoardExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("messageBoardQueryDao")
public class MessageBoardQueryDaoImpl implements MessageBoardQueryDao {
    @Autowired
    MessageBoardMapper messageBoardMapper;

    @Override
    public MessageBoard queryMessageBoardByPrimaryKey(Long oid) throws DaoException {
        MessageBoard record = messageBoardMapper.selectByPrimaryKey(oid);
        if (null == record || record.getIsDelete()) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public MessageBoard queryMessageBoardByOidWithDel(Long oid) throws DaoException {
        MessageBoard record = messageBoardMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<MessageBoard> queryMessagesByParentOid(Long oid) {
        MessageBoardExample example = new MessageBoardExample();
        example.createCriteria().andParentOidEqualTo(oid);
        return messageBoardMapper.selectByExample(example);
    }

    @Override
    public List<MessageBoard> queryAllMessages() {
        MessageBoardExample example = new MessageBoardExample();
        return messageBoardMapper.selectByExample(example);

    }

    @Override
    public Long queryMessagesByDateRangeAndIp(Date from, Date to, String ip) {
        MessageBoardExample example = new MessageBoardExample();
        example.createCriteria().andClientIpEqualTo(ip).andPushTimeGreaterThan(from).andPushTimeLessThan(to);
        return messageBoardMapper.countByExample(example);

    }
}