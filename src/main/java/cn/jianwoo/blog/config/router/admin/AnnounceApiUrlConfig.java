package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class AnnounceApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/announcement";
    public static final String URL_ANNOUNCEMENT_CREATE = "/create";
    public static final String URL_ANNOUNCEMENT_REMOVE = "/remove";
    public static final String URL_ANNOUNCEMENT_UPDATE = "/update";
    public static final String URL_ANNOUNCEMENT_QUERY_LIST = "/query/list";
    public static final String URL_ANNOUNCEMENT_EDIT = "/edit/{id}";
    public static final String URL_ANNOUNCEMENT_VOID = "/void";
    public static final String URL_ANNOUNCEMENT_REVERT = "/revert";
    public static final String URL_ANNOUNCEMENT_VOID_LIST = "/list/void";
    public static final String URL_ANNOUNCEMENT_REVERT_LIST = "/list/revert";
    public static final String URL_ANNOUNCEMENT_REMOVE_LIST = "/list/remove";


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
