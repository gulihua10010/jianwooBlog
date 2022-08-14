package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description 定义首页页面的URL
 * @date 2020-08-05 17:07
 */
public class CommMainPageUrlConfig extends UrlConfig {

    public static final String URL_PREFIX = "/";

    public static final String URL_INDEX = "/index";

    public static final String URL_OLD_PHP_DETAIL = "/detail/id/{id}.html";

    public static final String URL_NEW_VUE_DETAIL_ROUTE = "redirect:/#/detail?id=%s";


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
        return getUrl(URL_PREFIX, url, version);
    }
}
