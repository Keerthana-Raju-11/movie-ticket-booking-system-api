package com.example.mdb.security;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder {

    // Method to encode password
    public String encode(String password) {
        // Generate a salt with a log_rounds parameter for security strength (e.g., 12)

        String salt = BCrypt.gensalt(12);
        // Hash the password using the salt
        return BCrypt.hashpw(password, salt);
    }

    // Method to check if the password matches the hashed password
    public boolean matches(String password, String hashedPassword) {
        // Verify if the given password matches the hashed password
        return BCrypt.checkpw(password, hashedPassword);
    }
}
