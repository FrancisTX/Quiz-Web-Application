package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.service.QuizService;
import com.example.quizwebapplication.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    UserService userService;
    QuizService quizService;
    public UserController(UserService userService, QuizService quizService) {
        this.userService = userService;
        this.quizService = quizService;
    }

//    @GetMapping("/users/{id}")
//    public UserResponse getUserById(@PathVariable int id) {
//        User user = userService.getUserById(id);
//        if(user == null){
//            return UserResponse.builder()
//                    .status(
//                            ResponseStatus.builder()
//                                    .success(false)
//                                    .message("Oops, user not found")
//                                    .build()
//                    )
//                    .build();
//        }
//
//        return UserResponse.builder()
//                .status(
//                        ResponseStatus.builder()
//                                .success(true)
//                                .message("Success! User was found")
//                                .build()
//                )
//                .user(user)
//                .build();
//    }

//    @PostMapping("/users")
//    public UserResponse addUser(@Valid @RequestBody UserRequest userRequest,
//                                BindingResult bindingResult) {
//
//        UserResponse errorResponse = userService.checkInput(bindingResult);
//        if(errorResponse != null){
//            return errorResponse;
//        }
//
//        User newUser = User.builder()
//                .firstName(userRequest.getFirstName())
//                .lastName(userRequest.getLastName())
//                .email(userRequest.getEmail())
//                .password(userRequest.getPassword())
//                .isActive(userRequest.getIsActive())
//                .isAdmin(userRequest.getIsAdmin())
//                .build();
//
//        //add user to database
//        userService.addUser(newUser);
//
//        return UserResponse.builder()
//                .status(
//                        ResponseStatus.builder()
//                                .success(true)
//                                .message("Success! User was added")
//                                .build()
//                )
//                .user(newUser)
//                .build();
//    }

//    @DeleteMapping("/users/{id}")
//    public UserResponse deleteUser(@PathVariable int id) {
//        User user = userService.getUserById(id);
//        if(user == null){
//            return UserResponse.builder()
//                    .status(
//                            ResponseStatus.builder()
//                                    .success(false)
//                                    .message("Oops, user not found")
//                                    .build()
//                    )
//                    .build();
//        }
//        //delete user
//        userService.deleteUserById(id);
//
//        return UserResponse.builder()
//                .status(
//                        ResponseStatus.builder()
//                                .success(true)
//                                .message("Success! User was deleted")
//                                .build()
//                )
//                .user(user)
//                .build();
//    }
}
