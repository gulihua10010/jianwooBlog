package cn.jianwoo.blog.interceptor;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.page.CommBackendPageUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-26 9:37
 */
@Component
public class LoginHandleInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheStore<String, String> jwCacheStore;
    public static final String LOGIN_SESSION = "JIANWOO.LOGIN.SESSION";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = (String) request.getSession().getAttribute(LOGIN_SESSION);
        if (token == null) {
            request.getSession().setAttribute("msg", "没有权限请登录");
            request.getRequestDispatcher(CommBackendPageUrlConfig.URL_PREFIX + CommBackendPageUrlConfig.URL_LOGIN).forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
