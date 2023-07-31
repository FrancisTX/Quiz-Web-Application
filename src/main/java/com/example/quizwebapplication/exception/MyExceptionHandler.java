package com.example.quizwebapplication.exception;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(value = {ResourceNotFoundException.class})
//    public ResponseEntity<ResponseStatus> handleResourceNotFoundException(ResourceNotFoundException e){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((ResponseStatus.builder()
//                .success(false)
//                .message(e.getMessage()).build()));
//    }
//
//    @ExceptionHandler(value = {SignatureException.class})
//    public ResponseEntity<ResponseStatus> handleSignatureException(SignatureException e){
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(ResponseStatus.builder()
//                        .success(false)
//                        .message(e.getMessage())
//                        .build());
//    }
//
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ResponseStatus> handleAccessDeniedException(AccessDeniedException ex) {
        // Customize your response here
        String message = "Access denied";
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseStatus.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(value = {DuplicateResourceException.class})
    public ResponseEntity<UserResponse> handleDuplicateResourceException(DuplicateResourceException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(UserResponse.builder()
                        .status(ResponseStatus.builder()
                                .success(false)
                                .message(e.getMessage())
                                .build())
                        .user(null)
                        .build());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseStatus> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseStatus.builder()
                        .success(false)
                        .message(e.getMessage())
                        .build());
    }
}
