package com.titi.ecommercebackend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class JwtSecretGenerator {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32]; // 256 bits
        random.nextBytes(bytes);
        String secret = Base64.getEncoder().encodeToString(bytes);
        System.out.println("Generated JWT Secret: " + secret);
    }
}
