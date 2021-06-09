package cn.jianwoo.blog.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-24 15:06
 */
public class JwAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 540L;
    private final Object principal;
    private Object credentials;

    private Map<String, Object> map;

    public JwAuthenticationToken(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);

    }


    public JwAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, Object credentials, Map<String, Object> map) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.map = map;
        super.setAuthenticated(true);

    }
    public JwAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);

    }

    public Map<String, Object> getParam() {
        return map;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
