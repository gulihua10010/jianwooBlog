package cn.jianwoo.blog.cache;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-24 1:03
 */
public class JwCacheStore<K, V> extends AbstractCacheStore<K, V> {
    private final ConcurrentHashMap<K, CacheWrapper<V>> cacheContainer = new ConcurrentHashMap<>();

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
}
