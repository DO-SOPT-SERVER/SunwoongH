package org.sopt.api.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    private UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static UserAuthentication createDefaultUserAuthentication(Long memberId) {
        return new UserAuthentication(memberId, null, null);
    }
}
