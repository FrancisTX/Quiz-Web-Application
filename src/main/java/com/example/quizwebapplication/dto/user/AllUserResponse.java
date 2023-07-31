package com.example.quizwebapplication.dto.user;

import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllUserResponse {
    ResponseStatus status;
    List<User> users;
}
