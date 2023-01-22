package dev.jlkesh.stackoverflowdemo.controllers;

import com.sun.jdi.event.StepEvent;
import dev.jlkesh.stackoverflowdemo.dtos.AnswerCreateDTO;
import dev.jlkesh.stackoverflowdemo.services.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping(value = "/save/")
    public String save(@ModelAttribute AnswerCreateDTO dto) {
        answerService.save(dto);
        return "redirect:/question/get/%s/".formatted(dto.questionId());
    }

    @ExceptionHandler({RuntimeException.class})
    public String exceptionHandler(Model model, RuntimeException e) {
        model.addAttribute("code", 500);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

}
