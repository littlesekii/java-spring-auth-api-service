package com.littlesekii.authapi.domain.user.entity.dto;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(@NotBlank String username, @NotBlank String password) {
}
