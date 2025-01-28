package com.example.gestion_conciertos.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja las excepciones de tipo RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        // Personaliza el mensaje de error para RuntimeException
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
    }

    // Maneja las excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Personaliza el mensaje de error para Exception
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud incorrecta: " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        ErrorResponse errorResponse = ErrorResponse.builder(ex, problemDetail)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}
