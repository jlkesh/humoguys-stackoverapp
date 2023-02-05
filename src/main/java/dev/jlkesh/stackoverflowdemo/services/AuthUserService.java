package dev.jlkesh.stackoverflowdemo.services;

import dev.jlkesh.stackoverflowdemo.config.AuthUserDetails;
import dev.jlkesh.stackoverflowdemo.domains.AuthUser;
import dev.jlkesh.stackoverflowdemo.dtos.AuthUserCreateDTO;
import dev.jlkesh.stackoverflowdemo.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record AuthUserService(AuthUserRepository authUserRepository,
                              PasswordEncoder passwordEncoder) implements UserDetailsService {
    public AuthUser save(@NonNull AuthUserCreateDTO dto) {
        AuthUser authUser = AuthUser.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .status(AuthUser.Status.ACTIVE)
                .build();
        authUserRepository.save(authUser);
        log.info("User created :: {}", authUser);
        return authUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found by %s".formatted(username)));
        return new AuthUserDetails(authUser);
    }
}
