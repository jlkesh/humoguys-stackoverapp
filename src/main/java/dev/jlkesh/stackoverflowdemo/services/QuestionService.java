package dev.jlkesh.stackoverflowdemo.services;

import dev.jlkesh.stackoverflowdemo.domains.Question;
import dev.jlkesh.stackoverflowdemo.dtos.QuestionCreateDTO;
import dev.jlkesh.stackoverflowdemo.dtos.QuestionUpdateDTO;
import dev.jlkesh.stackoverflowdemo.repository.QuestionRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record QuestionService(QuestionRepository questionRepository) {

    public Question get(@NonNull Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Question with id '%s' not found".formatted(id)
                ));
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Integer save(@NonNull QuestionCreateDTO dto) {
        var question = Question.builder()
                .title(dto.title())
                .content(dto.content())
                .createdBy(-1)
                .build();
        questionRepository.save(question);
        return question.getId();
    }

    public boolean update(@NonNull QuestionUpdateDTO dto) {
        Integer id = dto.id();
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found by id '%s'"
                        .formatted(id)));

        question.setTitle(Objects.requireNonNullElse(dto.title(), question.getTitle()));
        question.setContent(Objects.requireNonNullElse(dto.content(), question.getContent()));

        questionRepository.save(question);

        return true;
    }

    public boolean delete(@NonNull Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found by id '%s'"
                        .formatted(id)));
        questionRepository.delete(question);
        return true;
    }
}
