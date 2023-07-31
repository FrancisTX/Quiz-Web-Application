package com.example.quizwebapplication.dto.signup;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignupRequest{
    @NotEmpty(message = "Email is required")
    @Email
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "firstName is required")
    private String firstName;

    @NotEmpty(message = "lastName is required")
    private String lastName;
}