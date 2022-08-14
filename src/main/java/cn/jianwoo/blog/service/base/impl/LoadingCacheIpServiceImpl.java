package cn.jianwoo.blog.service.base.impl;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.base.LoadingCacheIpService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-28 15:26
 */
@Service
@Slf4j
public class LoadingCacheIpServiceImpl implements LoadingCacheIpService {

    @Autowired
    private WebconfBizService webconfBizService;
    @Autowired
    private CacheStore<String, Integer> cacheStore;
    LoadingCache<String, RateLimiter> ipRequestCaches = CacheBuilder.newBuilder()
            .maximumSize(1000)// 设置缓存个数
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(new CacheLoader<String, RateLimiter>() {
                @Override
                public RateLimiter load(String s) throws Exception {
                    return RateLimiter.create(getIpAccessCtrl(s));// 新的IP初始化 (限流每秒0.1个令牌响应,即10s一个令牌)
                }
            });

    private Integer getIpAccessCtrl(String ipWithInter) {
        Integer limiter = 10;
        String[] name = ipWithInter.split(Constants.IP_SPLIT);
        String cacheKey = MessageFormat.format(CacheKeyConstants.IP_ACCESS_TRAFFIC_CTRL_KEY, name[1]);

        if (cacheStore.hasKey(cacheKey)) {
            Integer v = cacheStore.get(cacheKey).orElse(limiter);
            return v;
        }
        try {
            String value = webconfBizService.queryWebconfByKey(WebConfDataConfig.IP_ACCESS_TRAFFIC_CTRL);
            log.info("IP_ACCESS_TRAFFIC_CTRL value is {}", value);
            cacheStore.put(cacheKey, Integer.valueOf(value), 1, TimeUnit.DAYS);
            return Integer.valueOf(value);
        } catch (JwBlogException e) {
            log.error("LoadingCacheIpServiceImpl.getIpAccessCtrl exec failed: e \r\n", e);
            return limiter;
        }

    }

    @Override
    public RateLimiter getIpLimiter(String ipWithInter) throws ExecutionException {

        return ipRequestCaches.get(ipWithInter);
    }


}
