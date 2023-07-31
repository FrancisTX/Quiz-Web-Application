package com.example.quizwebapplication.dto.quiz;

import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import com.example.quizwebapplication.dto.question.QuestionDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class QuestionWithChoicesDTO {
    private QuestionDTO question;
    private List<ChoiceDTO> choices;
}
