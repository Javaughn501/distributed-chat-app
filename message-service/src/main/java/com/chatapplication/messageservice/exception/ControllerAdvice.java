package com.chatapplication.messageservice.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionDTO resourceNotFound(ResourceNotFoundException e) {
        return ExceptionDTO.builder()
                .message(e.getMessage())
                .build();
    }

}
