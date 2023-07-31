package com.example.quizwebapplication.dto.quiz;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.quizResult.QuizResultDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizResultResponse {
    ResponseStatus status;
    List<QuizResultDTO> quizResults;
}
