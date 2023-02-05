package dev.jlkesh.stackoverflowdemo.config;

import dev.jlkesh.stackoverflowdemo.domains.AuthRole;
import dev.jlkesh.stackoverflowdemo.domains.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class AuthUserDetails implements UserDetails {
    private final AuthUser authUser;

    public AuthUserDetails(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Objects.requireNonNullElse(authUser.getRoleSet(), Collections.<AuthRole>emptySet())
                .stream()
                .map(AuthRole::authority)
                .toList();
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !authUser.getStatus().equals(AuthUser.Status.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authUser.getStatus().equals(AuthUser.Status.ACTIVE);
    }
}
