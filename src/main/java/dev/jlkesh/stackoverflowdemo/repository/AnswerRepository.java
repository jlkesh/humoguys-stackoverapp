package dev.jlkesh.stackoverflowdemo.repository;

import dev.jlkesh.stackoverflowdemo.domains.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestionId(Integer questionID);
}
