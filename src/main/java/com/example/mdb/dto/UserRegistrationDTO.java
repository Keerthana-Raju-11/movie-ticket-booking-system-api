package com.example.mdb.dto;

import com.example.mdb.entity.UserDetails;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
public class UserRegistrationDTO {

    @Setter
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Setter
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Setter
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is mandatory")
    private String email;

    public String PhoneNumber;
    public String UserRole;




}
