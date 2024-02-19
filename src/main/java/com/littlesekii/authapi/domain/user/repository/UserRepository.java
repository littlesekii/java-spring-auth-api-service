package com.littlesekii.authapi.domain.user.repository;

import com.littlesekii.authapi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
