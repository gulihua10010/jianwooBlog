package cn.jianwoo.blog.cache;

import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 18:25
 */
@Slf4j
public abstract class AbstractCacheStore<K, V> implements CacheStore<K, V> {

    /**
     * 根据key从缓存器中获取值
     *
     * @param key key
     * @return
     * @author gulihua
     */
    abstract Optional<CacheWrapper<V>> getInternal(@NonNull K key);


    /**
     * 缓存器存放key-value对象
     *
     * @param key          key
     * @param cacheWrapper 缓存器
     * @return
     * @author gulihua
     */
    abstract void putInternal(@NonNull K key, @NonNull CacheWrapper<V> cacheWrapper);


    @Override
    public Optional<V> get(@NonNull K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return getInternal(key).map(cacheWrapper -> {
                    if (cacheWrapper.getIsSetExpire() && null != cacheWrapper.getExpireAt() && cacheWrapper.getExpireAt().before(DateUtil.getNow())) {
                        log.warn("Cache key: [{}] has been expired", key);
                        delete(key);
                        return null;
                    }
                    return cacheWrapper.getData();
                }
        );
    }

    @Override
    public void put(@NonNull K key, @NonNull V value) {
        putInternal(key, buildCacheWrapper(value, 0, null));
    }

    @Override
    public void put(@NonNull K key, @NonNull V value, long timeout, @NonNull TimeUnit timeUnit) {
        putInternal(key, buildCacheWrapper(value, timeout, timeUnit));
    }

    /**
     * 组装缓存器
     *
     * @param value    缓存值
     * @param timeout  超时时间
     * @param timeUnit timeUnit
     * @return
     * @author gulihua
     */
    private CacheWrapper<V> buildCacheWrapper(@NonNull V value, long timeout, @Nullable TimeUnit timeUnit) {
        Assert.notNull(value, "Cache value must not be null");
        Date now = DateUtil.getNow();
        Date expireAt = now;
        CacheWrapper<V> cacheWrapper = new CacheWrapper<>();
        if (timeout > 0 && null != timeUnit) {
            expireAt = DateUtil.add(now, timeout, timeUnit);
            cacheWrapper.setIsSetExpire(true);
        }
        cacheWrapper.setCreateAt(now);
        cacheWrapper.setExpireAt(expireAt);
        cacheWrapper.setData(value);

        return cacheWrapper;

    }
}