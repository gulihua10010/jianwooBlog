package cn.jianwoo.blog.interceptor;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-26 9:37
 */
@Component
@Slf4j
public class LoginHandleInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheStore<String, String> jwCacheStore;
    public static final String LOGIN_SESSION = "JIANWOO.LOGIN.SESSION";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long uid= (Long) request.getSession().getAttribute(Constants.CURRENT_USER);
        Optional<String> token=jwCacheStore.get(SecurityUtils.buildAccessTokenKey(uid));
        if (!token.isPresent())
        {
             log.info("token expire, logout for uid [{}]", uid);
            request.getRequestDispatcher(CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN).forward(request, response);
            return false;
        }

        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
