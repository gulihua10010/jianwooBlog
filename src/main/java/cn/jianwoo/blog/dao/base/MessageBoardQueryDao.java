package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.MessageBoard;
import cn.jianwoo.blog.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface MessageBoardQueryDao {
    MessageBoard queryMessageBoardByPrimaryKey(Long oid) throws DaoException;

    /**
     * 通过oid获取留言包含状态为已经删除的留言)
     *
     * @param oid oid
     * @return
     * @author gulihua
     */
    MessageBoard queryMessageBoardByOidWithDel(Long oid) throws DaoException;


    /**
     * 通过父oid获取留言
     *
     * @param oid 父oid
     * @return
     * @author gulihua
     */
    List<MessageBoard> queryMessagesByParentOid(Long oid);


    /**
     * 获取所有留言
     *
     * @return
     * @author gulihua
     */
    List<MessageBoard> queryAllMessages();


    /**
     * 根据日期范围和IP地址获取留言
     *
     * @param from 开始时间
     * @param to   截止时间
     * @param ip   IP地址
     * @return
     * @author gulihua
     */
    Long queryMessagesByDateRangeAndIp(Date from, Date to, String ip);
}