package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.MessageProfile;
import cn.jianwoo.blog.entity.MessageProfileWithBLOBs;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface MessageProfileTransDao extends MessageProfileQueryDao {
    void doInsert(MessageProfileWithBLOBs record) throws DaoException;

    void doInsertSelective(MessageProfileWithBLOBs record) throws DaoException;

    void doUpdateByPrimaryKey(MessageProfile record) throws DaoException;


    void doUpdateByPrimaryKeyWithBLOBs(MessageProfileWithBLOBs record) throws DaoException;

    void doUpdateByPrimaryKeySelective(MessageProfileWithBLOBs record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;


    /**
     * 批量更新弹出状态
     *
     * @param oidList oid列表
     * @return
     * @author gulihua
     */
    void doUpdateFlagPopupByOidList(List<Long> oidList);


    /**
     * 批量更新所有未读消息为已读
     *
     * @return
     * @author gulihua
     */
    void doUpdateAllMsgRead();
}