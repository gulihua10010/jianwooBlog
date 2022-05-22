package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.BizPraiseQueryDao;
import cn.jianwoo.blog.dao.base.mapper.BizPraiseMapper;
import cn.jianwoo.blog.entity.BizPraise;
import cn.jianwoo.blog.entity.example.BizPraiseExample;
import cn.jianwoo.blog.exception.DaoException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bizPraiseQueryDao")
public class BizPraiseQueryDaoImpl implements BizPraiseQueryDao {
    @Autowired
    BizPraiseMapper bizPraiseMapper;

    @Override
    public BizPraise queryBizPraiseByPrimaryKey(Long oid) throws DaoException {
        BizPraise record = bizPraiseMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public BizPraise queryByBizOidAndIp(Long bizOid, String ip, String type) {
        BizPraiseExample example = new BizPraiseExample();
        example.createCriteria().andBizOidEqualTo(bizOid).andUserIpEqualTo(ip).andTypeEqualTo(type);
        List<BizPraise> list = bizPraiseMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}