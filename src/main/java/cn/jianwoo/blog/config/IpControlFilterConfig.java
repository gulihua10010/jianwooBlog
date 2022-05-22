package cn.jianwoo.blog.config;

import cn.jianwoo.blog.config.router.main.AnnounceMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.ArticleMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.CommentMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.AccessMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.WebConfigApiUrlConfig;
import cn.jianwoo.blog.filter.IpControlFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-15 15:36
 */
@Configuration
public class IpControlFilterConfig {
    @Resource
    private IpControlFilter ipControlFilter;
    private static final String[] IP_CONTROL_FILTER_URL_PATTERN = {
            AnnounceMainApiUrlConfig.URL_PREFIX + AnnounceMainApiUrlConfig.ALL_PATTERNS,
            ArticleMainApiUrlConfig.URL_PREFIX + ArticleMainApiUrlConfig.ALL_PATTERNS,
            CommentMainApiUrlConfig.URL_PREFIX + CommentMainApiUrlConfig.ALL_PATTERNS,
            AccessMainApiUrlConfig.URL_PREFIX + AccessMainApiUrlConfig.ALL_PATTERNS,
            WebConfigApiUrlConfig.URL_PREFIX + WebConfigApiUrlConfig.ALL_PATTERNS,
    };


    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(ipControlFilter);
        registration.addUrlPatterns(IP_CONTROL_FILTER_URL_PATTERN);
        registration.setName("ipControlFilter");
        registration.setOrder(1);
        return registration;
    }
}
