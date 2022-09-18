package cn.jianwoo.blog.config;

import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.config.router.admin.CommApiUrlConfig;
import cn.jianwoo.blog.config.router.admin.PassportApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.interceptor.LoginHandleInterceptor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${vue.cursor.icon}")
    private String cursorIcon;
    @Value("${vue.favicon.icon}")
    private String faviconIcon;

    private static final String[] EXCLUDE_PATH = {
            CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN,
            PassportApiUrlConfig.URL_PREFIX + PassportApiUrlConfig.URL_PREFIX,
            PassportApiUrlConfig.URL_PREFIX + PassportApiUrlConfig.URL_PASSPORT_CAPTCHA_INIT,
            PassportApiUrlConfig.URL_PREFIX + PassportApiUrlConfig.URL_PASSPORT_CAPTCHA_VERIFY,
            PassportApiUrlConfig.URL_PREFIX + PassportApiUrlConfig.URL_PASSPORT_LOGIN_AUTH,
            Constants.ALL_STATIC_PATTERNS,
            Constants.ALL_RES_PATTERNS

    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(res)
                .addResourceLocations(Constants.FILE_PROTOCOL + uploadPath + Constants.URL_SEPARATOR);

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:static/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:templates/main/dist/css/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:templates/main/dist/fonts/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:templates/main/dist/img/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:templates/main/dist/js/");

        registry.addResourceHandler(cursorIcon)
                .addResourceLocations("classpath:templates/main/dist" + cursorIcon);
        registry.addResourceHandler(faviconIcon)
                .addResourceLocations("classpath:templates/main/dist" + faviconIcon);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //废弃通过session方法控制登录
//        registry.addInterceptor(loginHandleInterceptor()
//        ).addPathPatterns(Constants.ALL_ADMIN_PAGE);

    }

    //    @Bean
    public LoginHandleInterceptor loginHandleInterceptor() {
        return new LoginHandleInterceptor();
    }


}
