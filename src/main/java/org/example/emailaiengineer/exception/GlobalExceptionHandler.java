package org.example.emailaiengineer.exception;

import org.example.emailaiengineer.dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LlmException.class)
    public ResponseEntity<ApiResponse<Void>> handleLlmException(LlmException ex) {
        return new ResponseEntity<>(new ApiResponse<>(false, ex.getMessage(), null, LocalDateTime.now()), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(RuntimeException ex) {
        return new ResponseEntity<>(new ApiResponse<>(false, ex.getMessage(), null, LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
