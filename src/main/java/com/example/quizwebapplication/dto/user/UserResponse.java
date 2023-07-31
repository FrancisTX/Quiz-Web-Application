package com.example.quizwebapplication.dto.user;

import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    ResponseStatus status;
    User user;
}
