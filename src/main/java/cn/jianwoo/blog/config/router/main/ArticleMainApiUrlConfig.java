package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-10 18:03
 */
public class ArticleMainApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/article";
    public static final String URL_ARTICLE_QUERY_PAGE_LIST = "/query/page/list";
    public static final String URL_ARTICLE_QUERY_RECOMMEND_LIST = "/query/recommend/list";
    public static final String URL_ARTICLE_QUERY_DETAIL_RECOMMEND_LIST = "/query/detail/recommend/list";
    public static final String URL_ARTICLE_QUERY_DETAIL = "/query/detail";
    public static final String URL_ARTICLE_PRAISE_ADD = "/praise/add";
    public static final String URL_ARTICLE_PASSWORD_VERIFY = "/password/verify";
    public static final String URL_ARTICLE_MONTH_DATE_PUBLISH_QUERY = "/month/date/publish/query";

}
