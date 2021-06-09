package cn.jianwoo.blog.filter;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.security.token.AuthUserTokenBO;
import cn.jianwoo.blog.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理器
 *
 * @author gulh
 */
@Component
@Slf4j
public class SecurityLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private CacheStore<String, String> jwCacheStore;
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        AuthUserTokenBO user = (AuthUserTokenBO) authentication.getPrincipal();
        log.info("登出成功: {}", user.getUsername());
        Long uid= (Long) request.getSession().getAttribute(Constants.CURRENT_USER);
        jwCacheStore.delete(SecurityUtils.buildAccessTokenKey(uid));
        request.getSession().removeAttribute(Constants.CURRENT_USER);

    }

}
