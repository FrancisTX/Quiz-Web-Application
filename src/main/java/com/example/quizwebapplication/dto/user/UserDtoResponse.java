package com.example.quizwebapplication.dto.user;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDtoResponse {
    ResponseStatus status;
    UserDTO userDto;
}