package com.example.quizwebapplication.dto.question;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionResponse{
    ResponseStatus status;
    QuestionDTO question;
}
