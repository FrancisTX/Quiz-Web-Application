package com.example.quizwebapplication.domain;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="quiz_result")
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="quiz_result_id")
    private int quizResultId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
    @Column(name="start_time", nullable = false)
    private Date startTime;
    @Column(name="end_time",nullable = false)
    private Date endTime;

    @OneToMany(mappedBy = "quizResult", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuizQuestion> quizQuestions = new ArrayList<>();
}
