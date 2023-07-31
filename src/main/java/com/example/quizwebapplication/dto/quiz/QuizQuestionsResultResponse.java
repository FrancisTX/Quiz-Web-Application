package com.example.quizwebapplication.dto.quiz;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizQuestionsResultResponse {
    ResponseStatus status;
    List<QuizQuestionsDTO> quizQuestions;
}