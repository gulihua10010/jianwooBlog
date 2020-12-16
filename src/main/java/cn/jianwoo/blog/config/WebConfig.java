package cn.jianwoo.blog.config;

import cn.jianwoo.blog.config.page.CommApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String res = CommApiUrlConfig.URL_RES_PREFIX + Constants.URL_SEPARATOR + Constants.ASTERISK
            + Constants.ASTERISK;
    @Value("${file.upload.path}")
    private String uploadPath;

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

//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**").excludePathPatterns("/login",
//                "/login1", "/GeetestStart", "/VerifyLogin", "/static/**", "/api/**", "/img/**");
//    }
}
