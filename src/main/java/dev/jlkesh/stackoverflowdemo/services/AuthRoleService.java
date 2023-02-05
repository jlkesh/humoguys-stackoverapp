package dev.jlkesh.stackoverflowdemo.services;

import dev.jlkesh.stackoverflowdemo.repository.AuthRoleRepository;
import org.springframework.stereotype.Service;

@Service
public record AuthRoleService(AuthRoleRepository authRoleRepository) {

}
