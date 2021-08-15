package cn.jianwoo.blog.config;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.cache.JwCacheStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-24 21:26
 */
@Configuration
@ComponentScan(basePackages = {"cn.hutool.extra.spring"})
public class JwConfig {


    @Bean
    @ConditionalOnMissingBean
    public CacheStore jwCacheStore() {
        return new JwCacheStore();
    }

    @Bean
    public HttpFirewall allowUrlSemicolonHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }
}
