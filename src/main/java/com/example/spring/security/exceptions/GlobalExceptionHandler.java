package com.example.spring.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGeneralException(Exception ex) {
        List<String> errors = List.of(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorsMap(errors));
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, List<String>>> handleRuntimeException(RuntimeException ex) {
        List<String> errors = List.of(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getErrorsMap(errors));
    }

    @ExceptionHandler(InvalidJwtException.class)
    public final ResponseEntity<Map<String, List<String>>> handleInvalidJwtException(InvalidJwtException ex) {
        List<String> errors = List.of(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrorsMap(errors));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorsMap = new HashMap<>();
        errorsMap.put("error", errors);

        return errorsMap;
    }
}
