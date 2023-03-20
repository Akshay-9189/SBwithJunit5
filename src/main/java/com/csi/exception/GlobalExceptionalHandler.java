package com.csi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionalHandler {

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<?> handleNotFound(EmployeeNotFound employeeNotFound) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", employeeNotFound.getMessage());
        map.put("Status :- ", HttpStatus.NOT_FOUND);
        map.put("Code :-", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBeanValidation(MethodArgumentNotValidException exception) {
        Map<String, String> map = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error ->
                map.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleAnyException(RuntimeException runtimeException) {
        Map<String, Object> map = new HashMap<>();
        map.put("Message :- ", runtimeException.getMessage());
        map.put("Status :- ", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("Code :- ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.info("Stack Trace", (Object[]) runtimeException.getStackTrace());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}