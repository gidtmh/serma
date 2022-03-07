package com.example.server.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    public final ResponseEntity<Object> handleJwtAuthenticationException(
        JwtAuthenticationException ex, WebRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        errors.add(error);
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<Object> handleAuthenticationException(
        AuthenticationException ex, WebRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        errors.add(error);
        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
