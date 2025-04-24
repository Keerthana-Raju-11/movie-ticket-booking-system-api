package com.example.mdb.exception;

import com.example.mdb.response.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Example exception handler method
    @ExceptionHandler(SomeException.class)
    public ResponseEntity<ResponseStructure<List<String>>> handleSomeException(SomeException ex) {
        List<String> errorMessages = List.of("Error occurred", "Invalid input");

        ResponseStructure<List<String>> response = ResponseStructure.<List<String>>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("There was an error processing the request")
                .data(errorMessages)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
