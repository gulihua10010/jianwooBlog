package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:13
 */
public class SubTokenApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/request/token";
    public static final String URL_TOKEN_GENERATE = "/generate";
    public static final String URL_TOKEN_LAY_GENERATE = "/lay/generate";


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
