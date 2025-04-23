package com.example.mdb.controller;

import com.example.mdb.dto.RegisterRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Register the user and get the response DTO
        UserResponse registeredUser = userService.registerUser(registerRequest);

        // Return the response with CREATED status
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
