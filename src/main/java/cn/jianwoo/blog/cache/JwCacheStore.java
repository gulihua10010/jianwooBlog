package cn.jianwoo.blog.cache;

import cn.jianwoo.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-24 1:03
 */
@Slf4j
public class JwCacheStore<K, V> extends AbstractCacheStore<K, V> {
    private final ConcurrentMap<K, CacheWrapper<V>> cacheContainer = new ConcurrentHashMap<>();

    public JwCacheStore() {
    }

    @Override
    Optional<CacheWrapper<V>> getInternal(K key) {
        return Optional.ofNullable(cacheContainer.get(key));
    }

    @Override
    void putInternal(K key, CacheWrapper<V> cacheWrapper) {
        cacheContainer.put(key, cacheWrapper);
    }

    @Override
    public void delete(K key) {
        cacheContainer.remove(key);
    }

    @Override
    public boolean hasKey(K key) {
        boolean isContain = cacheContainer.containsKey(key);
        if (isContain) {
            if (getInternal(key).get().getIsSetExpire() && null != getInternal(key).get().getExpireAt() && getInternal(key).get().getExpireAt().before(DateUtil.getNow())) {
                log.warn("Cache key: [{}] has been expired, create date: [{}], expire date [{}] ",
                        key, DateUtil.getStandardFormat(getInternal(key).get().getCreateAt()), DateUtil.getStandardFormat(getInternal(key).get().getExpireAt()));
                delete(key);
                return false;
            }

        }
        return isContain;
    }

    @Override
    public Set<K> keySet() {
        return cacheContainer.keySet();
    }
}
