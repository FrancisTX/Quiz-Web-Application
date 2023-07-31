package com.example.quizwebapplication.dto.login;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    ResponseStatus status;
    private String message;
    private String token;

}