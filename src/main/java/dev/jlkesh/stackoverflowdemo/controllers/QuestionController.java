package dev.jlkesh.stackoverflowdemo.controllers;


import dev.jlkesh.stackoverflowdemo.domains.Answer;
import dev.jlkesh.stackoverflowdemo.domains.Question;
import dev.jlkesh.stackoverflowdemo.dtos.QuestionCreateDTO;
import dev.jlkesh.stackoverflowdemo.dtos.QuestionUpdateDTO;
import dev.jlkesh.stackoverflowdemo.services.AnswerService;
import dev.jlkesh.stackoverflowdemo.services.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/save/")
    public String savePage() {
        return "question/create";
    }

    @PostMapping(value = "/save/")
    public String save(@ModelAttribute QuestionCreateDTO dto) {
        questionService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/get/{id}/")
    public String get(@PathVariable Integer id, Model model) {
        Question question = questionService.get(id);
        List<Answer> answers = answerService.getAnswersByQuestionId(id);
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        return "question/detail";
    }

    @PostMapping(value = "/update/")
    public String update(@ModelAttribute QuestionUpdateDTO dto) {
        questionService.update(dto);
        return "index";
    }

    @PostMapping(value = "/delete/{id}/")
    public String delete(@PathVariable Integer id) {
        questionService.delete(id);
        return "index";
    }

}
