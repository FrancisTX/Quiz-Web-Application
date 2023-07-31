package com.example.quizwebapplication.dto.quiz;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizQuestionResponse {
    ResponseStatus status;
    List<QuestionWithChoicesDTO> QuestionsWithChoices;
}
