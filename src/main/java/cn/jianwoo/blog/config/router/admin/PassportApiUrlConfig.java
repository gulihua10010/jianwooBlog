package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class PassportApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/passport";
    public static final String URL_PASSPORT_CAPTCHA_INIT = "/captcha/init";
    public static final String URL_PASSPORT_CAPTCHA_VERIFY = "/captcha/verify";
    public static final String URL_PASSPORT_LOGIN_AUTH = "/login/auth";
    public static final String URL_PASSPORT_LOGIN_OUT = "/login/exit";
    public static final String URL_LOGIN_VERIFY_TOKEN = "/login/verify/token";
    public static final String URL_PASSPORT_FORGET_EMAIL_CAPTCHA_SEND = "/forget/email/captcha/send";
    public static final String URL_PASSPORT_FORGET_CAPTCHA_AUTH = "/forget/captcha/auth";
    public static final String URL_USER_FORGET_PASSWORD_MODIFY = "/forget/password/modify";
    public static final String URL_FETCH_CAPTCHA= "/fetch/captcha";



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
