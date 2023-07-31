package com.example.quizwebapplication.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="choice")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="choice_id")
    private Integer choiceId;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @Column(name="description")
    private String description;

    @Column(name="is_correct")
    private boolean isCorrect;

    @OneToMany(mappedBy = "choice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuizQuestion> quizQuestions = new ArrayList<>();
}
