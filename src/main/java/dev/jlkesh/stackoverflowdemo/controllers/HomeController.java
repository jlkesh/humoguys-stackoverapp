package dev.jlkesh.stackoverflowdemo.controllers;

import dev.jlkesh.stackoverflowdemo.domains.Question;
import dev.jlkesh.stackoverflowdemo.services.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    private final QuestionService questionService;

    public HomeController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Question> pageContent = questionService.getAll(page, size);
        int totalPages = pageContent.getTotalPages();
        model.addAttribute("page", pageContent);
        model.addAttribute("totalPagesCount", totalPages - 1);
        return "main";
    }

}
