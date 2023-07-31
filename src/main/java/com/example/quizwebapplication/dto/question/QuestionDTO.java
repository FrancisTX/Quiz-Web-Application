package com.example.quizwebapplication.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDTO {
    private Integer questionId;
    private Integer categoryId;
    private String content;
    private Boolean isActive;
}
