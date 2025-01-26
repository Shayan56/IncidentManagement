package com.example.IncidentManagement.service;

import com.example.IncidentManagement.model.User;
import com.example.IncidentManagement.repository.UserRepository;
import com.example.IncidentManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Register a new user
    public User registerUser(User user) {
        return userRepository.save(user); // Save the user to the database
    }

    // Login user and return a JWT token
    public String login(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            List<String> roles = Arrays.asList("USER"); // Assign default roles; customize as needed
            return jwtUtil.generateToken(user.getEmail(), roles); // Generate and return the JWT token
        }
        return "Invalid credentials"; // Return error message if credentials are invalid
    }

    // Password reset functionality (forgot-password flow)
    public void forgotPassword(User user) {
        // Logic to initiate password reset (e.g., sending a reset link via email)
        // This should ideally be implemented with a service like JavaMailSender to send an email.
        // Placeholder logic for now
        System.out.println("Password reset link sent to: " + user.getEmail());
    }
}
