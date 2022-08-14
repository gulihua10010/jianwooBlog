package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.IpBlackListTransDao;
import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.entity.query.BlackIpQuery;
import cn.jianwoo.blog.exception.BlackIpBizException;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.BlackIpBizService;
import cn.jianwoo.blog.service.bo.BlackIpBO;
import cn.jianwoo.blog.service.param.BlackIpParam;
import cn.jianwoo.blog.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 * @Description
 * @date 2022-06-30 11:42
 */
@Service
@Slf4j
public class BlackIpBizServiceImpl implements BlackIpBizService {

    @Autowired
    private IpBlackListTransDao ipBlackListTransDao;
    @Autowired
    private CacheStore<String, String> cacheStore;


    @Override
    public PageInfo<BlackIpBO> queryAllIpBlackListPage(BlackIpParam param) {
        Page page = PageHelper.startPage(param.getPageNo(), param.getPageSize());
        BlackIpQuery query = new BlackIpQuery();
        query.setIp(param.getIp());
        List<IpBlackList> ipList = ipBlackListTransDao.queryAllBlackList(query);
        List<BlackIpBO> list = new ArrayList<>();
        if (!org.springframework.util.CollectionUtils.isEmpty(ipList)) {
            ipList.forEach(o -> {
                BlackIpBO bo = new BlackIpBO();
                BeanUtils.copyProperties(o, bo);
                list.add(bo);
            });
        }
        PageInfo<BlackIpBO> pageInfo = new PageInfo<>(list);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public void doAddBlackIp(String ip) throws JwBlogException {
        IpBlackList blackList = ipBlackListTransDao.queryBlackByIp(ip.trim());
        if (null != blackList) {
            throw BlackIpBizException.HAS_EXIST_EXCEPTION_CN.format(ip).print();
        }
        IpBlackList ipBlackList = JwBuilder.of(IpBlackList::new)
                .with(IpBlackList::setAccessIp, ip.trim())
                .with(IpBlackList::setCreateTime, DateUtil.getNow()).build();
        try {
            ipBlackListTransDao.doInsertSelective(ipBlackList);
        } catch (DaoException e) {
            throw BlackIpBizException.CREATE_FAILED_EXCEPTION.format(ip).print();

        }
        String cacheKey = MessageFormat.format(CacheKeyConstants.IP_BLACK_KEY, ip);
        cacheStore.put(cacheKey, Constants.YES, 7, TimeUnit.DAYS);
    }

    @Override
    public void doRemoveBlackIp(String ip) throws JwBlogException {
        try {
            ipBlackListTransDao.doDeleteByIp(ip);
        } catch (DaoException e) {
            throw BlackIpBizException.DELETE_FAILED_EXCEPTION.format(ip).print();

        }

        String cacheKey = MessageFormat.format(CacheKeyConstants.IP_BLACK_KEY, ip);
        cacheStore.delete(cacheKey);
    }

    @Override
    public void doRemoveBlackIpList(List<String> ipList) throws JwBlogException {
        for (String ip : ipList) {
            doRemoveBlackIp(ip);
        }

    }

    @Override
    public void doAddBlackIpList(List<String> ipList) throws JwBlogException {
        for (String ip : ipList) {
            doAddBlackIp(ip);
        }
    }
}
