package com.example.mdb.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.ErrorResponse;

@Data
@Builder
public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;
    private ErrorResponse error;
}
