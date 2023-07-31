package com.example.quizwebapplication.dto.contact;

import com.example.quizwebapplication.domain.Contact;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactResponse {
    ResponseStatus status;
    private Contact contact;
}
