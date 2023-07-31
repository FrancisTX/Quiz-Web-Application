package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.domain.Contact;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.contact.ContactRequest;
import com.example.quizwebapplication.dto.contact.ContactResponse;
import com.example.quizwebapplication.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;

@RestController
public class ContactController {
    private ContactService contactService;
    @Autowired
    ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    public ContactResponse postContact(@Valid @RequestBody ContactRequest contactRequest) {
        Date startTime = new Date();
        Timestamp startTimeTimestamp = new Timestamp(startTime.getTime());

        Contact contact = Contact.builder()
                .subject(contactRequest.getSubject())
                .message(contactRequest.getMessage())
                .email(contactRequest.getEmail())
                .time(startTimeTimestamp)
                .build();

        contactService.insertContact(contact);

        return ContactResponse.builder()
                .status(
                        ResponseStatus.builder()
                        .success(true)
                        .message("Success! Contact send to admin")
                        .build()
                ).contact(contact)
                .build();
    }
}
