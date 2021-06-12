package cn.jianwoo.blog.config;

import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.config.router.admin.LoginApiUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.filter.AdminAuthenticationProvider;
import cn.jianwoo.blog.filter.JwtAuthenticationFilter;
import cn.jianwoo.blog.filter.LoginFilter;
import cn.jianwoo.blog.filter.SecurityLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.NullSecurityContextRepository;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-06 17:18
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AdminAuthenticationProvider adminAuthenticationProvider;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private SecurityLogoutSuccessHandler securityLogoutSuccessHandler;

    private static final String[] EXCLUDE_PATH = {
            CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN,
            Constants.ALL_ADMIN_PASSPORT,
            Constants.ALL_STATIC_PATTERNS,
            Constants.ALL_RES_PATTERNS
    };
    private static final String[] AUTHENTICATION_PATH = {
            Constants.ALL_ADMIN_API,
            Constants.ALL_FILE_API,
//            Constants.ALL_ADMIN_PAGE
    };


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .and().authenticationProvider(adminAuthenticationProvider)
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(EXCLUDE_PATH);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTHENTICATION_PATH).access("hasRole('ADMIN')")
                .and()
                .formLogin()
                .loginPage(CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN)
//                .loginProcessingUrl(LoginApiUrlConfig.URL_PREFIX.concat(LoginApiUrlConfig.URL_LOGIN_AUTH))
//                .failureUrl(CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN)
                .defaultSuccessUrl(CommAdminPageUrlConfig.URL_PREFIX)
//                .failureHandler(securityAuthenticationFailureHandler)

                .permitAll()
                .and()

                .logout()
                .logoutUrl(LoginApiUrlConfig.URL_PREFIX + LoginApiUrlConfig.URL_LOGIN_OUT)
//                .logoutSuccessHandler(securityLogoutSuccessHandler)
                .logoutSuccessUrl(CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_LOGIN)
                .permitAll()
                .and()

//                .anyRequest()
//                .authenticated()
                //不需要session来控制,所以这里可以去掉
                .securityContext().securityContextRepository(new NullSecurityContextRepository()).and()
                //开启匿名访问
                .anonymous().and()
                .addFilter(loginFilter())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),LoginFilter.class)
                .logout().permitAll();
        http.csrf().disable();


    }


    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(authenticationManager());
        loginFilter.setFilterProcessesUrl(LoginApiUrlConfig.URL_PREFIX.concat(LoginApiUrlConfig.URL_LOGIN_AUTH));
        return loginFilter;

    }
}
