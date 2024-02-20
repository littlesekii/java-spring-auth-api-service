package com.littlesekii.authapi.domain.auth.controller;

import com.littlesekii.authapi.domain.auth.controller.dto.*;
import com.littlesekii.authapi.domain.auth.service.AuthenticationService;
import com.littlesekii.authapi.domain.user.entity.User;
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
    public ResponseEntity<AuthTokenDTO> authenticate(@RequestBody @Valid UserAuthenticationDTO req) {
        AuthTokenDTO res = service.authenticate(req);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDTO> register(@RequestBody @Valid UserRegistrationDTO req) {
        User user = service.register(req);

        if (user == null)
            return ResponseEntity.badRequest().build();

        MessageDTO res = new MessageDTO("User successfully registered.");
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<MessageDTO> validate() {
        MessageDTO res = new MessageDTO("Token successfully validated.");
        return ResponseEntity.ok().body(res);
    }


}
