package cn.jianwoo.blog.util;

public class UuidUtil {
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "").trim();
    }
}
