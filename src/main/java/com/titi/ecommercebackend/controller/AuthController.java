package com.titi.ecommercebackend.controller;

import com.titi.ecommercebackend.dto.AuthResponse;
import com.titi.ecommercebackend.dto.LoginDto;
import com.titi.ecommercebackend.dto.RegisterDto;
import com.titi.ecommercebackend.service.userserviceinterface.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterDto request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/admin/register")
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthResponse> registerAdmin(@Valid @RequestBody RegisterDto request) {
        return new ResponseEntity<>(userService.registerUserAdmin(request), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto request) {
        AuthResponse response = userService.loginUser(request);
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        return new ResponseEntity<>(response, status);
    }
}