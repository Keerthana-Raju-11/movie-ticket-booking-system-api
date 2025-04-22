package com.example.mdb;

import java.util.UUID;

public class TestUUID {
    public static void main(String[] args) {
        String generatedId = UUID.randomUUID().toString();
        System.out.println("Generated UUID: " + generatedId);
    }
}
