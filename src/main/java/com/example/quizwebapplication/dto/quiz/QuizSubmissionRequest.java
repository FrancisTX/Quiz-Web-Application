package com.example.quizwebapplication.dto.quiz;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class QuizSubmissionRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    @NotEmpty
    private List<SingleQuizResult> result;
}
