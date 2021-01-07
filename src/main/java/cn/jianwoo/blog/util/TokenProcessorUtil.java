package cn.jianwoo.blog.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-07 16:24
 */
public class TokenProcessorUtil {

    public static String generateToken(HttpServletRequest request) {
        String subToken = UuidUtil.getUUID();
        request.setAttribute("subToken", subToken);
        return subToken;
    }
}
