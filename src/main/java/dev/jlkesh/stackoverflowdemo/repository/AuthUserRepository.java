package dev.jlkesh.stackoverflowdemo.repository;

import dev.jlkesh.stackoverflowdemo.domains.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsername(String username);

}
