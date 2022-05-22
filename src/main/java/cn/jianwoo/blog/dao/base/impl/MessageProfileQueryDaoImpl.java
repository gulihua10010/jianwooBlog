package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.MessageProfileQueryDao;
import cn.jianwoo.blog.dao.base.mapper.MessageProfileMapper;
import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.entity.example.MessageProfileExample;
import cn.jianwoo.blog.entity.query.MsgQuery;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageProfileQueryDao")
public class MessageProfileQueryDaoImpl implements MessageProfileQueryDao {
    @Autowired
    MessageProfileMapper messageProfileMapper;

    @Override
    public MessageProfile queryMessageProfileByPrimaryKey(Long oid) throws DaoException {
        MessageProfile record = messageProfileMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<MessageProfileWithBLOBs> queryMessageProfilePageList(MsgQuery query) {
        MessageProfileExample example = new MessageProfileExample();
        MessageProfileExample.Criteria criteria = example.createCriteria();
        if (Constants.TRUE_1.equals(query.getIsRead())) {
            criteria.andFlagReadEqualTo(true);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return messageProfileMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<MessageProfileWithBLOBs> queryMessageProfileTimerList(Integer limit) {
        MessageProfileExample example = new MessageProfileExample();
        MessageProfileExample.Criteria criteria = example.createCriteria();
        criteria.andFlagReadEqualTo(false);
        criteria.andFlagPopupEqualTo(false);

        example.setOrderByClause("CREATE_TIME DESC");
        if (limit != null && limit > 0) {
            example.setRows(limit);
            example.setStart(0);
        }

        return messageProfileMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Long queryUnreadMsgCount() {
        MessageProfileExample example = new MessageProfileExample();
        MessageProfileExample.Criteria criteria = example.createCriteria();
        criteria.andFlagReadEqualTo(false);
        return messageProfileMapper.countByExample(example);
    }
}