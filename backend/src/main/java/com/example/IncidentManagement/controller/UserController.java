package com.example.IncidentManagement.controller;

import com.example.IncidentManagement.model.User;
import com.example.IncidentManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users") // Base URL for user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint to register a new user.
     *
     * @param user The user details to register.
     * @return The registered user object with a 201 status on success.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED); // 201 for successful registration
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST); // 400 for validation errors
        }
    }

    /**
     * Endpoint for user login.
     *
     * @param user The user credentials (email and password).
     * @return A JWT token on successful login or an error message on failure.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String token = userService.login(user);
            return new ResponseEntity<>(token, HttpStatus.OK); // 200 for successful login with token
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED); // 401 for login failure
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 for server errors
        }
    }

    /**
     * Endpoint to initiate password reset for a user.
     *
     * @param user The user whose password is to be reset (email).
     * @return A message confirming the password reset request or an error message.
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody User user) {
        try {
            userService.forgotPassword(user); // Call service to handle password reset
            return new ResponseEntity<>("Password reset link sent to your email!", HttpStatus.OK); // 200 for successful request
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500 for server errors
        }
    }
}
