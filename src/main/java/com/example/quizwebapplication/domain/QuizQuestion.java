package com.example.quizwebapplication.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="quiz_question")
public class QuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="quiz_question_id")
    private int quizQuestionId;

    @ManyToOne
    @JoinColumn(name="quiz_result_id")
    private QuizResult quizResult;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name="choice_id")
    private Choice choice;
}
