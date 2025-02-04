package com.example.IncidentManagement.service;

import com.example.IncidentManagement.model.User;
import com.example.IncidentManagement.repository.UserRepository;
import com.example.IncidentManagement.util.JwtUtil;
import jakarta.transaction.Transactional;
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

    @Transactional
    public User registerUser(User user) {
        // Ensure new users do not have an ID
        if (user.getId() != null) {
            throw new IllegalArgumentException("New users should not have an ID");
        }

        // Check if a user with the same email already exists
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("A user with this email already exists");
        }

        // Save the new user
        return userRepository.save(user);
    }

    public String login(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");  // Handle missing user properly
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");  // Better error message
        }

        List<String> roles = Arrays.asList("USER");
        return jwtUtil.generateToken(user.getEmail(), roles);
    }

    // Password reset functionality (forgot-password flow)
    public void forgotPassword(User user) {
        // Logic to initiate password reset (e.g., sending a reset link via email)
        // This should ideally be implemented with a service like JavaMailSender to send an email.
        // Placeholder logic for now
        System.out.println("Password reset link sent to: " + user.getEmail());
    }
}
