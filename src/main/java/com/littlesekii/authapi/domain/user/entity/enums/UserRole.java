package com.littlesekii.authapi.domain.user.entity.enums;

@SuppressWarnings("unused")
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String toText() {
        return role;
    } 
    
}

