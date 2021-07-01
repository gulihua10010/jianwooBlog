package cn.jianwoo.blog.cache;

import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 17:33
 */
public interface CacheStore<K, V> {

    /**
     * 通过缓存键获取。
     *
     * @param key 缓存键
     * @return
     * @author gulihua
     */
    Optional<V> get(@NonNull K key);

    /**
     * 放置未过期的缓存
     *
     * @param key   缓存键
     * @param value 缓存值
     * @return
     * @author gulihua
     */
    void put(@NonNull K key, @NonNull V value);

    /**
     * 通过缓存键获取。
     *
     * @param key      缓存键
     * @param value    缓存值
     * @param timeout  有效期不得小于1
     * @param timeUnit timeUnit
     * @return
     * @author gulihua
     */
    void put(@NonNull K key, @NonNull V value, @NonNull long timeout, @NonNull TimeUnit timeUnit);

    /**
     * 删除缓存
     *
     * @param key 缓存键
     * @return
     * @author gulihua
     */
    void delete(@NonNull K key);


    /**
     * 根据key查询是否存在
     *
     * @param key 缓存键
     * @return
     * @author gulihua
     */
    boolean hasKey(@NonNull K key);


    /**
     * 遍历map的key键
     *
     * @return
     * @author gulihua
     */
    Set<K> keySet();
}
