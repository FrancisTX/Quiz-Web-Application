package com.example.quizwebapplication.dto.quizResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class QuizResultDTO {
    private int userId;

    private String firstName;
    private String lastName;
    private String categoryName;
    private int quizResultId;
    private Integer categoryId;
    private Date startTime;
    private Date endTime;

    public QuizResultDTO(int quizResultId, Integer categoryId, Date startTime, Date endTime) {
        this.quizResultId = quizResultId;
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
