package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-08 15:53
 */
@Slf4j
@Component
public class ProcessTokenUtil {
    public static final String LOGIN_ID = "LOGIN_ID";
    public static final String TOKEN_KEY = "REQUEST_ID_KEY";
    public static final String REQUEST_ID = "requestId";

    public String getSubTokenKey(HttpServletRequest request, String pageId) {
        String suffix = request.getRemoteAddr().replace(".", "_");

        StringBuilder key = new StringBuilder(TOKEN_KEY);
        key.append(Constants.SEPARATE_HYPHEN).append(suffix)
                .append(Constants.SEPARATE_HYPHEN).append(pageId);
        return key.toString();
    }


    public String getTokenValue(HttpServletRequest request) {
        String token = JwUtil.randomUUIDWithoutDash();
        request.setAttribute(REQUEST_ID, token);
        return token;
    }

    public String generateToken(HttpServletRequest request, String pageId) {
//        String key = getSubTokenKey(request, pageId);
        String value = getTokenValue(request);
//        request.getSession().setAttribute(key, value);
        return value;
    }
}
