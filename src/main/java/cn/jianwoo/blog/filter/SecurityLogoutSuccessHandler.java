package cn.jianwoo.blog.filter;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.security.util.SecurityUtils;
import cn.jianwoo.blog.util.JwUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登出成功处理器
 *
 * @author gulh
 */
@Component
@Slf4j
public class SecurityLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private CacheStore<String, Object> jwCacheStore;
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        String loginEncryptStr = request.getHeader(Constants.LOGIN_ID_SECRET);
        String decryptStr = null;
        try {
            decryptStr = JwUtil.decrypt(loginEncryptStr);
        } catch (Exception e) {
            log.error("======>>user logout failed, e:\r\n", e);
            response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGOUT));
            return;
        }
        if (StringUtils.isBlank(decryptStr)) {
            log.error("======>>user logout failed, login Secret is empty.");
            response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGOUT));
            return;

        }

        Map<Long, Object> userLogin = (Map<Long, Object>) jwCacheStore.get(Constants.LOGIN_USER_STATUS).orElse(new HashMap<>());
        Boolean isLogin = (Boolean) userLogin.get(Long.parseLong(decryptStr));
        if (!Boolean.TRUE.equals(isLogin)) {
            log.error("======>>user logout failed, user does not login.");
            response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGOUT));
            return;
        }
        userLogin.put(Long.parseLong(decryptStr), false);
        jwCacheStore.put(Constants.LOGIN_USER_STATUS, userLogin);
        response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGOUT));


//        AuthUserTokenBO user = (AuthUserTokenBO) authentication.getPrincipal();
//        log.info("登出成功: {}", user.getUsername());
        Long uid = (Long) request.getSession().getAttribute(Constants.CURRENT_USER);
        jwCacheStore.delete(SecurityUtils.buildAccessTokenKey(uid));
//        request.getSession().removeAttribute(Constants.CURRENT_USER);

    }

    private String processSuccessMsg(String msg) {
        return JSON.toJSONString(new BaseResponseDto(msg));
    }

    private String processFailMsg(String msg) {
        return JSON.toJSONString(new BaseResponseDto(ExceptionConstants.LOGOUT_FAILED, msg));
    }

}
