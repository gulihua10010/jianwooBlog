package cn.jianwoo.blog.config;

/**
 * GeetestWeb配置文件
 */
public class GeetestConfig {

    // 填入自己的captcha_id和private_key
    private static final String geetestId = "c57f7c1f9daf244c3ae62f0e7c6ea6c6";
    private static final String geetestKey = "1ffcf1559c1eae2cfedea93e635a70fd";
    private static final boolean newFailBack = true;

    public static String getGeetestId() {
        return geetestId;
    }

    public static String getGeetestKey() {
        return geetestKey;
    }

    public static boolean getNewFailBack() {
        return newFailBack;
    }
}
