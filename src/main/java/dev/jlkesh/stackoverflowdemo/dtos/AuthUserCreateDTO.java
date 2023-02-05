package dev.jlkesh.stackoverflowdemo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserCreateDTO {
    @NotBlank(message = "Username can not be blank")
    private String username;

    @NotBlank(message = "Password can not be blank")
    // @Pattern(regexp = "^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*\\\\d+)(?=.*[!@#$&*?><]+).{8,30}$", message = "Password did not match requirements")
    private String password;
    @NotBlank(message = "Confirm Password can not be blank")
    private String confirmPassword;

    @NotBlank(message = "Email can not be blank")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$", message = "Email did not match requirements")
    private String email;
}
