package com.example.quizwebapplication.dto.choice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChoiceDTO {
    private Integer choiceId;
    private String description;
    private boolean isCorrect;
}
