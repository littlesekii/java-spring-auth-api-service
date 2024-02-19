package com.littlesekii.authapi.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(
    //        @RequestBody UserAuthenticationDTO req
    ) {
    //    String token = service.authenticate(req);
        return ResponseEntity.ok().build(
        //        token
        );
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
    //        @RequestBody UserRegistrationDTO req
    ) {
    //    if (!service.register(req))
    //       return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }


}
