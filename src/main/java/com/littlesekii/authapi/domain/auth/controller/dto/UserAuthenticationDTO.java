package com.littlesekii.authapi.domain.auth.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(@NotBlank String username, @NotBlank String password) {
}
