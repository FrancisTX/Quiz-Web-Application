package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.login.LoginRequest;
import com.example.quizwebapplication.dto.login.LoginResponse;
import com.example.quizwebapplication.dto.signup.SignupRequest;
import com.example.quizwebapplication.dto.user.UserResponse;
import com.example.quizwebapplication.security.AuthUserDetail;
import com.example.quizwebapplication.security.JwtUtil;
import com.example.quizwebapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private JwtUtil jwtUtil;

    @Autowired
    public void setJwtProvider(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    //User trying to log in with username and password
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){

        Authentication authentication;

        //Try to authenticate the user using the username and password
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e){
            System.out.println("Invalid credential");
            throw new BadCredentialsException("Provided credential is invalid.");
        }

        //Successfully authenticated user will be stored in the authUserDetail object
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal(); //getPrincipal() returns the user object

        System.out.println("auth:" + authUserDetail.getAuthorities());
        //A token wil be created using the username/email/userId and permission
        String token = jwtUtil.createToken(authUserDetail);

        //Returns the token as a response to the frontend/postman
        return LoginResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! Logged in.")
                                .build()
                )
                .message("Welcome " + authUserDetail.getUsername())
                .token(token)
                .build();
    }

    @PostMapping("/users")
    public UserResponse addUser(@Valid @RequestBody SignupRequest signupRequest,
                                BindingResult bindingResult) {

        UserResponse errorResponse = userService.checkInput(bindingResult);
        if(errorResponse != null){
            return errorResponse;
        }

        User newUser = User.builder()
                .isAdmin(false)
                .isActive(true)
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .email(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .build();
        System.out.println(2);
        //add user to database
        userService.addUser(newUser);
        System.out.println(3);
        return UserResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! User was added")
                                .build()
                )
                .user(newUser)
                .build();
    }
}
