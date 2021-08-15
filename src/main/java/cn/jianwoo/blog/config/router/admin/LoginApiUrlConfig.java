package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class LoginApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/passport/login";
    public static final String URL_LOGIN_CAPTCHA_INIT = "/captcha/init";
    public static final String URL_LOGIN_CAPTCHA_VERIFY = "/captcha/verify";
    public static final String URL_LOGIN_AUTH = "/auth";
    public static final String URL_LOGIN_OUT = "/exit";
    public static final String URL_LOGIN_VERIFY_TOKEN = "/verify/token";


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
