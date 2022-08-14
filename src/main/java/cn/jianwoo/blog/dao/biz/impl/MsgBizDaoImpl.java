package cn.jianwoo.blog.dao.biz.impl;

import cn.jianwoo.blog.dao.biz.MsgBizDao;
import cn.jianwoo.blog.dao.biz.mapper.MsgBizMapper;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-08-02 18:43
 */
@Service
public class MsgBizDaoImpl implements MsgBizDao {
    @Autowired
    private MsgBizMapper msgBizMapper;

    @Override
    public List<MessageProfileWithBLOBs> queryMsgTimerMainList(Integer limit, String currentIp) {
        return msgBizMapper.selectMsgTimerMainList(limit, currentIp);
    }
}
