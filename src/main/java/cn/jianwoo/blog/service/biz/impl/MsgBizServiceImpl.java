package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.MessageProfileTransDao;
import cn.jianwoo.blog.dao.biz.MsgBizDao;
import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.entity.query.MsgQuery;
import cn.jianwoo.blog.enums.ReceiverTypeEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.MsgProfileBizException;
import cn.jianwoo.blog.service.biz.MsgBizService;
import cn.jianwoo.blog.service.bo.MsgProfileBO;
import cn.jianwoo.blog.service.param.MsgParam;
import cn.jianwoo.blog.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gulihua
 * @Description
 * @date 2022-05-19 16:22
 */
@Service
@Slf4j
public class MsgBizServiceImpl implements MsgBizService {

    @Autowired
    private MessageProfileTransDao messageProfileTransDao;
    @Autowired
    private MsgBizDao msgBizDao;

    @Override
    public PageInfo<MsgProfileBO> queryMsgPageList(MsgParam param) {
        String loginId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        MsgQuery query = new MsgQuery();
        if (Constants.TRUE_1.equals(param.getIsRead())) {
            query.setIsRead(param.getIsRead());
        }
        query.setReceiverId(loginId);
        query.setReceiverType(ReceiverTypeEnum.ADMIN.getValue());
        List<MessageProfileWithBLOBs> messageProfileList = messageProfileTransDao.queryMessageProfilePageList(query);
        List<MsgProfileBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageProfileList)) {
            messageProfileList.forEach(o -> {
                MsgProfileBO bo = new MsgProfileBO();
                BeanUtils.copyProperties(o, bo, "msgContent");
                bo.setMsgContent(new String(o.getMsgContent()));
                list.add(bo);
            });
        }
        PageInfo<MsgProfileBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public void doReadMsg(Long msgId) throws JwBlogException {
        MessageProfileWithBLOBs newMessage = new MessageProfileWithBLOBs();
        newMessage.setOid(msgId);
        newMessage.setFlagRead(true);
        newMessage.setReadTime(DateUtil.getNow());
        newMessage.setUpdateTime(DateUtil.getNow());
        try {
            messageProfileTransDao.doUpdateByPrimaryKeySelective(newMessage);
        } catch (DaoException e) {
            throw MsgProfileBizException.MODIFY_FAILED_EXCEPTION.format(msgId).print();

        }
    }

    @Override
    public void doReadAllMsg()  {
        messageProfileTransDao.doUpdateAllMsgRead();
    }

    @Override
    public List<MsgProfileBO> queryMsgTimerList(Integer limit) {
        String loginId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MessageProfileWithBLOBs> messageProfileList = messageProfileTransDao.queryMessageProfileTimerList(loginId,ReceiverTypeEnum.ADMIN.getValue(), limit);
        List<MsgProfileBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageProfileList)) {
            messageProfileList.forEach(o -> {
                MsgProfileBO bo = new MsgProfileBO();
                BeanUtils.copyProperties(o, bo, "msgContent");
                bo.setMsgContent(new String(o.getMsgContent()));
                list.add(bo);
            });
            messageProfileTransDao.doUpdateFlagPopupByOidList(list.stream().map(MsgProfileBO::getOid).collect(Collectors.toList()));
        }

        return list;
    }

    @Override
    public Long queryUnreadMsgCount() {
        String loginId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageProfileTransDao.queryUnreadMsgCount(loginId, ReceiverTypeEnum.ADMIN.getValue());
    }

    @Override
    public List<MsgProfileBO> queryMsgTimerMainList(Integer limit, String currentIp) {
        List<MessageProfileWithBLOBs> messageProfileList = msgBizDao.queryMsgTimerMainList(limit, currentIp);
        List<MsgProfileBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageProfileList)) {
            messageProfileList.forEach(o -> {
                MsgProfileBO bo = new MsgProfileBO();
                BeanUtils.copyProperties(o, bo, "msgContent");
                bo.setMsgContent(new String(o.getMsgContent()));
                list.add(bo);
            });
            messageProfileTransDao.doUpdateFlagPopupMainByOidList(list.stream().map(MsgProfileBO::getOid).collect(Collectors.toList()));
        }

        return list;
    }
}

