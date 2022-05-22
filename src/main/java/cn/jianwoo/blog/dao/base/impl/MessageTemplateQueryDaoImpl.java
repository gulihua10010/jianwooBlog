package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.MessageTemplateQueryDao;
import cn.jianwoo.blog.dao.base.mapper.MessageTemplateMapper;
import cn.jianwoo.blog.entity.MessageTemplate;
import cn.jianwoo.blog.entity.example.MessageTemplateExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageTemplateQueryDao")
public class MessageTemplateQueryDaoImpl implements MessageTemplateQueryDao {
    @Autowired
    MessageTemplateMapper messageTemplateMapper;

    @Override
    public MessageTemplate queryMessageTemplateByPrimaryKey(String busiSceneCode) throws DaoException {
        MessageTemplate record = messageTemplateMapper.selectByPrimaryKey(busiSceneCode);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public MessageTemplate queryMessageTemplateByBusiSceneCode(String busiSceneCode) throws DaoException {
        MessageTemplateExample messageTemplateExample = new MessageTemplateExample();
        messageTemplateExample.createCriteria().andBusiSceneCodeEqualTo(busiSceneCode);
        List<MessageTemplate> recordList = messageTemplateMapper.selectByExampleWithBLOBs(messageTemplateExample);
        if (CollectionUtils.isEmpty(recordList)) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return recordList.get(0);
    }
}