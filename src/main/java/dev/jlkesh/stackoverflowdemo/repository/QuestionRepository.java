package dev.jlkesh.stackoverflowdemo.repository;

import dev.jlkesh.stackoverflowdemo.domains.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
