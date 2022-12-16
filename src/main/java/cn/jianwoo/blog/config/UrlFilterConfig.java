package cn.jianwoo.blog.config;

import cn.jianwoo.blog.config.router.main.AnnounceMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.ArticleMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.CommentMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.AccessMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.MenuMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.MsgMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.TagsMainApiUrlConfig;
import cn.jianwoo.blog.config.router.main.WebConfigApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.filter.HistoryModeFilter;
import cn.jianwoo.blog.filter.IpControlFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-15 15:36
 */
@Configuration
@Slf4j
public class UrlFilterConfig {
    @Resource
    private IpControlFilter ipControlFilter;
    @Resource
    private HistoryModeFilter historymodeFilter;

    @Value("${vue.root.exclusive}")
    private String exclusiveVueUrl;
    private static final String[] IP_CONTROL_FILTER_URL_PATTERN = {
            AnnounceMainApiUrlConfig.URL_PREFIX + AnnounceMainApiUrlConfig.ALL_PATTERNS,
            ArticleMainApiUrlConfig.URL_PREFIX + ArticleMainApiUrlConfig.ALL_PATTERNS,
            CommentMainApiUrlConfig.URL_PREFIX + CommentMainApiUrlConfig.ALL_PATTERNS,
            AccessMainApiUrlConfig.URL_PREFIX + AccessMainApiUrlConfig.ALL_PATTERNS,
            WebConfigApiUrlConfig.URL_PREFIX + WebConfigApiUrlConfig.ALL_PATTERNS,
            TagsMainApiUrlConfig.URL_PREFIX + TagsMainApiUrlConfig.ALL_PATTERNS,
            MenuMainApiUrlConfig.URL_PREFIX + MenuMainApiUrlConfig.ALL_PATTERNS,
            MsgMainApiUrlConfig.URL_PREFIX + MsgMainApiUrlConfig.ALL_PATTERNS,
    };


    @Bean
    public FilterRegistrationBean ipControlFilterBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean(ipControlFilter);
        registration.addUrlPatterns(IP_CONTROL_FILTER_URL_PATTERN);
        registration.setName("ipControlFilter");
        registration.setOrder(2);
        return registration;
    }
    @Bean
    public FilterRegistrationBean historymodeFilterBean() {
        String[] urls= exclusiveVueUrl.split(Constants.COMMA_SEPARATOR);
        FilterRegistrationBean registration = new FilterRegistrationBean(historymodeFilter);
        registration.addUrlPatterns(urls);
        registration.setName("historymodeFilter");
        registration.setOrder(1);
        return registration;
    }
}
