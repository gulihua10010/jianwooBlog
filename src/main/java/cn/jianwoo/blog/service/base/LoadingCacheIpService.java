package cn.jianwoo.blog.service.base;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutionException;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-28 15:25
 */
public interface LoadingCacheIpService {

    /**
     * 从缓存获取ip
     *
     * @param ipWithInter ip地址@@接口名
     * @return
     * @author gulihua
     */
    RateLimiter getIpLimiter(String ipWithInter) throws ExecutionException;
}
