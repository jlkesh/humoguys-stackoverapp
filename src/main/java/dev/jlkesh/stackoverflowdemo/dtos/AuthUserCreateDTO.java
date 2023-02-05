package dev.jlkesh.stackoverflowdemo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthUserCreateDTO(
        @NotBlank(message = "Username can not be blank")
        String username,

        @NotBlank(message = "Password can not be blank")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password did not match requirements")
        String password,
        @NotBlank(message = "Confirm Password can not be blank")
        String confirmPassword,

        @NotBlank(message = "Email can not be blank")
        @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$", message = "Email did not match requirements")
        String email) {
}
