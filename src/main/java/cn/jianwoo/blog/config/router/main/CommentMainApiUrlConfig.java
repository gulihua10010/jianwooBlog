package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-10 18:03
 */
public class CommentMainApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/comment";
    public static final String URL_COMMENT_ARTICLE_QUERY_PAGE_LIST = "/article/query/page/list";
    public static final String URL_COMMENT_CREATE = "/create";
    public static final String URL_COMMENT_UPDATE = "/update";
    public static final String URL_COMMENT_PRAISE_ADD = "/praise/add";
    public static final String URL_COMMENT_REMOVE = "/remove";

}
