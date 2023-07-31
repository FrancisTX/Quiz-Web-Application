package com.example.quizwebapplication.dto.contact;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Builder
public class ContactRequest {
    @NotEmpty(message = "Subject is required")
    private String subject;
    @NotEmpty(message = "Message is required")
    private String message;
    @NotEmpty(message = "Email is required")
    private String email;
}
