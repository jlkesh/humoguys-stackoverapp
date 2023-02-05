package dev.jlkesh.stackoverflowdemo.services;

import dev.jlkesh.stackoverflowdemo.domains.AuthUser;
import dev.jlkesh.stackoverflowdemo.dtos.AuthUserCreateDTO;
import dev.jlkesh.stackoverflowdemo.repository.AuthUserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record AuthUserService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
    public AuthUser save(@NonNull AuthUserCreateDTO dto) {
        AuthUser authUser = AuthUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .email(dto.email())
                .status(AuthUser.Status.ACTIVE)
                .build();
        authUserRepository.save(authUser);
        log.info("User created :: {}", authUser);
        return authUser;
    }
}
