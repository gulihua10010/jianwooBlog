package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.builder.JwBuilder;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.dao.base.AccessIpCtrlTransDao;
import cn.jianwoo.blog.dao.base.IpBlackListTransDao;
import cn.jianwoo.blog.entity.AccessIpCtrl;
import cn.jianwoo.blog.entity.IpBlackList;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.service.base.IpControlBaseService;
import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-20 15:38
 */
@Service
@Slf4j
public class IpControlBaseServiceImpl implements IpControlBaseService {
    @Autowired
    private AccessIpCtrlTransDao accessIpCtrlTransDao;
    @Autowired
    private IpBlackListTransDao ipBlackListTransDao;

    @Autowired
    private CacheStore<String, String> cacheStore;

    @Override
    public void doCreateRecord(String ip, String url) {
        AccessIpCtrl accessIpCtrl = JwBuilder.of(AccessIpCtrl::new)
                .with(AccessIpCtrl::setAccessIp, ip)
                .with(AccessIpCtrl::setInterfaceUrl, url)
                .with(AccessIpCtrl::setAccessTime, DateUtil.getNow()).build();
        try {
            accessIpCtrlTransDao.doInsertSelective(accessIpCtrl);
        } catch (DaoException e) {
            log.error("IpControlBaseService.doCreateRecord exec failed, ip=[{}], url=[{}]", ip, url);
            log.error("IpControlBaseService.doCreateRecord exec failed:", e);
        }

    }

    @Override
    public boolean isIpInBlackList(String ip) {
        String cacheKey = MessageFormat.format(CacheKeyConstants.IP_BLACK_KEY, ip);
        String cacheFlagKey = CacheKeyConstants.IP_BLACK_FLAG_KEY;
        //缓存有 黑名单列表
        if (cacheStore.hasKey(cacheFlagKey)) {
            if (cacheStore.hasKey(cacheKey)) {
                String isIn = cacheStore.get(cacheKey).orElse(null);
                return Constants.YES.equals(isIn);
            }
            return false;

        } else {
            List<IpBlackList> blackList = ipBlackListTransDao.queryAllBlackList();
            cacheStore.put(cacheFlagKey, Constants.YES, 7, TimeUnit.DAYS);
            if (!CollectionUtils.isEmpty(blackList)) {
                for (IpBlackList ipBlack : blackList) {
                    String cacheTmpKey = MessageFormat.format(CacheKeyConstants.IP_BLACK_KEY, ipBlack.getAccessIp());
                    cacheStore.put(cacheTmpKey, Constants.YES, 7, TimeUnit.DAYS);
                }

            }
            String isIn = cacheStore.get(cacheKey).orElse(null);
            return Constants.YES.equals(isIn);
        }

    }

    @Override
    public void doCreateBlackRecord(String ip) {
        IpBlackList ipBlackList = JwBuilder.of(IpBlackList::new)
                .with(IpBlackList::setAccessIp, ip)
                .with(IpBlackList::setCreateTime, DateUtil.getNow())
                .build();
        try {
            ipBlackListTransDao.doInsertSelective(ipBlackList);
        } catch (DaoException e) {
            log.error("IpControlBaseServiceImpl.doCreateBlackRecord exec failed, e:\r\n", e);
        }

        String cacheKey = MessageFormat.format(CacheKeyConstants.IP_BLACK_KEY, ip);
        cacheStore.put(cacheKey, Constants.YES, 7, TimeUnit.DAYS);
    }

    @Override
    public void doRemoveBlackRecord(String ip) {
        try {
            ipBlackListTransDao.doDeleteByIp(ip);
        } catch (DaoException e) {
            log.error("IpControlBaseServiceImpl.doRemoveBlackRecord exec failed, e:\r\n", e);


        }
    }
}
