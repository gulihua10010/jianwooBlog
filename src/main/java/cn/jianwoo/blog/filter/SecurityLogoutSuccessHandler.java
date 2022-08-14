package cn.jianwoo.blog.filter;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.enums.LoginEventTypeEnum;
import cn.jianwoo.blog.event.LoginLogEvent;
import cn.jianwoo.blog.util.JwUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        String loginEncryptStr = request.getHeader(Constants.LOGIN_ID_SECRET);
        String accessToken = request.getHeader(Constants.ACCESS_TOKEN);
        response.setContentType(Constants.CONTENT_TYPE_JSON);

        if (StringUtils.isBlank(loginEncryptStr)) {
            log.error("======>>user logout failed, login Secret is empty.");
            response.getWriter().write(processFailMsg(ExceptionConstants.LOGOUT_FAILED_MSG_1));
            return;
        }
        String decryptStr = null;
        try {
            decryptStr = JwUtil.decrypt(loginEncryptStr);
        } catch (Exception e) {
            log.error("======>>user logout failed, e:\r\n", e);
            response.getWriter().write(processFailMsg(ExceptionConstants.LOGOUT_FAILED_MSG_3));
            return;
        }
        if (StringUtils.isBlank(decryptStr)) {
            log.error("======>>user logout failed, login Secret is empty.");
            response.getWriter().write(processFailMsg(ExceptionConstants.LOGOUT_FAILED_MSG_1));
            return;

        }
        Long uid = Long.parseLong(decryptStr);
        String loginIdCacheKey = MessageFormat.format(CacheKeyConstants.LOGIN_USER_STATUS, uid);
        boolean isLogin = (Boolean) jwCacheStore.get(loginIdCacheKey).orElse(false);

        String loginIDNameKey = MessageFormat.format(CacheKeyConstants.ADMIN_OID_NAME_KEY, uid);
        String name = (String) jwCacheStore.get(loginIDNameKey).orElse(null);
        LoginLogEvent event = new LoginLogEvent(this, name, request);


        if (!Boolean.TRUE.equals(isLogin)) {
            log.error("======>>user logout failed, user does not login.");
            response.getWriter().write(processFailMsg(ExceptionConstants.LOGOUT_FAILED_MSG_2));
            if (StringUtils.isNotBlank(name)) {
                event.setEventTypeEnum(LoginEventTypeEnum.LOGOUT);
                event.setLoginDesc(Constants.LOGOUT_DESC1);
                event.setIsSuccess(false);
                event.setReason(ExceptionConstants.LOGOUT_FAILED_MSG_2);
                applicationContext.publishEvent(event);
            }
            return;
        }
        jwCacheStore.put(loginIdCacheKey, false, 7, TimeUnit.DAYS);
        response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGOUT));

        //把token存放缓存，置以失效
        String invalidTokenKey = MessageFormat.format(CacheKeyConstants.INVALID_TOKEN, accessToken);
        jwCacheStore.put(invalidTokenKey, accessToken, 7, TimeUnit.DAYS);

        log.info("logout successfully: [id = {}]", uid);
        String accessCacheKey = MessageFormat.format(CacheKeyConstants.TOKEN_ACCESS_CACHE, uid);
        jwCacheStore.delete(accessCacheKey);
        if (StringUtils.isNotBlank(name)) {
            event.setEventTypeEnum(LoginEventTypeEnum.LOGOUT);
            event.setLoginDesc(Constants.LOGOUT_DESC1);
            event.setIsSuccess(true);
            applicationContext.publishEvent(event);
        }

    }

    private String processSuccessMsg(String msg) {
        return JSON.toJSONString(new BaseResponseDto(msg));
    }

    private String processFailMsg(String msg) {
        return JSON.toJSONString(new BaseResponseDto(ExceptionConstants.LOGOUT_FAILED, msg));
    }

}
