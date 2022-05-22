package cn.jianwoo.blog.service.biz.impl;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.entity.Admin;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthUserTokenBO;
import cn.jianwoo.blog.service.base.AdminBaseService;
import cn.jianwoo.blog.util.JwUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-06 15:54
 */
@Service("userDetailsService")
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AdminBaseService adminBaseService;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info("==>>UserDetailServiceImpl.loadUserByUsername start...,the name is {} ", name);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 判断用户是否存在
        Admin admin = null;
        try {
            if (JwUtil.isEmail(name)) {
                admin = adminBaseService.queryAdminByEmail(name);
            } else {

                admin = adminBaseService.queryAdminByLoginId(name);
            }
        } catch (JwBlogException e) {
            log.error("user {} does not exist.", name);
            throw new UsernameNotFoundException("用户名或者邮箱不存在");
        }

        authorities.add(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + Constants.ADMIN.toUpperCase(Locale.ROOT)));
        // 这里直接注入角色，因为JWT已经验证了用户合法性，所以principal和credentials直接为null即可
        log.info("==>>UserDetailServiceImpl.loadUserByUsername end... ");

        return new AuthUserTokenBO(admin.getUsername(), admin.getPassword(), authorities);
    }
}
