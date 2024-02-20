package com.littlesekii.authapi.domain.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.littlesekii.authapi.domain.token.exception.AuthTokenCreateException;
import com.littlesekii.authapi.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SuppressWarnings("unused")
@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generate(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(createExpirationTime())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException e) {
            throw new AuthTokenCreateException("Error while generating token.");
        }
    }

    public String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String subject = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

            return subject;
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant createExpirationTime() {
        return LocalDateTime.now().plusMinutes(30)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
