package cn.jianwoo.blog.config;

import cn.jianwoo.blog.config.router.admin.CommApiUrlConfig;
import cn.jianwoo.blog.config.router.admin.CommBackendPageUrlConfig;
import cn.jianwoo.blog.config.router.admin.LoginApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.interceptor.LoginHandleInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String res = CommApiUrlConfig.URL_RES_PREFIX + Constants.URL_SEPARATOR + Constants.ASTERISK
            + Constants.ASTERISK;
    @Value("${file.upload.path}")
    private String uploadPath;
    private static final String[] EXCLUDE_PATH = {
            CommBackendPageUrlConfig.URL_PREFIX + CommBackendPageUrlConfig.URL_LOGIN,
            LoginApiUrlConfig.URL_PREFIX + LoginApiUrlConfig.URL_PREFIX,
            LoginApiUrlConfig.URL_PREFIX + LoginApiUrlConfig.URL_LOGIN_CAPTCHA_INIT,
            LoginApiUrlConfig.URL_PREFIX + LoginApiUrlConfig.URL_LOGIN_CAPTCHA_VERIFY,
            LoginApiUrlConfig.URL_PREFIX + LoginApiUrlConfig.URL_LOGIN_AUTH,
            Constants.ALL_STATIC_PATTERNS,
            Constants.ALL_RES_PATTERNS

    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(res)
                .addResourceLocations(Constants.FILE_PROTOCOL + uploadPath + Constants.URL_SEPARATOR);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginHandleInterceptor()
//        ).addPathPatterns(Constants.ALL_PATH_PATTERNS).excludePathPatterns(EXCLUDE_PATH);

    }

    @Bean
    public LoginHandleInterceptor loginHandleInterceptor() {
        return new LoginHandleInterceptor();
    }

}
