package dev.jlkesh.stackoverflowdemo.controllers;


import dev.jlkesh.stackoverflowdemo.dtos.AuthUserCreateDTO;
import dev.jlkesh.stackoverflowdemo.services.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthUserService authUserService;

    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("/login/")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/logout/")
    public String logoutPage() {
        return "auth/logout";
    }

    @GetMapping("/register/")
    public String registerPage() {
        return "auth/register";
    }

    @PostMapping("/register/")
    public String register(@Valid @ModelAttribute AuthUserCreateDTO dto, BindingResult result) {
        if (result.hasErrors()){

        }
        authUserService.save(dto);
        return "redirect:/auth/login/";
    }


}
