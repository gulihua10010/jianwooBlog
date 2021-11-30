package cn.jianwoo.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-24 11:50
 */
@Configuration
public class AsyncEventConfig {

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        applicationEventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return applicationEventMulticaster;
    }
}
