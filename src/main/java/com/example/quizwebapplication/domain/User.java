package com.example.quizwebapplication.domain;

import lombok.*;
import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="u_id")
    private int id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name="firstname", nullable = false)
    private String firstName;
    @Column(name="lastname", nullable = false)
    private String lastName;
    @Column(name="is_active",nullable = false)
    private boolean isActive;
    @Column(name="is_admin",nullable = false)
    private boolean isAdmin;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuizResult> quizResults = new ArrayList<>();
}