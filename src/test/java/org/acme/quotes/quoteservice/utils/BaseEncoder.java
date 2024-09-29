package org.acme.quotes.quoteservice.utils;

import java.util.Base64;

public class BaseEncoder {
    public static void main(String[] args) {
        String originalInput = "username:password";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

        System.out.println("Original Input: " + originalInput);
        System.out.println("Encoded String: " + encodedString);
    }
}