package com.littlesekii.authapi.domain.user.entity;

import com.littlesekii.authapi.domain.user.entity.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Entity(name = "t_user")
@Table(name = "t_user")
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = -4343597299163474914L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String password;
    private UserRole role;
    private Boolean verified;
    private Boolean active;

    /* CONSTRUCTORS */
    public User() {
    }

    public User(UUID id, String username, String password, UserRole role, Boolean verified, Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.verified = verified;
        this.active = active;
    }

    /* GETTERS & SETTERS */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    /* EQUALS & HASH CODE */
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /* USER DETAILS IMPLEMENTATIONS */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("USER_ROLE"));

        if (this.role == UserRole.ADMIN)
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return roles;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
