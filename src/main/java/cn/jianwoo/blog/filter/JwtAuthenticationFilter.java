package cn.jianwoo.blog.filter;

import cn.hutool.extra.spring.SpringUtil;
import cn.jianwoo.blog.base.BaseRequestDto;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dao.base.AdminTransDao;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.DaoException;
import cn.jianwoo.blog.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    public static String accessTokenExpireSec = RESOURCE_BUNDLE.getString("access.token.expired.seconds");


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("==>>JwtAuthenticationFilter.doFilterInternal start, url: [{}]",  request.getServletPath());
        if (isProtectedUrl(request)) {
            JwAuthenticationToken authenticationToken = getAuthentication(request);
            if (authenticationToken == null) {
                log.info("==>>JwtAuthenticationFilter.doFilterInternal authenticationToken is null...");
                //手动设置异常
                request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new AuthenticationCredentialsNotFoundException("权限认证失败"));
               // request.getRequestDispatcher(CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN).forward(request, response);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(Constants.CONTENT_TYPE_JSON);
                response.getWriter().write(JSONObject.toJSONString(new BaseResponseDto(ExceptionConstants.UNAUTHORIZED,ExceptionConstants.UNAUTHORIZED_DESC)));
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

    private JwAuthenticationToken getAuthentication(HttpServletRequest request) {
        log.info("==>>JwtAuthenticationFilter.getAuthentication start...");
        List<GrantedAuthority> authorities = new ArrayList<>();
        String token = request.getHeader(Constants.ACCESS_TOKEN);
        if (StringUtils.isBlank(token))
        {
            token = request.getParameter(Constants.ACCESS_TOKEN);
        }
        log.info("==>>JwtAuthenticationFilter.getAuthentication token {}...", token);
        if (StringUtils.isNotBlank(token)) {
            Long oid = null;
            try {
                Claims claims = JwtUtils.parse(token);

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
                if ((issuedAt + ((expirationTime - issuedAt) / 3 * 2 )) < currentTimeMillis && currentTimeMillis < expirationTime) {
                    log.info("JWT token will expire  in {} Millis, system will refreshToken.",(expirationTime - issuedAt) / 3);
                    String refreshToken = JwtUtils.sign(claims, Long.parseLong(accessTokenExpireSec) * 1000);
                    claims.put(Constants.REFRESH_TOKEN, refreshToken);

                }



                oid = Long.parseLong(String.valueOf(claims.get(USER_KEY)));
                request.setAttribute(USER_KEY, oid);
            } catch (Exception e) {
                log.info("==>>JwtAuthenticationFilter.getAuthentication exec failed, e\r\n", e);
                return null;
            }
            // 从数据库中取出用户信息
            AdminTransDao adminTransDao = SpringUtil.getBean(AdminTransDao.class);
            try {
                Admin admin = adminTransDao.queryAdminByPrimaryKey(oid);
                if (admin == null) {
                    return null;
                }

                authorities.add(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + Constants.ADMIN.toUpperCase(Locale.ROOT)));
                // 这里直接注入角色，因为JWT已经验证了用户合法性，所以principal和credentials直接为null即可
                return new JwAuthenticationToken(authorities, null, null, null);
            } catch (DaoException e) {
                log.info("==>>JwtAuthenticationFilter.getAuthentication exec failed, e\r\n", e);
                return null;
            }
        }
        log.info("==>>JwtAuthenticationFilter.getAuthentication start...");
        return null;

    }

    //只对/api/admin/*下请求拦截
    private boolean isProtectedUrl(HttpServletRequest request) {
        String path = request.getServletPath();
        if (pathMatcher.match("/api/admin/**", path)) {

            return true;
        }
        return false;
    }
}
