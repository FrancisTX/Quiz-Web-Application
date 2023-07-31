package com.example.quizwebapplication.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@Builder
public class UserRequest {
    @NotEmpty(message = "Email is required")
    @Email
    private String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 5, max = 30, message = "Password must be between 5 and 30 characters")
    private String password;
    @NotEmpty(message = "Firstname is required")
    private String firstName;
    @NotEmpty(message = "Lastname is required")
    private String lastName;
    @NotNull(message = "isActive is required")
    private Boolean isActive;
    @NotNull(message = "isAdmin is required")
    private Boolean isAdmin;
}
