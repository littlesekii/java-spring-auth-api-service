package com.littlesekii.authapi.domain.auth.service;

import com.littlesekii.authapi.domain.auth.controller.dto.AuthTokenDTO;
import com.littlesekii.authapi.domain.auth.controller.dto.UserAuthenticationDTO;
import com.littlesekii.authapi.domain.auth.controller.dto.UserRegistrationDTO;
import com.littlesekii.authapi.domain.token.service.TokenService;
import com.littlesekii.authapi.domain.user.entity.User;
import com.littlesekii.authapi.domain.user.service.UserAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserAuthService userAuthService;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthenticationService(UserAuthService userAuthService, AuthenticationManager authManager, TokenService tokenService) {
        this.userAuthService = userAuthService;
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    public User register(UserRegistrationDTO dto) {
        return userAuthService.register(dto);
    }

    public AuthTokenDTO authenticate(UserAuthenticationDTO dto) {
        User userAuthenticated = userAuthService.authenticate(dto, authManager);
        return new AuthTokenDTO(tokenService.generate(userAuthenticated));
    }

}
