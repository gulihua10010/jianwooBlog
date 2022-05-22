package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-10 18:03
 */
public class ArticleMainApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/article";
    public static final String URL_QUERY_PAGE_LIST = "/query/page/list";
    public static final String URL_QUERY_RECOMMEND_LIST = "/query/recommend/list";
    public static final String URL_QUERY_DETAIL = "/query/detail?id={id}";
    public static final String URL_PRAISE_ADD = "/praise/add";
    public static final String URL_PASSWORD_VERIFY = "/password/verify";

}
