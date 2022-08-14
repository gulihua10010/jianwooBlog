package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description 定义main页面的模板页面路由
 * @date 2020-08-05 17:07
 */
public class CommMainPageTemplateConfig extends UrlConfig {

    public static final String PAGE_PREFIX = "main/dist";

    public static final String PAGE_INDEX = "/index";



    /**
     * 获取默认的url
     *
     * @param url url
     * @return
     * @author gulihua
     */
    public static String getUrl(String url) {
        return getUrl(url, DEFAULT_VERSION);
    }

    /**
     * 获取指定版本的url
     *
     * @param url     url
     * @param version 版本
     * @return
     * @author gulihua
     */
    public static String getUrl(String url, int version) {
        return getUrl(PAGE_PREFIX, url, version);
    }
}
