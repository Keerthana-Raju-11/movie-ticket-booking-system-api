package com.example.mdb.controller;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.dto.UserRequest;
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

    @PutMapping
    public ResponseEntity<UserResponse> updatedUser(
            @RequestParam String email,
            @RequestBody UserRequest userRequest){
        UserResponse updatedUser = userService.updateUser(email, userRequest);
        return new ResponseEntity<>(updatedUser , HttpStatus.OK);
    }

}
