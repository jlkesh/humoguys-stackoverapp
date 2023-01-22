package dev.jlkesh.stackoverflowdemo.domains;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "stackoverflowapp")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Integer createdBy;

    @Column(nullable = false)
    private boolean accepted;


    @ManyToOne(targetEntity = Question.class)
    private Question question;

}
