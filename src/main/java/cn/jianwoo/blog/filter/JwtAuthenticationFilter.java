package cn.jianwoo.blog.filter;

import cn.hutool.extra.spring.SpringUtil;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.router.admin.MsgApiUrlConfig;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.enums.LoginEventTypeEnum;
import cn.jianwoo.blog.event.LoginLogEvent;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.bo.AdminBO;
import cn.jianwoo.blog.service.bo.UserBO;
import cn.jianwoo.blog.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-06 15:35
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    static final String USER_KEY = "USER_ID";

    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application");
    private final static String accessTokenExpireSec = RESOURCE_BUNDLE.getString("access.token.expired.seconds");
    private final CacheStore<String, Object> jwCacheStore = SpringUtil.getBean(CacheStore.class);
    private final ApplicationContext applicationContext = SpringUtil.getApplicationContext();

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("==>>JwtAuthenticationFilter.doFilterInternal start, url: [{}]", request.getServletPath());
        if (isProtectedUrl(request)) {
            UserBO userBO = new UserBO();
            JwAuthenticationToken authenticationToken = getAuthentication(request, response, userBO);
            if (authenticationToken == null) {
                if (StringUtils.isNotBlank(userBO.getName())) {
                    LoginLogEvent event = new LoginLogEvent(this, userBO.getName(), request);
                    event.setEventTypeEnum(LoginEventTypeEnum.LOGOUT);
                    event.setLoginDesc(Constants.LOGOUT_DESC2);
                    event.setIsSuccess(true);
                    applicationContext.publishEvent(event);
                }
                log.info("==>>JwtAuthenticationFilter.doFilterInternal authenticationToken is null...");

                // 手动设置异常
//                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION",
//                        new AuthenticationCredentialsNotFoundException("权限认证失败"));
                // request.getRequestDispatcher(CommAdminPageUrlConfig.URL_PREFIX +
                // CommAdminPageUrlConfig.URL_LOGIN).forward(request, response);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(Constants.CONTENT_TYPE_JSON);
                response.getWriter().write(JSONObject.toJSONString(
                        new BaseResponseDto(ExceptionConstants.UNAUTHORIZED, ExceptionConstants.UNAUTHORIZED_DESC)));
            } else {
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

        log.info("==>>JwtAuthenticationFilter.doFilterInternal end...");

    }


    private JwAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                    UserBO userBO) {
        log.info("==>>JwtAuthenticationFilter.getAuthentication start...");
        List<GrantedAuthority> authorities = new ArrayList<>();
        String accessToken = request.getHeader(Constants.ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)) {
            accessToken = request.getParameter(Constants.ACCESS_TOKEN);
        }
        String refreshToken = request.getHeader(Constants.ACCESS_TOKEN);
        if (StringUtils.isBlank(refreshToken)) {
            refreshToken = request.getParameter(Constants.ACCESS_TOKEN);
        }
        log.info("==>>JwtAuthenticationFilter.getAuthentication access token {}...", accessToken);
        log.info("==>>JwtAuthenticationFilter.getAuthentication refresh token {}...", refreshToken);

        if (StringUtils.isNotBlank(accessToken)) {
            Long oid = null;
            try {
                Claims claims = JwtUtils.parse(accessToken);
                Long refresgOid = null;
                Date refreshExpiredDate = null;
                if (StringUtils.isNotBlank(refreshToken)) {
                    Claims refreshClaims = JwtUtils.parse(refreshToken);
                    refresgOid = Long.parseLong(String.valueOf(refreshClaims.get(USER_KEY)));
                    refreshExpiredDate = refreshClaims.getExpiration();

                }
                oid = Long.parseLong(String.valueOf(claims.get(USER_KEY)));
                request.setAttribute(USER_KEY, oid);

                String loginIdCacheKey = MessageFormat.format(CacheKeyConstants.LOGIN_USER_STATUS, oid);
                boolean isLogin = (Boolean) jwCacheStore.get(loginIdCacheKey).orElse(false);
                // 解决服务器每次重启缓存失效，但是jwt有效的问题，调试时可以注释
//                if (!Boolean.TRUE.equals(isLogin)) {
//                    log.error("======>>user logout failed, user does not login.");
//                    return null;
//                }
                // 验证是否失效
                String invalidTokenKey = MessageFormat.format(CacheKeyConstants.INVALID_TOKEN, accessToken);
                if (jwCacheStore.hasKey(invalidTokenKey)) {
                    return null;
                }

                // token签发时间
                long issuedAt = claims.getIssuedAt().getTime();
                // 当前时间
                long currentTimeMillis = System.currentTimeMillis();
                // token过期时间
                long expirationTime = claims.getExpiration().getTime();

                // 1. 签发时间 < 当前时间 < (签发时间+((token过期时间-token签发时间) / 3 * 2)) 不刷新token
                // 2. (签发时间+((token过期时间-token签发时间) / 3 * 2)) < 当前时间 < token过期时间 刷新token并返回给前端
                // 3. tokne过期时间 < 当前时间 跳转登录，重新登录获取token
                // 验证token时间有效性
                if (!isReceiveMsgNotofy(request)) {
                    if (Objects.equals(oid, refresgOid) && refreshExpiredDate.getTime() > currentTimeMillis) {
                        if ((issuedAt + ((expirationTime - issuedAt) / 3 * 2)) < currentTimeMillis
                                && currentTimeMillis < expirationTime) {
                            log.info("JWT token (url=[{}]) will expire  in {} Millis, system will refreshToken.",
                                    request.getServletPath(), (expirationTime - currentTimeMillis));
                            String refreshAccessToken = JwtUtils.sign(claims, Long.parseLong(accessTokenExpireSec) * 1000);
                            // 将token放入响应头中
                            response.setContentType(Constants.CONTENT_TYPE_JSON);
                            response.addHeader(Constants.REFRESH_ACCESS_TOKEN, refreshAccessToken);
                        }
                    }
                }


            } catch (Exception e) {
                log.info("==>>JwtAuthenticationFilter.getAuthentication exec failed, e\r\n", e);
                return null;
            }
            // 从数据库中取出用户信息
            AdminBizService adminBizService = SpringUtil.getBean(AdminBizService.class);
            try {
                AdminBO admin = adminBizService.queryAdminByOid(oid);
                // 实际上不会为null，直接抛出异常
                if (admin == null) {
                    return null;
                }
                userBO.setName(admin.getUsername());
                Map param = new HashMap();
                param.put(Constants.LOGIN_IP, request.getRemoteAddr());

                authorities.add(
                        new SimpleGrantedAuthority(Constants.ROLE_PREFIX + Constants.ADMIN.toUpperCase(Locale.ROOT)));
                // 这里直接注入角色，因为JWT已经验证了用户合法性，所以principal和credentials直接为null即可
                return new JwAuthenticationToken(authorities, admin.getUsername(), null, param);
            } catch (JwBlogException e) {
                log.info("==>>JwtAuthenticationFilter.getAuthentication exec failed, e\r\n", e);
                return null;
            }
        }
        log.info("==>>JwtAuthenticationFilter.getAuthentication start...");
        return null;

    }


    // 只对/api/admin/*下请求拦截
    private boolean isProtectedUrl(HttpServletRequest request) {
        String path = request.getServletPath();
        return pathMatcher.match("/api/admin/**", path);
    }


    // 对 /admin/ 下请求单独处理
    private boolean isAdminUrl(HttpServletRequest request) {
        String path = request.getServletPath();
        return pathMatcher.match("/admin", path) || pathMatcher.match("/admin/**", path);
    }

    // 是否是验证token的api:/api/admin/jwt/verify/token
    private boolean isReceiveMsgNotofy(HttpServletRequest request) {
        String path = request.getServletPath();
        return pathMatcher.match(MsgApiUrlConfig.URL_PREFIX.concat(MsgApiUrlConfig.URL_MSG_TIMER_NEWEST_QUERY_LIST), path);
    }
}
