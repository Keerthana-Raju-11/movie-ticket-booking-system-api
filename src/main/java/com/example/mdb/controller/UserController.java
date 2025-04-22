package com.example.mdb.controller;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.exception.DuplicateEmailException;
import com.example.mdb.response.RestBuilder;
import com.example.mdb.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private RestBuilder restBuilder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userDTO) throws DuplicateEmailException {
        userService.register(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

}
