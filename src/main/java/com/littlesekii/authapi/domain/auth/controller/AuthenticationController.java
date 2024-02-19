package com.littlesekii.authapi.domain.auth.controller;

import com.littlesekii.authapi.domain.auth.service.AuthenticationService;
import com.littlesekii.authapi.domain.user.entity.User;
import com.littlesekii.authapi.domain.user.entity.dto.UserAuthenticationDTO;
import com.littlesekii.authapi.domain.user.entity.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<User> authenticate(@RequestBody @Valid UserAuthenticationDTO req) {
        User res = service.authenticate(req);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegistrationDTO req) {
        User res = service.register(req);

        if (res == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(res);
    }


}
