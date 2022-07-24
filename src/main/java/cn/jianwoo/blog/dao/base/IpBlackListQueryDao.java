package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.entity.query.BlackIpQuery;
import cn.jianwoo.blog.exception.DaoException;

import java.util.List;

public interface IpBlackListQueryDao {
    IpBlackList queryIpBlackListByPrimaryKey(Long oid) throws DaoException;

    /**
     * IP是否在黑名单里
     *
     * @param ip ip地址
     * @return
     * @author gulihua
     */
    boolean queryIpIsInBlackList(String ip);

    /**
     * 查询黑名单列表
     *
     * @return
     * @author gulihua
     */
    List<IpBlackList> queryAllBlackList();

    /**
     * 查询黑名单列表
     *
     * @return
     * @author gulihua
     */
    List<IpBlackList> queryAllBlackList(BlackIpQuery query);

    /**
     * 根据IP查询
     *
     * @param ip ip地址
     * @return
     * @author gulihua
     */
    IpBlackList queryBlackByIp(String ip);
}