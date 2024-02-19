package com.littlesekii.authapi.domain.user.service;

import com.littlesekii.authapi.domain.user.entity.User;
import com.littlesekii.authapi.domain.user.entity.dto.UserAuthenticationDTO;
import com.littlesekii.authapi.domain.user.entity.dto.UserRegistrationDTO;
import com.littlesekii.authapi.domain.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserAuthService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserRegistrationDTO dto) {
        User newUser = repository.findByUsername(dto.username());

        if (newUser != null)
            return null;

        newUser = dto.toEntity();

        String encodedPassword = passwordEncoder.encode(dto.password());
        newUser.setPassword(encodedPassword);

        return repository.save(newUser);
    }

    public User authenticate(UserAuthenticationDTO dto, AuthenticationManager authManager) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        return (User) authManager.authenticate(usernamePassword).getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

}
