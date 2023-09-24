package com.tree.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
//        log.info("General Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred. Please try again later.");
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
//        log.info("User not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found: " + e.getMessage());
    }
    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<?> handleInvalidRequestException(BusinessValidationException e) {
//        log.info("Invalid request");
        return ResponseEntity.badRequest()
                .body("Invalid request: " + e.getMessage());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
//        log.info("Access denied");
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Access denied: " + e.getMessage());
    }
    @ExceptionHandler(SessionTimeoutException.class)
    public ResponseEntity<?> handleSessionTimeoutException(SessionTimeoutException e) {
//        log.info("Session timed out");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Session timed out: " + e.getMessage());
    }
}

