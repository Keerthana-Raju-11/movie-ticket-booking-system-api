package com.example.mdb.security;

import java.security.SecureRandom;
import java.util.Base64;

public class SecureRandomExample {

    // Generate a cryptographically strong salt
    public static String generateSalt(int length) {
        // Create an instance of SecureRandom
        SecureRandom secureRandom = new SecureRandom();

        // Create a byte array to store the random salt
        byte[] salt = new byte[length];

        // Generate the random salt
        secureRandom.nextBytes(salt);

        // Return the salt encoded in Base64 (or other format)
        return Base64.getEncoder().encodeToString(salt);
    }

    public static void main(String[] args) {
        // Generate a random salt of 16 bytes (128 bits)
        String salt = generateSalt(16);
        System.out.println("Generated Salt: " + salt);
    }
}
