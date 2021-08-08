package com.example.demo.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<String> handleEmptyResultDataAccessException(Exception e){
        return ResponseEntity.badRequest().body("EmptyResultDataAccess Error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleSystemException(Exception e){
        System.out.println(e);
        return ResponseEntity.internalServerError().body("Unknown Error");
    }
}
