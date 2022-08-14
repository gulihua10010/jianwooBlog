package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.MessageBoardBizDao;
import cn.jianwoo.blog.dao.biz.mapper.MessageBoardBizMapper;
import cn.jianwoo.blog.entity.extension.MessageBoardExt;
import cn.jianwoo.blog.entity.query.MessageBoardPageQuery;
import cn.jianwoo.blog.entity.query.MessageBoardQuery;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-08-06 19:00
 */
@Service
public class MessageBoardBizDaoImpl implements MessageBoardBizDao {
    @Autowired
    private MessageBoardBizMapper messageBoardBizMapper;

    @Override
    public Long countAllMessage() {
        return messageBoardBizMapper.countAllMessage();
    }

    @Override
    public Long countWithUnreadMsg() {
        return messageBoardBizMapper.countWithUnreadMsg();
    }

    @Override
    public List<MessageBoardExt> queryRecentMessage(Integer limit) {
        return messageBoardBizMapper.selectRecentMessage(limit);
    }

    @Override
    public List<MessageBoardExt> queryMessagePageList(MessageBoardPageQuery query) {
        return messageBoardBizMapper.selectMessagePageList(query);
    }

    @Override
    public Long queryMessageRootCount() {
        return messageBoardBizMapper.selectMessageRootCount();
    }

    @Override
    public List<MessageBoardExt> queryMessageExt() {
        return messageBoardBizMapper.selectMessageExt();
    }

    @Override
    public List<MessageBoardExt> queryAllMessageExt(MessageBoardQuery param) {
        return messageBoardBizMapper.selectAllMessageExt(param);
    }

    @Override
    public void doUpdateMessagePraiseCnt(Long oid) throws DaoException {
        int updRlt = messageBoardBizMapper.updateMessagePraiseCnt(oid);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }
    }

    @Override
    public void doUpdateMessageReplyCnt(Long oid, String optType) throws DaoException {
        int updRlt = messageBoardBizMapper.updateMessageReplyCnt(oid, optType);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }

    }

    @Override
    public void doUpdateMessageTotalReplyCnt(Long oid, String optType) throws DaoException {
        int updRlt = messageBoardBizMapper.updateMessageTotalReplyCnt(oid, optType);
        if (1 != updRlt) {
            throw DaoException.DAO_UPDATE_RESULT_0.print();
        }

    }
}
