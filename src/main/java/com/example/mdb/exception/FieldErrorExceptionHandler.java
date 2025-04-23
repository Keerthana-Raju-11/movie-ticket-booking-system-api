package com.example.mdb.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {


    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<Map<String, Object>> errorList = new ArrayList<>();

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        for (ObjectError error : allErrors) {
            Map<String, Object> errorMap = new HashMap<>();

            if (error instanceof FieldError fieldError) {
                errorMap.put("field", fieldError.getField());
                errorMap.put("rejectedValue", null); // explicitly null
                errorMap.put("message", fieldError.getDefaultMessage());
            } else {
                errorMap.put("field", "nonFieldError");
                errorMap.put("rejectedValue", null);
                errorMap.put("message", error.getDefaultMessage());
            }

            errorList.add(errorMap);
        }

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
//hey hi , im bored
/** what should i do
 * i wanna eat , drink , sleep and repeat
 * ask raju to leave the class!!
 * i want to go home !!!!!!!!!!!!!!!!!
 * everything is going out of box or out of my head , so i dont know what to do
 * i just wanna go home kindly help me to get-out of here
 */