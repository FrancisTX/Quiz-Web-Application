package com.example.quizwebapplication.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contact_id")
    private int contactId;
    @Column(name="subject")
    private String subject;
    @Column(name="message")
    private String message;
    @Column(name="email")
    private String email;
    @Column(name="time")
    private Date time;
}
