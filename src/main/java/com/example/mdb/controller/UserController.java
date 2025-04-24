package com.example.mdb.controller;


import com.example.mdb.dto.UserRegistrationDTO;
import com.example.mdb.entity.UserDetails;
import com.example.mdb.response.ResponseStructure;
import com.example.mdb.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.mdb.response.RestBuilder;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final RestBuilder restBuilder;

    @PostMapping("/register")
    public ResponseStructure<String> registeredUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        String message = userService.register(userRegistrationDTO);
        return ResponseStructure.<String>builder()
                .status(HttpStatus.CREATED.value())
                .message("User Registered successfully")
                .data(message)
                .build();
    }


}
