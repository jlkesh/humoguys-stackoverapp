package dev.jlkesh.stackoverflowdemo.domains;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "stackoverflowapp")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String content;

    @OneToMany(
            targetEntity = Answer.class,
            fetch = FetchType.LAZY,
            mappedBy = "question",
            cascade = CascadeType.ALL
    )
    private List<Answer> answers;

    @CreationTimestamp
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer createdBy;

    @Column(nullable = false)
    private boolean answered;
}