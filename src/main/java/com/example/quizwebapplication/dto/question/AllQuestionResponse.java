package com.example.quizwebapplication.dto.question;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllQuestionResponse {
    ResponseStatus status;
    List<QuestionDTO> questions;
}
