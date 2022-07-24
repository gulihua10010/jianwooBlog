package cn.jianwoo.blog.enums;

import cn.jianwoo.blog.constants.CacheKeyConstants;

public enum GtCaptchaEnum {

    /**
     * 登录//default
     */
    LOGIN(CacheKeyConstants.LOGIN_CAPTCHA_AUTH, "login"),
    /**
     * 忘记密码
     */
    FORGET_PASSWORD(CacheKeyConstants.FORGET_CAPTCHA_AUTH, "forget_pwd"),

    ;


    /**
     * name
     */
    private String cacheKey;
    /**
     * value
     */
    private String apiTypeValue;

    public String getCacheKey() {
        return this.cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getApiTypeValue() {
        return this.apiTypeValue;
    }

    public void setApiTypeValue(String apiTypeValue) {
        this.apiTypeValue = apiTypeValue;
    }

    GtCaptchaEnum(String cacheKey, String apiTypeValue) {
        this.cacheKey = cacheKey;
        this.apiTypeValue = apiTypeValue;
    }

    public static String getKey(String value) {
        GtCaptchaEnum[] arry = GtCaptchaEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getApiTypeValue().equals(value)) {
                return arry[i].getCacheKey();
            }
        }
        return LOGIN.getCacheKey();
    }


}
