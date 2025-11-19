package com.titi.ecommercebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private int status;
    private String token;
    private String message;
    private String email;
    private String role;

    public AuthResponse(String token, String message) {
        this.status = 200;
        this.role = "ROLE_USER";
        this.token = token;
        this.message = message;
    }
}
