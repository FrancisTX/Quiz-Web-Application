package com.example.quizwebapplication.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isAdmin;
}
