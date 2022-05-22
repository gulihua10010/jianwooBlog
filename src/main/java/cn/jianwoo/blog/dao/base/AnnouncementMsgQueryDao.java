package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.AnnouncementMsg;
import cn.jianwoo.blog.entity.query.AnnounceQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface AnnouncementMsgQueryDao {
    AnnouncementMsg queryAnnouncementMsgByPrimaryKey(Long oid) throws DaoException;


    /**
     * 查询公告
     *
     * @return
     * @author gulihua
     */
    List<AnnouncementMsg> queryAllAnnounceList(AnnounceQuery query);

    /**
     * 根据标题查询公告
     *
     * @return
     * @author gulihua
     */
    List<AnnouncementMsg> queryAnnounceByTitle(String title);


    /**
     * 查询可用公告
     *
     * @return
     * @author gulihua
     */
    List<AnnouncementMsg> queryUsefulAnnounceList(AnnounceQuery query);
}