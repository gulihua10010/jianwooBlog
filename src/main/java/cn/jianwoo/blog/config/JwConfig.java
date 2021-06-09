package cn.jianwoo.blog.config;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.cache.JwCacheStore;
import cn.jianwoo.blog.util.JwtUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-24 21:26
 */
@Configuration
@ComponentScan(basePackages={"cn.hutool.extra.spring"})
public class JwConfig {


    @Bean
    @ConditionalOnMissingBean
    public CacheStore jwCacheStore() {
        return new JwCacheStore();
    }

}
