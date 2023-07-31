package com.example.quizwebapplication.dto.quiz;

import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import com.example.quizwebapplication.dto.question.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuizQuestionsDTO {
    private QuestionDTO questionsDto;
    private ChoiceDTO choiceDto;
    private List<ChoiceDTO> choices;
}
