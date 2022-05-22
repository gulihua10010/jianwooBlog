package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.dao.base.AnnouncementMsgTransDao;
import cn.jianwoo.blog.entity.AnnouncementMsg;
import cn.jianwoo.blog.entity.query.AnnounceQuery;
import cn.jianwoo.blog.enums.AnnounceStatusEnum;
import cn.jianwoo.blog.enums.BizEventOptTypeEnum;
import cn.jianwoo.blog.enums.BizEventTypeEnum;
import cn.jianwoo.blog.event.BizEventLogEvent;
import cn.jianwoo.blog.exception.AnnounceBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.AnnouncementBizService;
import cn.jianwoo.blog.service.bo.AnnounceBO;
import cn.jianwoo.blog.service.param.AnnounceParam;
import cn.jianwoo.blog.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-31 11:08
 */
@Service
@Slf4j
public class AnnouncementBizServiceImpl implements AnnouncementBizService {

    @Autowired
    private AnnouncementMsgTransDao announcementMsgTransDao;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageInfo<AnnounceBO> queryAllAnnouncePage(AnnounceParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        AnnounceQuery query = new AnnounceQuery();
        if (StringUtils.isNotBlank(param.getTitle())) {
            query.setTitle(param.getTitle());
        }
        if (StringUtils.isNotBlank(param.getStatus())) {
            query.setStatus(param.getStatus());
        }
        List<AnnouncementMsg> announcementMsgList = announcementMsgTransDao.queryAllAnnounceList(query);
        List<AnnounceBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(announcementMsgList)) {
            announcementMsgList.forEach(o -> {
                AnnounceBO bo = new AnnounceBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<AnnounceBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doCreateAnnounce(AnnounceBO param) throws JwBlogException {

        Date now = DateUtil.getNow();
        String loginId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AnnouncementMsg tpl = JwBuilder.of(AnnouncementMsg::new)
                .with(AnnouncementMsg::setTitle, param.getTitle())
                .with(AnnouncementMsg::setContent, param.getContent())
                .with(AnnouncementMsg::setExpiationTime, DateUtil.parse(param.getExpiationTimeStr(), "yyyy-MM-dd HH:mm:ss"))
                .with(AnnouncementMsg::setPushBy, loginId)
                .with(AnnouncementMsg::setPushTime, now)
                .with(AnnouncementMsg::setStatus, AnnounceStatusEnum.VALID.getValue())
                .with(AnnouncementMsg::setCreateTime, now)
                .with(AnnouncementMsg::setUpdateTime, now).build();
        try {
            announcementMsgTransDao.doInsertSelective(tpl);
        } catch (DaoException e) {
            log.error(">>doCreateAnnounce exec failed, e\r\n", e);
            throw AnnounceBizException.CREATE_FAILED_EXCEPTION.format(param.getTitle()).print();
        }
        registerBizEvent(tpl.getOid(), tpl.getTitle(), BizEventOptTypeEnum.CREATE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doUpdateAnnounce(AnnounceBO param) throws JwBlogException {
        Date now = DateUtil.getNow();
        AnnouncementMsg msg = JwBuilder.of(AnnouncementMsg::new)
                .with(AnnouncementMsg::setOid, param.getOid())
                .with(AnnouncementMsg::setTitle, param.getTitle())
                .with(AnnouncementMsg::setContent, param.getContent())
                .with(AnnouncementMsg::setExpiationTime, DateUtil.parse(param.getExpiationTimeStr(), "yyyy-MM-dd HH:mm:ss"))
                .with(AnnouncementMsg::setUpdateTime, now).build();
        try {
            announcementMsgTransDao.doUpdateByPrimaryKeySelective(msg);
        } catch (DaoException e) {
            log.error(">>doUpdateAnnounce exec failed, e\r\n", e);
            throw AnnounceBizException.MODIFY_FAILED_EXCEPTION.format(param.getOid()).print();
        }
        registerBizEvent(msg.getOid(), msg.getTitle(), BizEventOptTypeEnum.UPDATE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveAnnounce(Long oid) throws JwBlogException {
        try {
            announcementMsgTransDao.doDeleteByPrimaryKey(oid);
        } catch (DaoException e) {
            log.error(">>doRemoveAnnounce exec failed, e\r\n", e);
            throw AnnounceBizException.DELETE_FAILED_EXCEPTION.format(oid).print();
        }
        registerBizEvent(oid, null, BizEventOptTypeEnum.DELETE);
    }

    @Override
    public AnnounceBO queryAnnounceByOid(String oid) throws JwBlogException {
        try {
            AnnouncementMsg msg = announcementMsgTransDao.queryAnnouncementMsgByPrimaryKey(Long.parseLong(oid));
            return JwBuilder.of(AnnounceBO::new)
                    .with(AnnounceBO::setOid, msg.getOid())
                    .with(AnnounceBO::setTitle, msg.getTitle())
                    .with(AnnounceBO::setContent, msg.getContent())
                    .with(AnnounceBO::setExpiationTimeStr, DateUtil.format(msg.getExpiationTime(), "yyyy-MM-dd HH:mm:ss"))
                    .with(AnnounceBO::setExpiationTime, msg.getExpiationTime())
                    .with(AnnounceBO::setPushBy, msg.getPushBy())
                    .with(AnnounceBO::setPushTime, msg.getPushTime())
                    .with(AnnounceBO::setStatus, AnnounceStatusEnum.VALID.getValue())
                    .with(AnnounceBO::setCreateTime, msg.getCreateTime())
                    .with(AnnounceBO::setUpdateTime, msg.getUpdateTime()).build();
        } catch (Exception e) {
            log.error(">>queryAnnounceByOid exec failed, e\r\n", e);
            throw AnnounceBizException.NOT_EXIST_EXCEPTION_CN.print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doVoidAnnounce(Long oid) throws JwBlogException {
        try {
            AnnouncementMsg msg = announcementMsgTransDao.queryAnnouncementMsgByPrimaryKey(oid);
            if (!AnnounceStatusEnum.VALID.getValue().equals(msg.getStatus())) {
                throw AnnounceBizException.HAS_VOID_EXCEPTION_CN.print();
            }
            AnnouncementMsg newMsg = JwBuilder.of(AnnouncementMsg::new)
                    .with(AnnouncementMsg::setOid, oid)
                    .with(AnnouncementMsg::setStatus, AnnounceStatusEnum.VOID.getValue()).build();
            announcementMsgTransDao.doUpdateByPrimaryKeySelective(newMsg);
        } catch (DaoException e) {
            log.error(">>doVoidAnnounce exec failed, e\r\n", e);
            throw AnnounceBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRevertAnnounce(Long oid) throws JwBlogException {
        try {
            AnnouncementMsg msg = announcementMsgTransDao.queryAnnouncementMsgByPrimaryKey(oid);
            if (!AnnounceStatusEnum.VOID.getValue().equals(msg.getStatus())) {
                throw AnnounceBizException.NOT_VOID_EXCEPTION_CN.print();
            }
            AnnouncementMsg newMsg = JwBuilder.of(AnnouncementMsg::new)
                    .with(AnnouncementMsg::setOid, oid)
                    .with(AnnouncementMsg::setStatus, AnnounceStatusEnum.VALID.getValue()).build();
            announcementMsgTransDao.doUpdateByPrimaryKeySelective(newMsg);
        } catch (DaoException e) {
            log.error(">>doRevertAnnounce exec failed, e\r\n", e);
            throw AnnounceBizException.MODIFY_FAILED_EXCEPTION.format(oid).print();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doVoidAnnounceList(List<Long> oidList) throws JwBlogException {
        for (Long oidId : oidList) {
            doVoidAnnounce(oidId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRevertAnnounceList(List<Long> oidList) throws JwBlogException {
        for (Long oidId : oidList) {
            doRevertAnnounce(oidId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doRemoveAnnounceList(List<Long> oidList) throws JwBlogException {
        for (Long oidId : oidList) {
            doRemoveAnnounce(oidId);
        }
    }

    @Override
    public PageInfo<AnnounceBO> queryUsefulAnnouncePage(AnnounceParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        AnnounceQuery query = new AnnounceQuery();
        if (StringUtils.isNotBlank(param.getTitle())) {
            query.setTitle(param.getTitle());
        }
        List<AnnouncementMsg> announcementMsgList = announcementMsgTransDao.queryUsefulAnnounceList(query);
        List<AnnounceBO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(announcementMsgList)) {
            announcementMsgList.forEach(o -> {
                AnnounceBO bo = new AnnounceBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<AnnounceBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    private void registerBizEvent(Long oid, String desc, BizEventOptTypeEnum optTypeEnum) {
        BizEventLogEvent event = new BizEventLogEvent(this, SecurityContextHolder.getContext());
        event.setBizEventTypeEnum(BizEventTypeEnum.ANNOUNCEMENT_TPL);
        event.setBizEventOptTypeEnum(optTypeEnum);
        event.setOptEntityId(oid != null ? String.valueOf(oid) : null);
        event.setDesc(desc);
        applicationContext.publishEvent(event);
    }
}
