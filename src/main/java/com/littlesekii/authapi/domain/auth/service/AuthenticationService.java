package com.littlesekii.authapi.domain.auth.service;

import com.littlesekii.authapi.domain.user.entity.User;
import com.littlesekii.authapi.domain.user.entity.dto.UserAuthenticationDTO;
import com.littlesekii.authapi.domain.user.entity.dto.UserRegistrationDTO;
import com.littlesekii.authapi.domain.user.service.UserAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserAuthService userAuthService;
    private final AuthenticationManager authManager;

    public AuthenticationService(UserAuthService userAuthService, AuthenticationManager authManager) {
        this.userAuthService = userAuthService;
        this.authManager = authManager;
    }


    public User register(UserRegistrationDTO dto) {
        return userAuthService.register(dto);
    }

    public User authenticate(UserAuthenticationDTO dto) {
        return userAuthService.authenticate(dto, authManager);
    }

}
