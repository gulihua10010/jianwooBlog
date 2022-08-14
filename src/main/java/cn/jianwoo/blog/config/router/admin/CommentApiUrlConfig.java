package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class CommentApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/comment";
    public static final String URL_COMMENT_CREATE = "/create";
    public static final String URL_COMMENT_REMOVE = "/remove";
    public static final String URL_COMMENT_REMOVE_LIST = "/remove/list";
    public static final String URL_COMMENT_QUERY_LIST = "/query/list";
    public static final String URL_COMMENT_QUERY_SEARCH = "/query/search";
    public static final String URL_COMMENT_QUERY_ARTICLE_LIST = "/query/article/list";
    public static final String URL_COMMENT_REPLY = "/reply";
    public static final String URL_COMMENT_READ_LIST = "/read/list";
    public static final String URL_COMMENT_READ = "/read";
    public static final String URL_COMMENT_INFO = "/info/{id}";



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
