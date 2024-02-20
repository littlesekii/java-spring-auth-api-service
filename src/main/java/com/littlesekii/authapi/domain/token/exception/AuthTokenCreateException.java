package com.littlesekii.authapi.domain.token.exception;

import java.io.Serial;

public class AuthTokenCreateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2933230096785333398L;

    public AuthTokenCreateException(String msg) {
        super(msg);
    }
}
