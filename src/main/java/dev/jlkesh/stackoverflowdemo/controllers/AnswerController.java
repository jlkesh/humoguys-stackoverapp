package dev.jlkesh.stackoverflowdemo.controllers;

import dev.jlkesh.stackoverflowdemo.config.AuthUserDetails;
import dev.jlkesh.stackoverflowdemo.domains.Answer;
import dev.jlkesh.stackoverflowdemo.dtos.AnswerCreateDTO;
import dev.jlkesh.stackoverflowdemo.services.AnswerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/answer")
@PreAuthorize("isAuthenticated()")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(value = "/save/")
    public String save(@ModelAttribute AnswerCreateDTO dto, @AuthenticationPrincipal(errorOnInvalidType = true) AuthUserDetails user) {
        answerService.save(dto, user.getAuthUser().getId());
        return "redirect:/question/get/%s/".formatted(dto.questionId());
    }

    @GetMapping(value = "/delete/{id}/")
    public String deletePage(@PathVariable Integer id, Model model) {
        Answer answer = answerService.get(id);
        model.addAttribute("answer", answer);
        return "answer/delete";
    }

    @PostMapping(value = "/delete/{id}/")
    public String delete(@PathVariable Integer id) {
        Answer answer = answerService.delete(id);
        return "redirect:/question/get/%s/".formatted(answer.getQuestion().getId());
    }

    @ExceptionHandler({RuntimeException.class})
    public String exceptionHandler(Model model, RuntimeException e) {
        model.addAttribute("code", 500);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

}
