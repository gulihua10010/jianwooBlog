package cn.jianwoo.blog.dao.base;

import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.enums.PraiseTypeEnum;
import cn.jianwoo.blog.exception.DaoException;

public interface BizPraiseQueryDao {
    BizPraise queryBizPraiseByPrimaryKey(Long oid) throws DaoException;


    /**
     * 通过文章oid和ip查询
     *
     * @param bizOid 文章/评论oid
     * @param ip     IP地址
     * @param type   类型{@link PraiseTypeEnum}
     * @return
     * @author gulihua
     */
    BizPraise queryByBizOidAndIp(Long bizOid, String ip, String type);
}