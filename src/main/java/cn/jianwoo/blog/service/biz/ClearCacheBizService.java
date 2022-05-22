package cn.jianwoo.blog.service.biz;

import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.CacheBO;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-30 15:02
 */
public interface ClearCacheBizService {
    /**
     * 清除缓存目录
     *
     * @param isClearCache 是否清除缓存
     * @param isClearLog   是否清除日志
     * @param isClearTemp  是否清除临时目录
     * @param isCleanMemory  是否清除内存缓存:jwCacheStore
     * @return
     * @author gulihua
     */
    void clearCache(boolean isClearCache, boolean isClearLog, boolean isClearTemp, boolean isCleanMemory) throws JwBlogException;


    /**
     * 获取缓存信息
     *
     * @return
     * @author gulihua
     */
    CacheBO queryCacheInfo();
}
