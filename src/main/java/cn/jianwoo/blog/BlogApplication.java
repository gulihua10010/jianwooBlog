package cn.jianwoo.blog;

import cn.hutool.cron.CronUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.jianwoo.blog.dao.base.mapper", "cn.jianwoo.blog.dao.biz.mapper", "com.baidu.fsg.uid.worker.dao"})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        CronUtil.start();
    }

}
