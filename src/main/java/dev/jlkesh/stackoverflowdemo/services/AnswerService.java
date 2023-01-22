package dev.jlkesh.stackoverflowdemo.services;

import dev.jlkesh.stackoverflowdemo.domains.Answer;
import dev.jlkesh.stackoverflowdemo.domains.Question;
import dev.jlkesh.stackoverflowdemo.dtos.AnswerCreateDTO;
import dev.jlkesh.stackoverflowdemo.repository.AnswerRepository;
import dev.jlkesh.stackoverflowdemo.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
    public Integer save(AnswerCreateDTO dto) {
        Integer questionId = dto.questionId();

        Question question = questionRepository
                .findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id '%d'".formatted(questionId)));

        Answer answer = Answer.builder()
                .text(dto.text())
                .createdBy(-1)
                .question(question)
                .build();

        answerRepository.save(answer);
        return answer.getId();
    }

    public List<Answer> getAnswersByQuestionId(Integer id) {
        return answerRepository.findAllByQuestionId(id);
    }

    public Answer get(Integer id) {
        return answerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found with id '%d'".formatted(id)));
    }

    public Answer delete(Integer id) {
        Answer answer = get(id);
        answerRepository.deleteById(id);
        return answer;
    }
}
