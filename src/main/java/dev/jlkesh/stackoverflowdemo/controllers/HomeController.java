package dev.jlkesh.stackoverflowdemo.controllers;

import dev.jlkesh.stackoverflowdemo.domains.Question;
import dev.jlkesh.stackoverflowdemo.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class HomeController {

    private final QuestionService questionService;

    public HomeController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Question> questions = questionService.getAll();
        model.addAttribute("questions", questions);
        return "index";
    }

    @GetMapping("/unauthorized")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorized(Model model) {
        model.addAttribute("message", "Unauthorized");
        return "error";
    }

}
