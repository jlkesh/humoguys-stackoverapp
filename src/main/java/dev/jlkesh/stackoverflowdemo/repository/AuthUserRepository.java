package dev.jlkesh.stackoverflowdemo.repository;

import dev.jlkesh.stackoverflowdemo.domains.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
}
