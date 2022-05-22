package cn.jianwoo.blog.dao.base.impl;

import cn.jianwoo.blog.dao.base.IpBlackListQueryDao;
import cn.jianwoo.blog.dao.base.mapper.IpBlackListMapper;
import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.entity.example.IpBlackListExample;
import cn.jianwoo.blog.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ipBlackListQueryDao")
public class IpBlackListQueryDaoImpl implements IpBlackListQueryDao {
    @Autowired
    IpBlackListMapper ipBlackListMapper;

    @Override
    public IpBlackList queryIpBlackListByPrimaryKey(Long oid) throws DaoException {
        IpBlackList record = ipBlackListMapper.selectByPrimaryKey(oid);
        if (null == record) {
            throw DaoException.DAO_SELECTONE_IS_NULL.print();
        }
        return record;
    }

    @Override
    public boolean queryIpIsInBlackList(String ip) {
        IpBlackListExample example = new IpBlackListExample();
        example.createCriteria().andAccessIpEqualTo(ip);
        return ipBlackListMapper.countByExample(example) > 0;

    }

    @Override
    public List<IpBlackList> queryAllBlackList() {
        IpBlackListExample example = new IpBlackListExample();
        return ipBlackListMapper.selectByExample(example);
    }
}