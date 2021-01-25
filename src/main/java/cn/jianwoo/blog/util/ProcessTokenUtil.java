package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-08 15:53
 */
@Slf4j
public class ProcessTokenUtil {
    public static final String LOGIN_ID = "LOGIN_ID";
    public static final String TOKEN_KEY = "SUB_TOEKN_KEY";
    public static final String SUB_TOKEN = "subToken";


    public static String getSubTokenKey(HttpServletRequest request, String pageId) {

        String loginId = (String) request.getSession().getAttribute(LOGIN_ID);
        if (StringUtils.isBlank(loginId)) {
            loginId = LOGIN_ID;
        }
        StringBuilder key = new StringBuilder(TOKEN_KEY);
        key.append(Constants.SEPARATE_HYPHEN).append(loginId)
                .append(Constants.SEPARATE_HYPHEN).append(pageId);
        return key.toString();
    }


    public static String getTokenValue(HttpServletRequest request) {
        String token = JwUtil.randomUUIDWithoutDash();
        request.setAttribute(SUB_TOKEN, token);
        return token;
    }

    public static String generateToken(HttpServletRequest request, String pageId) {
        String key = ProcessTokenUtil.getSubTokenKey(request, pageId);
        String value = getTokenValue(request);
        request.getSession().setAttribute(key, value);
        return value;
    }
}
