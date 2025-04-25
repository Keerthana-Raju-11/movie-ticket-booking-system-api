package com.example.mdb.response;

import com.example.mdb.entity.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RestBuilder {
    public <T> ResponseEntity<ResponseStructure<T>> success (HttpStatus status , String message , T data){
        ResponseStructure <T> structure = ResponseStructure
                .<T> builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.status(status).body(structure);

    }

    public ResponseEntity<ResponseStructure<String>> error(HttpStatus httpStatus, String message, String errorDetails) {
        ResponseStructure<String> structure = ResponseStructure
                .<String>builder()
                .status(httpStatus.value())
                .message(message)
                .data(errorDetails)  // This would be the error message or details
                .build();
        return ResponseEntity.status(httpStatus).body(structure);
    }
}
