package com.example.mdb.controller;

import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.dto.UserRequest;
import com.example.mdb.dto.UserResponse;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@DeleteMapping("/delete")
public ResponseEntity<String> softDeleteUser(@RequestParam String email) {
    boolean isDeleted = userService.softDeleteUserByEmail(email);

    if (isDeleted) {
        return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }
}


}
