package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:40
 */
public class ConsoleApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/console";
    public static final String URL_RECENT_ARTICLE_PUBLISHED_QUERY = "/recent/article/published/query";
    public static final String URL_RECENT_ARTICLE_DRAFT_QUERY = "/recent/article/draft/query";
    public static final String URL_RECENT_COMMENT_QUERY = "/recent/comment/query";
    public static final String URL_RECENT_MESSAGE_BOARD_QUERY = "/recent/message/board/query";
    public static final String URL_PUBLISHED_ARTICLES_COUNT = "/published/articles/count";
    public static final String URL_DRAFT_ARTICLES_COUNT = "/draft/articles/count";
    public static final String URL_COMMENT_COUNT = "/comment/count";
    public static final String URL_TAGS_COUNT = "/tags/count";


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
