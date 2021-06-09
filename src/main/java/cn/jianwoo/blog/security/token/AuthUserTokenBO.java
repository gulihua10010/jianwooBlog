package cn.jianwoo.blog.security.token;

import cn.jianwoo.blog.security.token.AuthToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-08 9:15
 */
public class AuthUserTokenBO extends User {
    private static final long serialVersionUID = -6638412688910711239L;

    private AuthToken authToken;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public AuthToken getAuthToken() {
        return this.authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public AuthUserTokenBO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AuthUserTokenBO(String username, String password, Collection<? extends GrantedAuthority> authorities, AuthToken authToken) {
        super(username, password, authorities);
        this.authToken = authToken;
    }

    public AuthUserTokenBO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                           boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, AuthToken authToken) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.authToken = authToken;
    }
}
