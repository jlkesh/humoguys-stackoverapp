package dev.jlkesh.stackoverflowdemo.controllers;


import dev.jlkesh.stackoverflowdemo.dtos.AuthUserCreateDTO;
import dev.jlkesh.stackoverflowdemo.services.AuthUserService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String registerPage(Model model) {
        model.addAttribute("dto", new AuthUserCreateDTO());
        return "auth/register";
    }

    @PostMapping("/register/")
    public String register(@Valid @ModelAttribute(name = "dto") AuthUserCreateDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        authUserService.save(dto);
        return "redirect:/auth/login/";
    }


}
