package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class BlackIpApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/black/ip";
    public static final String URL_BLACK_IP_QUERY_LIST = "/query/list";
    public static final String URL_BLACK_IP_ADD = "/add";
    public static final String URL_BLACK_IP_ADD_LIST = "/add/list";
    public static final String URL_BLACK_IP_REMOVE = "/remove";
    public static final String URL_BLACK_IP_REMOVE_LIST = "/remove/list";


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
