package com.littlesekii.authapi.infra.security.filter;

import com.littlesekii.authapi.domain.token.service.TokenService;
import com.littlesekii.authapi.domain.user.service.UserAuthService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserAuthService userAuthService;

    public SecurityFilter(TokenService tokenService, UserAuthService userAuthService) {
        this.tokenService = tokenService;
        this.userAuthService = userAuthService;
    }

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = recoverHeaderToken(request);

        if (token != null) {
            String subject = tokenService.validate(token);
            UserDetails userDetails = userAuthService.loadUserByUsername(subject);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String recoverHeaderToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null)
            return null;

        return authHeader.replace("Bearer: ", "");
    }
}
