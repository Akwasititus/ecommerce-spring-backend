package com.titi.ecommercebackend.service.userserviceinterface;

import com.titi.ecommercebackend.dto.AuthResponse;
import com.titi.ecommercebackend.dto.LoginDto;
import com.titi.ecommercebackend.dto.RegisterDto;
import com.titi.ecommercebackend.entity.User;

public interface UserService {
    AuthResponse registerUser(RegisterDto request);
    AuthResponse registerUserAdmin(RegisterDto request);
    User findByEmail(String email);
    AuthResponse loginUser(LoginDto request);
    boolean existsByEmail(String email);
}
