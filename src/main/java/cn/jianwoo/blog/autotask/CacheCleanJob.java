package cn.jianwoo.blog.autotask;

import cn.hutool.extra.spring.SpringUtil;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author GuLihua
 * @Description
 * @date 2021-06-24 20:10
 */
@Slf4j
public class CacheCleanJob implements Job {

    @Override
    public void doProcess() throws JwBlogException {
        CacheStore<String, Object> cacheStore = SpringUtil.getBean(CacheStore.class);
        log.info("====>>AutoTask::CacheCleanJob start...");
        try {
            cacheStore.keySet().forEach(key -> {
                if (key.startsWith(CacaheKeyConstants.INVALID_TOKEN.replace("{0}",""))) {
                    Optional<Object> value = cacheStore.get(key);
                    if (value.isPresent()) {
                        String accessToken = (String) value.get();
                        try {
                            JwtUtils.parse(accessToken);
                        } catch (Exception e) {
                            log.debug(">>access token has expired, delete it::[{}]", accessToken);
                            cacheStore.delete(key);
                        }
                    }


                }
            });
        } catch (Exception e) {
            log.error(">>AutoTask::CacheCleanJob exec failed, e:\r\n", e);
        }


        log.info("====>>AutoTask::CacheCleanJob end...");
    }
}
