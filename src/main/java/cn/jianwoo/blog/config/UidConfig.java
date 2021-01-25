package cn.jianwoo.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:config/cached-uid-spring.xml"})
public class UidConfig {
}
