package dev.jlkesh.stackoverflowdemo.dtos;

import lombok.NonNull;

public record QuestionUpdateDTO(@NonNull Integer id,
                                String title,
                                String content) {
}
