package com.titi.ecommercebackend.service.userserviceimpl;

import com.titi.ecommercebackend.dto.AuthResponse;
import com.titi.ecommercebackend.dto.LoginDto;
import com.titi.ecommercebackend.dto.RegisterDto;
import com.titi.ecommercebackend.entity.User;
import com.titi.ecommercebackend.entity.UserRole;
import com.titi.ecommercebackend.repository.UserRepository;
import com.titi.ecommercebackend.security.JwtUtil;
import com.titi.ecommercebackend.service.userserviceinterface.UserService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse registerUser(RegisterDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return AuthResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("User already exists")
                    .email(request.getEmail())
                    .build();
        }

        // Create new user
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(UserRole.ROLE_USER)
                .enabled(true)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("User registered")
                .email(user.getEmail())
                .token(jwtUtil.generateToken(user))
                .role(String.valueOf(UserRole.ROLE_USER))
                .build();
    }

    @Override
    public AuthResponse registerUserAdmin(RegisterDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return AuthResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Admin already exists")
                    .email(request.getEmail())
                    .build();
        }

        // Create new user
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(UserRole.ROLE_ADMIN)
                .enabled(true)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Admin registered")
                .email(user.getEmail())
                .token(jwtUtil.generateToken(user))
                .role(String.valueOf(UserRole.ROLE_ADMIN))
                .build();
    }


    @Override
    public AuthResponse loginUser(LoginDto request) {
        try {
            // Authenticate user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Get user from database
            User user = findByEmail(request.getEmail());

            // Generate JWT token
            String token = jwtUtil.generateToken(user);

            // Return success response
            return AuthResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Login successful")
                    .email(user.getEmail())
                    .token(token)
                    .role(user.getRole().name())
                    .build();

        } catch (BadCredentialsException e) {
            return AuthResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED.value())
                    .message("Invalid email or password")
                    .email(request.getEmail())
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Login failed: " + e.getMessage())
                    .email(request.getEmail())
                    .build();
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
