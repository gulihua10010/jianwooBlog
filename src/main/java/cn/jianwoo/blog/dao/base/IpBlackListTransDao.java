package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.exception.DaoException;

public interface IpBlackListTransDao extends IpBlackListQueryDao {
    void doInsert(IpBlackList record) throws DaoException;

    void doInsertSelective(IpBlackList record) throws DaoException;

    void doUpdateByPrimaryKey(IpBlackList record) throws DaoException;

    void doUpdateByPrimaryKeySelective(IpBlackList record) throws DaoException;

    void doDeleteByPrimaryKey(Long oid) throws DaoException;

    /**
     * 通过IP从黑名单移除
     *
     * @param ip IP
     * @return
     * @author gulihua
     */
    void doDeleteByIp(String ip) throws DaoException;
}