package com.example.mdb.controller;


import com.example.mdb.dto.UserRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
//@RequestMapping("/register")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @PutMapping
//    public ResponseEntity<UserResponse> updatedUser(
//            @RequestParam String email,
//            @RequestBody UserRequest userRequest){
//        UserResponse updatedUser = userService.updateUser(email, userRequest);
//        return new ResponseEntity<>(updatedUser , HttpStatus.OK);
//    }
//    Soft delete
//    @DeleteMapping("/{id}")
//    public String softDeleteUser(@PathVariable UUID id){
//        userService.softDeleteUser(id);
//        return "User deleted successfully.";
//    }
//
//    @GetMapping("/{id}")
//    public Optional<UserDetails> getUser(@PathVariable UUID id){
//        return userService.getActiveUserById(id);
//    }
    //Delete USer

//
@PostMapping("/register")
public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO, BindingResult result) {
    if (result.hasErrors()) {
        // Collecting validation error messages
        StringBuilder errorMessages = new StringBuilder();
        result.getAllErrors().forEach(error -> errorMessages.append(error.getDefaultMessage()).append("\n"));
        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }
    userService.registerUser(userRegistrationDTO);
    return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
}

}

