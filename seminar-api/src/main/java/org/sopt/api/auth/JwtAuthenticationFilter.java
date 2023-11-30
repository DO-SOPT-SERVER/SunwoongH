package org.sopt.api.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.api.auth.jwt.JwtProvider;
import org.sopt.api.auth.jwt.JwtValidator;
import org.sopt.api.common.Constants;
import org.sopt.common.error.ErrorStatus;
import org.sopt.common.error.UnauthorizedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.sopt.api.auth.UserAuthentication.createDefaultUserAuthentication;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtValidator jwtValidator;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String accessToken = getAccessToken(request);
        jwtValidator.validateAccessToken(accessToken);
        setAuthentication(request, jwtProvider.getSubject(accessToken));
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader(Constants.AUTHORIZATION);
        if (StringUtils.hasText(accessToken) && accessToken.startsWith(Constants.BEARER)) {
            return accessToken.substring(Constants.BEARER.length());
        }
        throw new UnauthorizedException(ErrorStatus.INVALID_ACCESS_TOKEN);
    }

    private void setAuthentication(HttpServletRequest request, Long memberId) {
        UserAuthentication authentication = createDefaultUserAuthentication(memberId);
        createWebAuthenticationDetailsAndSet(request, authentication);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    private void createWebAuthenticationDetailsAndSet(HttpServletRequest request, UserAuthentication authentication) {
        WebAuthenticationDetailsSource webAuthenticationDetailsSource = new WebAuthenticationDetailsSource();
        WebAuthenticationDetails webAuthenticationDetails = webAuthenticationDetailsSource.buildDetails(request);
        authentication.setDetails(webAuthenticationDetails);
    }
}
