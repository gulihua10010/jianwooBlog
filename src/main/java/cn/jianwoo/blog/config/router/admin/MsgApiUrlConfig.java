package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class MsgApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/msg";
    public static final String URL_MSG_QUERY_LIST = "/query/list";
    public static final String URL_MSG_UNREAD_COUNT = "/unread/count";
    public static final String URL_MSG_TIMER_NEWEST_QUERY_LIST = "/timer/newest/query/list";

    public static final String URL_MSG_READ = "/read";
    public static final String URL_ALL_MSG_READ = "/all/read";


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
