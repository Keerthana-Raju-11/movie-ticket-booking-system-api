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
}
