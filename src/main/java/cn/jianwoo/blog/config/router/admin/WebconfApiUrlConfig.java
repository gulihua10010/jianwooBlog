package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:40
 */
public class WebconfApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/webconf";
    public static final String URL_WEBCONF_UPDATE = "/update";
    public static final String URL_WEBCONF_INFO = "/info";
    public static final String URL_WEBCONF_EMAIL_TEST = "/email/test";


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
