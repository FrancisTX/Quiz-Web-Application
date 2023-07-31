package com.example.quizwebapplication.dto.contact;

import com.example.quizwebapplication.domain.Contact;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllContactResponse {
    ResponseStatus status;
    private List<Contact> contacts;
}