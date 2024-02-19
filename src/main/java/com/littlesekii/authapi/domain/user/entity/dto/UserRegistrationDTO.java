package com.littlesekii.authapi.domain.user.entity.dto;

import com.littlesekii.authapi.domain.user.entity.User;
import com.littlesekii.authapi.domain.user.entity.enums.UserRole;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationDTO(@NotBlank String username, @NotBlank String password) {

    public User toEntity() {
        return new User(
                null,
                username,
                password,
                UserRole.USER,
                true,
                true
        );
    }
}
