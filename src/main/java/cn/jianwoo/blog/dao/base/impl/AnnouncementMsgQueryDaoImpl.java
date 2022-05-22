package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.AnnouncementMsgQueryDao;
import cn.jianwoo.blog.dao.base.mapper.AnnouncementMsgMapper;
import cn.jianwoo.blog.entity.AnnouncementMsg;
import cn.jianwoo.blog.entity.example.AnnouncementMsgExample;
import cn.jianwoo.blog.entity.query.AnnounceQuery;
import cn.jianwoo.blog.enums.AnnounceStatusEnum;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("announcementMsgQueryDao")
@Slf4j
public class AnnouncementMsgQueryDaoImpl implements AnnouncementMsgQueryDao {
    @Autowired
    AnnouncementMsgMapper announcementMsgMapper;

    @Override
    public AnnouncementMsg queryAnnouncementMsgByPrimaryKey(Long oid) throws DaoException {
        AnnouncementMsg record = announcementMsgMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public List<AnnouncementMsg> queryAllAnnounceList(AnnounceQuery query) {
        AnnouncementMsgExample example = new AnnouncementMsgExample();
        AnnouncementMsgExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(query.getTitle())) {
            criteria.andTitleLike(query.getTitle());
        }
        if (StringUtils.isNotBlank(query.getStatus())) {
            criteria.andStatusEqualTo(query.getStatus());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return announcementMsgMapper.selectByExample(example);
    }

    @Override
    public  List<AnnouncementMsg>  queryAnnounceByTitle(String title) {
        AnnouncementMsgExample example = new AnnouncementMsgExample();
        example.createCriteria().andTitleEqualTo(title);
        return announcementMsgMapper.selectByExample(example);

    }

    @Override
    public List<AnnouncementMsg> queryUsefulAnnounceList(AnnounceQuery query) {
        AnnouncementMsgExample example = new AnnouncementMsgExample();
        AnnouncementMsgExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(query.getTitle())) {
            criteria.andTitleLike(query.getTitle());
        }
        criteria.andStatusEqualTo(AnnounceStatusEnum.VALID.getValue()).andExpiationTimeGreaterThan(DateUtil.getNow());
        example.setOrderByClause("CREATE_TIME DESC");
        return announcementMsgMapper.selectByExample(example);
    }
}