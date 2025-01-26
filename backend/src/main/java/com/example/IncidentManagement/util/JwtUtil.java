package com.example.IncidentManagement.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_super_secure_secret_key"; // Use a strong secret key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour expiration

    /**
     * Generate token with roles.
     *
     * @param username The username for which to generate the token.
     * @param roles    The roles assigned to the user.
     * @return The generated JWT token.
     */
    public String generateToken(String username, List<String> roles) {
        Key key = getSigningKey();
        return Jwts.builder()
                .setSubject(username) // Set the username as the subject of the token
                .claim("roles", roles) // Add roles as a claim
                .setIssuedAt(new Date()) // Set the issue date of the token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set the expiration time
                .signWith(key, SignatureAlgorithm.HS256) // Sign the token with the key
                .compact(); // Create the token
    }

    /**
     * Validate the JWT token.
     *
     * @param token The JWT token to validate.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        try {
            Key key = getSigningKey();
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // Parse the token to get claims
                    .getBody();
            return claims.getExpiration().after(new Date()); // Check if the token is expired
        } catch (Exception e) {
            return false; // If parsing or validation fails, return false
        }
    }

    /**
     * Extract username from the token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username.
     */
    public String extractUsername(String token) {
        Key key = getSigningKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // Parse the token to get claims
                .getBody()
                .getSubject(); // Get the subject (username) from the claims
    }

    /**
     * Extract roles from the token.
     *
     * @param token The JWT token from which to extract the roles.
     * @return The list of roles.
     * @throws IllegalArgumentException if the roles are in an invalid format.
     */
    public List<String> extractRoles(String token) {
        Key key = getSigningKey();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token) // Parse the token to get claims
                .getBody();

        // Extract roles safely from the token
        Object roles = claims.get("roles");
        if (roles instanceof List<?>) {
            return ((List<?>) roles).stream()
                    .filter(role -> role instanceof String) // Ensure each role is a String
                    .map(role -> (String) role)
                    .toList(); // Convert roles to a List of Strings
        } else {
            throw new IllegalArgumentException("Invalid roles format in token"); // Handle invalid roles format
        }
    }

    // Get the signing key for token creation and validation
    private Key getSigningKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName()); // Convert secret key to signing key
    }
}
