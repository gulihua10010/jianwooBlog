package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.entity.query.MsgQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface MessageProfileQueryDao {
    MessageProfile queryMessageProfileByPrimaryKey(Long oid) throws DaoException;

    /**
     * 分页查询消息
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    List<MessageProfileWithBLOBs> queryMessageProfilePageList(MsgQuery query);

    /**
     * 页面定时获取最新消息
     *
     * @param limit 消息条数
     * @return
     * @author gulihua
     */
    List<MessageProfileWithBLOBs> queryMessageProfileTimerList(Integer limit);

    /**
     * 获取未读消息数量
     *
     * @return Integer
     * @author gulihua
     */
    Long queryUnreadMsgCount();
}