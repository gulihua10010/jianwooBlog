package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2021-04-29 16:29
 */
public class WebConfigApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/config";
    //详细配置获取API
    public static final String URL_CONFIG_QUERY = "/query";
    public static final String URL_CONFIG_PAGE_COMM_QUERY = "/page/comm/query";
    public static final String URL_CONFIG_PAGE_FOOTER_QUERY = "/page/footer/query";
}
